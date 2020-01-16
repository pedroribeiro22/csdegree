import javax.naming.ldap.Control;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente implements Runnable {

    private Controlador c;
    private Socket s;

    public Cliente(final Controlador c, final Socket s) {
        this.c = c;
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
            String message;
            while((message = in.readLine()) != null) {
                String[] cmd = message.split(" ");
                if ("go".equals(cmd[0])) {
                    this.c.requisita_viagem(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                } else if ("leave".equals(cmd[0])) {
                    this.c.espera(Integer.parseInt(cmd[1]));
                }
            }
        } catch (Exception ignored) {
        }
    }
}
