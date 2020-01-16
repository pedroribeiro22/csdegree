import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(12345);
        System.out.println(ss.getLocalSocketAddress()+ "    " +ss.getInetAddress());
        Museu c = new MuseuController();

        while(true) {
            Socket s = ss.accept();
            new Thread(new Client(c, s)).start();
        }
    }
}
