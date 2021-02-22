import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Session implements Runnable {

    private int id;
    private SocketChannel socket;
    private List<SocketChannel> connectedClients;
    private BlockingQueue messages;

    public Session(final int id, final SocketChannel socket, final List<SocketChannel> connectedClients,
                   final BlockingQueue messages) throws IOException {
        this.id = id;
        this.socket = socket;
        this.connectedClients = connectedClients;
        this.messages = messages;
    }

    public void run() {

        ByteBuffer buffer = ByteBuffer.allocate(100);

        // TODO: Launch Reader Thread
        new Thread(new ReaderThread(this.socket, this.messages)).start();
        // TODO: Launch Writer Thread
        new Thread(new WriterThread(this.socket, this.messages)).start();

    }
}
