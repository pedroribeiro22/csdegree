import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server {

    private static final int PORT = 12345;

    private int sessionIdentifier = 0;
    private ServerSocketChannel server;
    private List<SocketChannel> clients;
    private List<BlockingQueue> connections = new ArrayList<>();

    public static void main(String[] args) {

        new Server().launch();

    }

    public void launch() {


        try {

            // Create the variables necessary to maintain the servers' functionality
            // TODO: this.server = new ServerSocket(this.PORT);
            this.server = ServerSocketChannel.open();
            this.server.bind(new InetSocketAddress(this.PORT));

            // this.server.bind(new InetSocketAddress(this.HOSTNAME, this.PORT));
            this.clients = new ArrayList<>();

            // We have to continually accept new connections
            while(true) {

                // Accept new client connection
                // TODO: Socket socket = this.server.accept();
                SocketChannel socket = this.server.accept();
                BlockingQueue messageQueue = new ArrayBlockingQueue(20);

                this.clients.add(socket);
                this.connections.add(messageQueue);

                System.out.println("Someone has connected. Here's the info: ");
                System.out.println("Address: " + socket.getLocalAddress());

                new Thread(new Session(this.sessionIdentifier++, socket, this.clients, messageQueue)).start();

            }

        } catch(IOException e) {

            e.printStackTrace();

        }
    }

}