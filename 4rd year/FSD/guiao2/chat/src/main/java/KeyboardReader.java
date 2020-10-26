import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class KeyboardReader implements Runnable {

    private PrintWriter out;

    public KeyboardReader(final PrintWriter out) {
        this.out = out;
    }

    public void run() {

        try {
            String message;

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while ((message = in.readLine()) != null) {

                System.out.println();
                out.println(message);

            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
