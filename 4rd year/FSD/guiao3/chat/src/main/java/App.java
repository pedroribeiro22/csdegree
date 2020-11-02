import spullara.nio.channels.FutureServerSocketChannel;
import spullara.nio.channels.FutureSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class App {

    public static void main(String[] args) throws Exception {

        List<FutureSocketChannel> connectedClients = new ArrayList<>();
        FutureServerSocketChannel fsc = new FutureServerSocketChannel();
        fsc.bind(new InetSocketAddress(12345));

        CompletableFuture<FutureSocketChannel> sc = acceptClients(fsc, connectedClients);

        while(true) {
            Thread.sleep(100);
        }
    }

    private static CompletableFuture<FutureSocketChannel> acceptClients(FutureServerSocketChannel fsc, List<FutureSocketChannel> connectedClients) {
        CompletableFuture<FutureSocketChannel> sc = fsc.accept();
        sc.thenAccept(s -> {
            connectedClients.add(s);
            ByteBuffer buff = ByteBuffer.allocate(1000);
            read(buff, s, connectedClients);
            acceptClients(fsc, connectedClients);
        });
        return sc;
    }

    private static void read(ByteBuffer buff, FutureSocketChannel sc, List<FutureSocketChannel> connectedClients) {
        CompletableFuture<Integer> read = sc.read(buff);
        read.thenAccept(i -> {
            if(i == 0) {
            } else {
                buff.flip();
                write(buff, connectedClients);
                read(buff, sc, connectedClients);
            }
        });
    }

    private static void write(ByteBuffer buff, List<FutureSocketChannel> connectedClients) {
        for(FutureSocketChannel fsc : connectedClients) {
            fsc.write(buff.duplicate());
        }
        buff.clear();
    }
}
