package exercicio2;

public class Incrementador implements Runnable {

    private Counter counter;
    private int increments;

    // Não interessa para o exercício este construtor
    public Incrementador(int increments) {
        this.counter = new Counter();
        this.increments = increments;
    }

    // Vamos usar este construtor, uma vez que o contador é partilhado
    public Incrementador(Counter c, int increments) {
        this.counter = c;
        this.increments = increments;
    }

    // O único objetivo dos objetos desta classe é incrementar o seu objeto `contador`
    public void run() {
        for(int i = 0; i < increments; i++) {
             this.counter.increment();
            // counter.counter++;
        }
    }
}
