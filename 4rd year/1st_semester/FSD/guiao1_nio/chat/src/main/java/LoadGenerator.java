import java.io.PrintWriter;
import java.util.Random;

public class LoadGenerator implements Runnable {

    private final static int stringSize = 10;
    private PrintWriter out;
    private float write_delay;

    public LoadGenerator(final PrintWriter out, final float write_delay) {
        this.out = out;
        this.write_delay = write_delay;
    }


    public static String generateRandomString(final int size) {

        int ascii_lower_bound = 97;
        int ascii_upper_bound = 122;
        Random rand = new Random();
        return rand.ints(ascii_lower_bound, ascii_upper_bound + 1)
            .limit(size)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    public void run() {

        while(true) {

            String message = LoadGenerator.generateRandomString(this.stringSize);
            this.out.println(message);
            System.out.println("I sent this:" +  message);

            try {
                Thread.sleep((long) (this.write_delay * 1000));
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}
