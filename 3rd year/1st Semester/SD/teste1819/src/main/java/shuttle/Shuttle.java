package shuttle;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Shuttle implements Controlador {

    private ReentrantLock lock;
    private List<Condition> estacoes;
    private boolean travelling;
    private int posicao;
    private int ocupacao;
    private int max_capacity;

    public Shuttle() {
        this.lock = new ReentrantLock();
        this.estacoes = new ArrayList<>();
        for(int i = 0; i < 4; i++) this.estacoes.add(this.lock.newCondition());
        this.travelling = false;
        this.posicao = 0;
        this.ocupacao = 0;
        this.max_capacity = 30;

    }
    public void requisita_viagem(int origem, int destino) {
        this.lock.lock();
        while(this.posicao != origem || this.travelling || this.ocupacao == this.max_capacity) {
            try {
                this.estacoes.get(origem).await();
            }
            catch (InterruptedException ignored) {
            }
        }
        this.ocupacao++;
        this.lock.unlock();
    }

    public void espera(int destino) {
        this.lock.lock();
        while(this.posicao != destino || this.travelling) {
            try {
                this.estacoes.get(destino).await();
            } catch(InterruptedException e) {
            }
        }
        this.ocupacao--;
        this.lock.unlock();
    }
}
