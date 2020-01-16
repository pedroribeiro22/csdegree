import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(12345);
        Controlador c = new ShuttleController();
        new Thread(new ShuttleSession(c)).start();
        while(true) {
            Socket client = ss.accept();
            new Thread(new ClientSession(c, client)).start();
        }

    }
}
