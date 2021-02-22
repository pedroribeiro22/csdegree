import javax.sound.midi.SysexMessage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

public class Session implements Runnable {

    private int id;
    private SocketChannel socket;
    private List<SocketChannel> connectedClients;

    public Session(final int id, final SocketChannel socket, final List<SocketChannel> connectedClients) throws IOException {
        this.id = id;
        this.socket = socket;
        this.connectedClients = connectedClients;
    }

    public void run(){

        ByteBuffer buffer = ByteBuffer.allocate(100);

        try {

            while (true) {

                // TODO: Read messages from the main socket and send to the other sockets
                this.socket.read(buffer);

                System.out.println("Client said: " + new String(buffer.array()));

                buffer.flip();

                for(SocketChannel client : this.connectedClients) {
                   client.write(buffer.duplicate());
                }

                buffer.clear();

            }

        } catch(IOException e) {

            e.printStackTrace();

        }
    }
}
