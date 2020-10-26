import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.List;

public class FullContext {

    private AsynchronousServerSocketChannel server;
    private List<AsynchronousSocketChannel> clients;

    public FullContext(AsynchronousServerSocketChannel server) {
        this.server = server;
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
