import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;

public class WriterThread implements Runnable {

    private SocketChannel socket;
    private BlockingQueue messages;

    public WriterThread(final SocketChannel socket, final BlockingQueue messages) {
       this.socket = socket;
       this.messages = messages;
    }

    public void run() {

        try {

            while(true) {

                ByteBuffer buffer = (ByteBuffer) this.messages.take();
                this.socket.write(buffer);

            }

        } catch(InterruptedException | IOException e) {

            e.printStackTrace();

        }

    }
}
