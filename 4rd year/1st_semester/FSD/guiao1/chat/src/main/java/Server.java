import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final int PORT = 12345;

    private int sessionIdentifier = 0;
    private ServerSocket server;
    private List<Socket> clients;

    public static void main(String[] args) {

        new Server().launch();

    }

    public void launch() {


        try {

            // Create the variables necessary to maintain the servers' functionality
            this.server = new ServerSocket(this.PORT);
            // this.server.bind(new InetSocketAddress(this.HOSTNAME, this.PORT));
            this.clients = new ArrayList<>();

            // We have to continually accept new connections
            while(true) {

                // Accept new client connection
                Socket socket = this.server.accept();
                this.clients.add(socket);

                System.out.println("Someone has connected. Here's the info: ");
                System.out.println("Address: " + socket.getLocalAddress() + ", Port: " + socket.getPort());

                new Thread(new Session(this.sessionIdentifier++, socket, this.clients)).start();

            }

        } catch(IOException e) {

            e.printStackTrace();

        }
    }

}