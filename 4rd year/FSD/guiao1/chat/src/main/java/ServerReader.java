import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader implements Runnable {

    private BufferedReader in;
    private int delay;

    public ServerReader(final BufferedReader in, final int delay) {
        this.in = in;
        this.delay = delay;
    }

    public void run() {

        try {

            String message;

            while ((message = this.in.readLine()) != null) {

                System.out.println(message);

            }

            if(this.delay != 0) {
                Thread.sleep(this.delay);
            }

        } catch(IOException | InterruptedException e) {

            e.printStackTrace();

        }

    }
}
