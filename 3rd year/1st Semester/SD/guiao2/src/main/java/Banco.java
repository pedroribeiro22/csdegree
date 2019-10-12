public class Banco {

    // Array de contas do banco
    private double contas[];

    // Cria `n` contas
    public Banco(int n) {

       this.contas = new double[n];
       for(int i = 0; i < n; i++) contas[i] = 0;

    }

    // Consultar a conta de índice `n`
    public double consultar(int n) {

        return this.contas[n];

    }

    // Depositar `amount` na conta de índice `n`
    public void depositar(int n, float amount) {

        if(n >= 0 && n < this.contas.length) {

            synchronized (contas) {
                this.contas[n] += amount;
            }

        }
    }

    // Levantar `amount` da conta `n`
    public void levantar(int n, float amount) {

        if(n >= 0 && n < this.contas.length) {

            if(this.contas[n] - amount >= 0) {

                synchronized (contas) {
                   this.contas[n] -= amount;
                }
            }

        }

    }

    // Transferir `amount` entre `c1` e `c2`
    public void transferir(int c1, int c2, float amount) {

        if(this.contas[c1] - amount >= 0) {
            this.contas[c1] -= amount;
            this.contas[c2] += amount;
        }

    }

}

