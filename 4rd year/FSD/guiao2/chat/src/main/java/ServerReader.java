import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader implements Runnable {

    private BufferedReader in;
    private float delay;

    public ServerReader(final BufferedReader in, final float delay) {
        this.in = in;
        this.delay = delay;
    }

    public void run() {

        try {

            String message;

            while ((message = this.in.readLine()) != null) {

                System.out.println("Server sent: " + message);

            }

            if(this.delay != 0) {
                Thread.sleep((long) this.delay * 1000);
            }

        } catch(IOException | InterruptedException e) {

            e.printStackTrace();

        }

    }
}
