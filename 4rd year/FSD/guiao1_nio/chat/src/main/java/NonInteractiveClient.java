import java.io.*;
import java.net.Socket;

public class NonInteractiveClient {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 12345;
    private float write_delay;
    private float read_delay;

    public NonInteractiveClient(final float write_delay, final float read_delay) {
       this.write_delay = write_delay;
       this.read_delay = read_delay;
    }

    public static void main(String[] args) {

        float write_delay = Float.parseFloat(args[0]);
        float read_delay = Float.parseFloat(args[1]);
        new NonInteractiveClient(write_delay, read_delay).launch();

    }

    public void launch() {

        try {

            Socket s = new Socket(this.HOSTNAME, this.PORT);

            System.out.println("Address: " + s.getLocalAddress() + ", Port: " + s.getPort());

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

            new Thread(new LoadGenerator(out, this.write_delay)).start();

            new Thread(new ServerReader(in, this.read_delay)).start();

        } catch(IOException e) {

            e.printStackTrace();

        }
    }

}
