import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static java.util.concurrent.Executors.defaultThreadFactory;

public class Server {

    private static Logger log = LogManager.getLogger(Server.class);

    private static class Context {

        ByteBuffer buff;
        AsynchronousSocketChannel sc;

        public Context(ByteBuffer buff, AsynchronousSocketChannel sc) {
            this.buff = buff;
            this.sc = sc;
        }

    }

    private static final int PORT = 12345;
    private static ServerContext serverContext;


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



    private static final CompletionHandler<AsynchronousSocketChannel, ServerContext> ach =

            new CompletionHandler<AsynchronousSocketChannel, ServerContext>() {

                @Override
                public void completed(AsynchronousSocketChannel sc,
                                      ServerContext fc) {

                    System.out.println("Accepted!");
                    fc.addClient(sc);
                    ByteBuffer buf = ByteBuffer.allocate(1000);
                    Context c = new Context(buf, sc);

                    readRec(c);

                    acceptRec(fc);
                }

                @Override
                public void failed(Throwable throwable, ServerContext o) {

                }
            };


    public static void readRec(Context c){
        c.sc.read(c.buff, c, read);
    }

    public static void acceptRec(ServerContext fc) {
        fc.getServer().accept(fc, ach);
    }



    public void launch() throws Exception {

        // Creates the pool of threads that are available for accepting and managing connections
        AsynchronousChannelGroup g = AsynchronousChannelGroup.withFixedThreadPool(1, defaultThreadFactory());
        // Binds the server to the thread pool
        AsynchronousServerSocketChannel sc = AsynchronousServerSocketChannel.open(g);
        sc.bind(new InetSocketAddress(Server.PORT));

        // Initalizes the server context
        serverContext = new ServerContext(sc);

        // Continuosly accpets connections
        acceptRec(serverContext);

        // Sets the maximum amount of time that a thread can wait for a new connection
        g.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

        System.out.println("Terminei!");

    }

}