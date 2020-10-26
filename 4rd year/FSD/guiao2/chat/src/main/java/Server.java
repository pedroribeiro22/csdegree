import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.defaultThreadFactory;

public class Server {

    private static class Context {

        ByteBuffer buff;
        AsynchronousSocketChannel sc;

        public Context(ByteBuffer buff, AsynchronousSocketChannel sc) {
            this.buff = buff;
            this.sc = sc;
        }

    }

    private static final int PORT = 12345;
    private static FullContext serverContext;


    public static void main(String[] args) throws Exception {

        new Server().launch();

    }

    private static final CompletionHandler<Integer, Context> write =

            new CompletionHandler<Integer, Context>() {

                @Override
                public void completed(Integer result, Context attachment) {
                    attachment.buff.clear();
                    // readRec(attachment);
                }


                @Override
                public void failed(Throwable exc, Context attachment) {

                }
            };

    private static final CompletionHandler<Integer, Context> read =

            new CompletionHandler<Integer, Context>() {

                @Override
                public void completed(Integer result, Context attachment) {

                    if(result == 0) {
                        try {
                            attachment.sc.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    attachment.buff.flip();

                    for(AsynchronousSocketChannel client : serverContext.getClients()) {
                        client.write(attachment.buff, attachment, write);
                    }
                    readRec(attachment);
                    //attachment.sc.write(attachment.buff, attachment, write);
                }

                @Override
                public void failed(Throwable exc, Context attachment) {

                }
            };



    private static final CompletionHandler<AsynchronousSocketChannel, FullContext> ach =

            new CompletionHandler<AsynchronousSocketChannel, FullContext>() {

                @Override
                public void completed(AsynchronousSocketChannel sc,
                                      FullContext fc) {

                    System.out.println("Accepted!");
                    fc.addClient(sc);
                    ByteBuffer buf = ByteBuffer.allocate(1000);
                    Context c = new Context(buf, sc);

                    readRec(c);

                    acceptRec(fc);
                }

                @Override
                public void failed(Throwable throwable, FullContext o) {

                }
            };

    public static void readRec(Context c){
        c.sc.read(c.buff, c, read);
    }

    public static void acceptRec(FullContext fc) {

        // ach: the handler. In order not to have to create a new handler for every new client we make it static
        fc.getServer().accept(fc, ach);
        //sc.accept(sc, ach);

    }



    public void launch() throws Exception {

        // Creates the thead pool
        AsynchronousChannelGroup g = AsynchronousChannelGroup.withFixedThreadPool(1, defaultThreadFactory());
        AsynchronousServerSocketChannel sc = AsynchronousServerSocketChannel.open(g);
        sc.bind(new InetSocketAddress(Server.PORT));
        serverContext = new FullContext(sc);

        // TODO: Recursive client acceptor
        acceptRec(serverContext);

        g.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("Terminei!");
    }

}