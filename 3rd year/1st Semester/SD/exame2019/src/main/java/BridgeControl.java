import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BridgeControl implements Control {

    private ReentrantLock lock;
    private Condition available_carros;
    private Condition available_barcos;
    private int carros_on_bridge;
    private boolean barcos_waiting;
    private boolean barco_crossing;

    public BridgeControl() {
        this.lock = new ReentrantLock();
        this.available_carros = this.lock.newCondition();
        this.available_barcos = this.lock.newCondition();
        this.carros_on_bridge = 0;
        this.barcos_waiting = false;
        this.barco_crossing = false;
    }

    public void entra_carro() {
        this.lock.lock();
        while(this.barco_crossing) {
            try {
                this.available_carros.await();
            }
            catch(InterruptedException ignored) {
            }
        }
        this.carros_on_bridge++;
        this.lock.unlock();
    }

    public void sai_carro() {
        this.lock.lock();
        this.carros_on_bridge--;
        if(this.carros_on_bridge == 0 && this.barcos_waiting)
            this.available_barcos.signal();
        this.lock.unlock();
    }

   public void entra_barco() {
        this.lock.lock();
        try {
            Thread.sleep(5 * 60 * 1000);
        } catch(InterruptedException ignored) {
        }
        this.lock.lock();
        while(this.carros_on_bridge > 0 || this.barco_crossing) {
            try {
                this.available_barcos.await();
            } catch(InterruptedException ignored) {
            }
        }
        this.barco_crossing = true;
        this.lock.unlock();
   }

   public void sai_barco() {
        this.lock.lock();
        this.barco_crossing = false;
        this.available_carros.signalAll();
        this.lock.unlock();
   }
}
