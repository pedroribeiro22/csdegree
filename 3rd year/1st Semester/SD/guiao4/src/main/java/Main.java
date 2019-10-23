public class Main {

    // Gerar um número aleatório entre `min`e `max`
    static int generate_random_number(int min, int max) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * max + min;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    // Classe `Produtor`
    // Povoa o BoundBuffer com números aleatórios entre 1 e 50
    public static class Produtor implements Runnable {

        private BoundedBuffer pbf;

        public void run() {
            int r;
            for(int i = 0; i < 20; i++) {
                r = generate_random_number(1, 20);
                try {
                    pbf.put(r);
                    System.out.println(pbf.print_buffer());
                }
                catch(Exception e) {
                }
            }
        }

        public Produtor(BoundedBuffer pbf) {
            this.pbf = pbf;
        }

    }

    // Classe `Consumidor`
    public static class Consumidor implements Runnable {

        private BoundedBuffer cbf;

        public void run() {
            int r;
            for(int i = 0; i < 20; i++) {
                try {

                   r = cbf.get();
                   System.out.println("Got: " + r);
                }
                catch(Exception e) {
                }
            }
        }

        public Consumidor(BoundedBuffer cbf) {
            this.cbf = cbf;
        }

    }

    public static void main(String[] args) {

        BoundedBuffer bf = new BoundedBuffer(10);

        Thread[] threads = new Thread[2];

        threads[0] = new Thread(new Produtor(bf));
        threads[1] = new Thread(new Consumidor(bf));

        threads[0].start();
        threads[1].start();

        for(int i = 0; i < 2; i++) {
            try {
                threads[i].join();
            }
            catch(Exception e) {
            }
        }
    }
}
