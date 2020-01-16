import java.io.*;
import java.net.Socket;

public class ClientSession implements Runnable {

    private Controlador c;
    private Socket s;

    public ClientSession(final Controlador c, final Socket s) {
        this.c = c;
        this.s = s;
    }

    public void run() {
        // por causa do IOException
        try {
           BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
           PrintWriter out = new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
           String message;
           while((message = in.readLine()) != null) {
               try {
                    String[] cmd = message.split(" ");
                    switch(cmd[0]) {
                        case "go":
                            c.requisita_viagem(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                            System.out.println("Partiste");
                            break;
                        case "leave":
                            c.espera(Integer.parseInt(cmd[1]));
                            System.out.println("Chegaste");
                            break;
                        default:
                            System.out.println("Introduza um comando válido, por favor");
                    }
               } catch (Exception ignored) {
               }
           }
        } catch (Exception ignored) {
        }
    }
}
