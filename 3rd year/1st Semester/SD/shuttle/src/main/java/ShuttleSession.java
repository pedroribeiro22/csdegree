import javax.naming.ldap.Control;

public class ShuttleSession implements Runnable {

    private Controlador c;
    private int posicao;

    public ShuttleSession(final Controlador c) {
        this.c = c;
        this.posicao = 0;
    }

    public void run() {
        while(true) {
           c.notifyPartida();
           System.out.println("Parti de " + this.posicao);
           try {
               Thread.sleep(5 * 60 * 1000);
           } catch (InterruptedException ignored) {
           }
           c.notifyChegada();
           this.posicao = (this.posicao + 1) % 4;
           System.out.println("Cheguei a " + this.posicao);
           // Espera 10 segundos por passageiros, por exemplo
           try {
               Thread.sleep(10 * 1000);
           } catch (InterruptedException ignored) {
           }
        }
    }
}
