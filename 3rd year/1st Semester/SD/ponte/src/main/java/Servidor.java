import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(12345);
        Control c = new ControlPonte();

        while(true) {
            Socket s = ss.accept();
            new Thread(new Cliente(c, s)).start();
        }
    }
}
