import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;

public class ReaderThread implements Runnable {

    private SocketChannel socket;
    private BlockingQueue messages;

    public ReaderThread(final SocketChannel socket, final BlockingQueue messages) {
        this.socket = socket;
        this.messages = messages;
    }

    public void run() {

        ByteBuffer buffer = ByteBuffer.allocate(100);

        try {

            while(true) {

                this.socket.read(buffer);
                System.out.println("Client said: " + new String(buffer.array()));
                buffer.flip();

                synchronized (this.messages) {

                    this.messages.put(buffer.duplicate());

                }

                buffer.clear();

            }

        } catch(IOException | InterruptedException e) {

            e.printStackTrace();

        }

    }

}
