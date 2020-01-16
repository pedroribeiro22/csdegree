import sun.awt.datatransfer.DataTransferer;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControladorShuttle implements Controlador {

    private static int max = 30;
    private static int min = 10;
    private ReentrantLock lock;
    private List<Condition> paragens;
    private int inShuttle;
    private boolean travelling;
    private int currStation;

    public ControladorShuttle() {
        this.lock = new ReentrantLock(true);
        for(int i = 0; i < 4; i++) this.paragens.add(this.lock.newCondition());
        this.inShuttle = 0;
        this.travelling = false;
        this.currStation = 0;
    }

    public void requisita_viagem(int origem, int destino) {
        this.lock.lock();
        while(this.travelling || (this.currStation != origem) || this.inShuttle < min || this.inShuttle == max) {
            try {
                this.paragens.get(origem).await();
            } catch (InterruptedException ignored) {
            }
        }
        this.inShuttle++;
        this.lock.unlock();
    }

    public void espera(int destino) {
        this.lock.lock();
        while(this.travelling || (this.currStation != destino)) {
            try {
                this.paragens.get(destino).await();
            } catch (InterruptedException ignored) {
            }
        }
        this.inShuttle--;
        this.lock.unlock();
    }

    public void partir() {
        this.lock.lock();
        while(this.inShuttle < min) {
            try {
                this.paragens.get(this.currStation).await();
            } catch (InterruptedException ignored) {
            }
        }
        this.travelling = true;
        this.lock.unlock();

    }

    public void parar() {
        this.lock.lock();
        this.travelling = false;
        this.currStation = ((currStation) + 1) % 4;
        this.paragens.get(currStation).signalAll();
        this.lock.unlock();
    }
}
