import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ServerContext {

    // Main Server Socket
    private AsynchronousServerSocketChannel server;
    // List of currently connected clients
    private List<AsynchronousSocketChannel> clients;

    public ServerContext(AsynchronousServerSocketChannel server) {
        this.server = server;
        // When the server starts it has no connected clients so we initialize the ArrayList empty
        this.clients = new ArrayList<>();
    }

    public AsynchronousServerSocketChannel getServer() {
        return this.server;
    }

    public void addClient(AsynchronousSocketChannel client) {
        this.clients.add(client);
    }

    public List<AsynchronousSocketChannel> getClients() {
        return this.clients;
    }
}
