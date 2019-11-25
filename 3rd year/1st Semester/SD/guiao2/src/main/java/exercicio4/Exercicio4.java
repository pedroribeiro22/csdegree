package exercicio4;

import exercicio2.Banco;
import exercicio2.Depositers;
import exercicio2.Withdrawlers;

public class Exercicio4 {

    public static void main(String[] args) {

        // número de contas
        int n = 10;

        exercicio2.Banco b = new Banco(n);
        exercicio2.Depositers d = new Depositers(b);
        exercicio2.Withdrawlers w = new Withdrawlers(b);

        new Thread(d).start();
        new Thread(w).start();

        for(int i = 0; i < b.size(); i++)
            System.out.println(b.account_balance(i));
    }

}
