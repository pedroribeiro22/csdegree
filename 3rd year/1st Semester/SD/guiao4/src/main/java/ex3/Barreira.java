package ex3;

public class Barreira {

    private int N;
    private int counter;
    private int ronda = 0;

    public Barreira(int n) {
        this.N = n;
        this.counter = 0;
    }

    public synchronized void esperar()  throws InterruptedException {

        this.counter++;
        int my_ronda = this.ronda;

        while(this.counter < N && my_ronda == this.ronda) {
            this.wait();
        }

        if(counter == N) {
            this.notifyAll();
            this.counter = 0;
            this.ronda++;
            System.out.println("Desbloqueei");
        }
    }


}
