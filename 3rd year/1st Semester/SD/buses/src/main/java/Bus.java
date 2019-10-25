import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bus {

    private static final int max_capacity = 10; // o autocarro transporta 10 pessoas
    private ReentrantLock l;
    private boolean on_tour = false;
    private int occupation = 0;
    private Condition c = l.newCondition();

    public void take_a_tour() throws InterruptedException{

        // espera que o autocarro volte da viagem
        while(this.on_tour) {
           c.await();
        }
        c.notifyAll();

        // entrar no autocarro (só pode entrar um de cada vez)
        l.lock();
        this.occupation++;
        l.unlock();

        // esperar que o autocarro encha antes de prosseguir viagem
        while(occupation < max_capacity) {
            c.await();
        }
        c.notifyAll();


    }
}
