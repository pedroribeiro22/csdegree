import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Random;

public class LoadGenerator implements Runnable {

    private final static int stringSize = 10;
    private PrintWriter out;

    public LoadGenerator(final PrintWriter out) {
        this.out = out;
    }

    public static String generateRandomString(int size) {

        byte[] array = new byte[size];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("ASCII"));

    }

    public void run() {

        while(true) {

            String message = LoadGenerator.generateRandomString(this.stringSize);
            this.out.println(message);
            System.out.println("I sent this:" +  message);

        }

    }



}
