import java.io.*;
import java.net.Socket;

public class NonInteractiveClient {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 12345;
    private int delay;

    public NonInteractiveClient(final int delay) {
       this.delay = delay;
    }

    public static void main(String[] args) {

        int delay = Integer.parseInt(args[1]);
        new NonInteractiveClient(delay).launch();

    }

    public void launch() {

        try {

            Socket s = new Socket(this.HOSTNAME, this.PORT);

            System.out.println("Address: " + s.getLocalAddress() + ", Port: " + s.getPort());

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            new Thread(new LoadGenerator(out)).start();

            new Thread(new ServerReader(in, this.delay)).start();

        } catch(IOException e) {

            e.printStackTrace();

        }
    }

}
