import java.net.Socket;

public class Shuttle implements Runnable {

    private Controlador c;
    private Socket s;

    public Shuttle(final Controlador c, final Socket s) {
        this.c = c;
        this.s = s;
    }


    public void run() {
        while(true) {
            this.c.partir();

            try {
                Thread.sleep(5 * 60 * 1000);
            } catch (InterruptedException e) {
            }

            this.c.parar();

            // o partir já espera o tempo necessário para ter passageiros que encontram os requisitos
        }
    }
}
