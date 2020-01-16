import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente implements Runnable {

    private Control c;
    private Socket s;

    public Cliente(final Control c, final Socket s) {
        this.c = c;
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
            String message;
            while((message = in.readLine()) != null) {
                if ("entrar_carro".equals(message)) {
                    this.c.entra_barco();
                } else if ("entrar_barco".equals(message)) {
                    this.c.entra_barco();
                } else if ("sair_carro".equals(message)) {
                    this.c.sai_carro();
                } else if ("sair_barco".equals(message)) {
                    this.c.sai_barco();
                }
            }
        } catch (Exception ignored) {


        }
    }

}
