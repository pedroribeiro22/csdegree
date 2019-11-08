package ex3;

public class Main {

    public static void main(String[] args) {

        Barreira b = new Barreira(10);

        for(int i = 0; i < 100; i++) {
            Thread t = new Thread(new MyThread(b));
        }

    }
}