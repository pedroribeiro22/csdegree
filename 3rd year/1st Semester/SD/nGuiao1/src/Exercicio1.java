public class Exercicio1 implements Runnable {

    public static int i = 5;  /* Até quanto cada thread deve contar */
    public static int j = 5;  /* Quantas threads deseja o utilizar */

    /* Trabalho que cada thread deve realizar */
    public void run() {
        for(int k = 1; k <= i; k++)
            System.out.println(k);
    }

    /* Exercício */
    public static void main(String[] args) {
        j = Integer.parseInt(args[0]);
        i = Integer.parseInt(args[1]);
        Thread[] threads = new Thread[j];
        for(int k = 0; k < j; k++) {
            threads[k] = new Thread(new Exercicio1());
            threads[k].start();
        }
        for(int k = 0; k < j; k++) {
           try {
               threads[k].join();
           }
           catch(Exception e) {
               System.out.println("Something went terribly wrong!");
           }
        }
    }
}
