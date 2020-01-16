import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControlPonte implements Control {

    private ReentrantLock lock;
    private Condition allowCarros;
    private Condition allowBarcos;
    private int carros_crossing;
    private boolean carros_allowed;
    private boolean barcos_waiting;
    private boolean barcos_crossing;

    public ControlPonte() {
        this.lock = new ReentrantLock(true);
        this.allowCarros = this.lock.newCondition();
        this.allowBarcos = this.lock.newCondition();
        this.carros_crossing = 0;
        this.carros_allowed = true;
        this.barcos_waiting  = false;
        this.barcos_crossing = false;
    }

    public void entra_carro() {
        this.lock.lock();
        while(this.barcos_crossing || (this.barcos_crossing && this.barcos_waiting) || this.carros_allowed == false) {
            try {
                this.allowCarros.await();
            } catch (InterruptedException ignored) {
            }
        }
        this.carros_allowed = true;
        this.carros_crossing++;
        this.lock.unlock();

    }

    public void sai_carro() {
        this.lock.lock();
        this.carros_crossing--;
        if(this.carros_crossing == 0 && this.barcos_waiting) {
            this.allowBarcos.signal();
        }
        this.lock.unlock();
    }

    public void entra_barco() {
        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException ignored) {
        }
        this.lock.lock();
        this.carros_allowed = false;
        this.barcos_waiting = true;
        while(this.carros_crossing > 0 || (this.barcos_crossing)) {
            try {
                this.allowBarcos.await();
            } catch (InterruptedException ignored) {
            }
        }
        this.barcos_crossing = true;
        this.lock.unlock();
    }

    public void sai_barco() {
        this.lock.lock();
        this.barcos_crossing = false;
        if(this.barcos_waiting = false) {
            this.carros_allowed = true;
            this.allowCarros.signalAll();
        }
        this.lock.unlock();
    }
}
