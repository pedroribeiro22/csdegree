package ex2;

public class Consumer implements Runnable {

    private Warehouse wh;

    public Consumer(Warehouse wh) {
        this.wh = wh;
    }


    public void run() {
       String[] items = {"item1", "item2", "item3"};
       for(int i = 0; i <= 10; i++) {
           this.wh.consume(items);
           System.out.println("Consumi-le um");
       }
    }
}
