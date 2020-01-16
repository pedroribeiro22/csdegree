import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client implements Runnable {

    private Museu m;
    private Socket s;

    public Client(Museu m, Socket s) {
        this.m = m;
        this.s = s;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            PrintWriter out =  new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
            String message;
            while((message = in.readLine()) != null) {
                if ("enterPT\n".equals(message)) {
                    this.m.enterPT();
                } else if ("enterEN\n".equals(message)) {
                    this.m.enterEN();
                } else if ("enterPoly\n".equals(message)) {
                    this.m.enterPoly();
                } else if ("enterGuide\n".equals(message)) {
                    this.m.enterGuide();
                }

                // out.println(this.m.toString());
                out.print(message);
                out.flush();
            }

        } catch (Exception ignored) {
        }
    }
}
