import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int PORT = 12345;

    private int sessionIdentifier = 0;
    private ServerSocketChannel server;
    private List<SocketChannel> clients;

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

                this.clients.add(socket);

                System.out.println("Someone has connected. Here's the info: ");
                System.out.println("Address: " + socket.getLocalAddress());

                new Thread(new Session(this.sessionIdentifier++, socket, this.clients)).start();

            }

        } catch(IOException e) {

            e.printStackTrace();

        }
    }

}