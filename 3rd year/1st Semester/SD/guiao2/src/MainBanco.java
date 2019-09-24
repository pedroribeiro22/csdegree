public class MainBanco {

    public static void main(String[] args) throws Exception {
        Banco b = new Banco(10);
        int threads = 100000;
        Thread deposits[] = new Thread[threads];
        Thread withdrawls[] = new Thread[threads];
        for(int i = 0; i < threads; i++) {
            deposits[i] = new Thread(new Cliente1(2, b));
            withdrawls[i] = new Thread(new Cliente2(3, b));
            deposits[i].start();
            withdrawls[i].start();
        }

        for(int i = 0; i < threads; i++) {
            deposits[i].join();
            withdrawls[i].join();
        }

        System.out.println("Saldo conta 1: " + b.consultar(2));
        System.out.println("Saldo conta 2: " + b.consultar(3));

    }
}
