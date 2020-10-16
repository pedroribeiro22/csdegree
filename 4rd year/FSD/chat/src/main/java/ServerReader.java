import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader implements Runnable {

    private BufferedReader in;

    public ServerReader(final BufferedReader in) {
        this.in = in;
    }

    public void run() {

        try {

            String message;

            while ((message = this.in.readLine()) != null) {

                System.out.println(message);

            }

        } catch(IOException e) {

            e.printStackTrace();

        }

    }
}
