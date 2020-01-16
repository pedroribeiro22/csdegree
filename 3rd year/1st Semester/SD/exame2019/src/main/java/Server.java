import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(12345);
       Control c = new BridgeControl();

       while(true) {
           Socket client = ss.accept();
           new Thread(new VehicleSession(c, client)).start();
       }

    }
}
