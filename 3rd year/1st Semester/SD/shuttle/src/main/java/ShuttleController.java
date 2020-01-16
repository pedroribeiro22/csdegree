import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ShuttleController implements Controlador {

    private ReentrantLock lock;
    private List<Condition> estacoes;
    private int posicao;
    private boolean travelling;
    private int capacity;
    private final int max_capacity;

    public ShuttleController() {
        this.lock = new ReentrantLock();
        this.estacoes = new ArrayList<Condition>();
        for(int i = 0; i < 4; i++)
            this.estacoes.add(this.lock.newCondition());
        this.posicao = 0;
        this.travelling = false;
        this.capacity = 0;
        this.max_capacity = 30;
    }

    public void requisita_viagem(int origem, int destino) {
        this.lock.lock();
        while(this.posicao != origem || this.travelling || this.capacity == this.max_capacity) {
           try {
                this.estacoes.get(destino).await();
           } catch (InterruptedException ignored) {
           }
        }
        this.capacity++;
        this.lock.unlock();
    }

    public void espera(int destino) {
        this.lock.lock();
        while(this.posicao != destino || this.travelling) {
            try {
                this.estacoes.get(destino).await();
            } catch (InterruptedException ignored) {
            }
        }
        this.capacity--;
        this.lock.unlock();
    }

    public void notifyPartida() {
       this.lock.lock();
       while(this.capacity < 10) {
           try {
               this.estacoes.get(this.posicao).await();
           } catch(InterruptedException ignored) {
           }
       }
       this.travelling = true;
       this.lock.unlock();
    }

    public void notifyChegada() {
       this.lock.lock();
       this.posicao = (this.posicao + 1) % 4;
       this.estacoes.get(posicao).signalAll();
       this.travelling = false;
       this.lock.unlock();
    }
}


