package ex1;

public class Producer implements Runnable {

    private Warehouse wh;

    public Producer(Warehouse wh) {
        this.wh = wh;
    }

    public void run() {
        for(int i = 1; i <= 10; i++) {
            this.wh.supply("item" + i, 1);
            System.out.println("Meti-le um");
        }
    }
}
