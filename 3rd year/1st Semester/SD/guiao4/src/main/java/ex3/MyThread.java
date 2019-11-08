package ex3;

public class MyThread implements Runnable {

    private Barreira b;

    public MyThread(Barreira b) {
        this.b = b;
    }

    public void run() {

        try {
            b.esperar();
        }

        catch(Exception e) {
        }

    }
}
