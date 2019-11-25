package exercicio2;

public class Exercicio2 {

    public static void main(String[] args) {

        // número de contas
        int n = 10;

        Banco b = new Banco(n);
        Depositers d = new Depositers(b);
        Withdrawlers w = new Withdrawlers(b);

        new Thread(d).start();
        new Thread(w).start();

        for(int i = 0; i < b.size(); i++)
            System.out.println(b.account_balance(i));
    }
}
