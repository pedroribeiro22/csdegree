import java.io.*;
import java.net.Socket;

public class InteractiveClient {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {

        new InteractiveClient().launch();

    }

    public void launch() {

        try {

            Socket s = new Socket(this.HOSTNAME, this.PORT);

            System.out.println("Address: " + s.getLocalAddress() + ", Port: " + s.getPort());

            BufferedReader fromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

            new Thread(new KeyboardReader(out)).start();

            new Thread(new ServerReader(fromServer, 0)).start();


        } catch(IOException e) {

            e.printStackTrace();
        }
    }
}
