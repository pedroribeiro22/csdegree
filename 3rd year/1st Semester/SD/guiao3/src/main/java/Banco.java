public class Banco {

    // Array de objetos do tipo `Conta`
    private Conta[] contas;

    // Construtor -> recebe o número de contas que o banco aloja.
    public Banco(int n) {

        this.contas = new Conta[n];

        for(int i = 0; i < n; i++)
            this.contas[i] = new Conta();

    }

    // Permite consultar o saldo da conta do índice `n`
    // Utiliza o método de consulta da classe `Conta`
    public double consultar(int n) {

        return this.contas[n].consultar();

    }

    // Permite depositor dinheiro na conta do índice `n`
    // Utiliza o método de depósito da classe `Conta`
    public void depositar(int n, float amount) {

        this.contas[n].depositar(amount);

    }

    // Permite levantar dinheiro da conta do índice `n`
    // Utiliza o método de levantamento da classe `Conta`
    public void levantar(int n, float amount) {

        this.contas[n].levantar(amount);

    }

    // Permite transferir dinheiro entre a conta `c1` e a conta `c2`
    // Nenhuma operação deve acontecer entre o levantamento da conta `c1` e o
    // depósito na conta `c2`.
    public void transferir(int c1, int c2, float amount) {

        // Descobrir qual deles é mais pequeno, para implementar aquela ordem para que não
        // existam Deadlocks.
        int lock1 = Math.min(c1, c2);
        int lock2 = Math.max(c1, c2);

        synchronized (this.contas[lock1]) {
            synchronized (this.contas[lock2]) {

                this.contas[c1].levantar(amount);
                this.contas[c2].depositar(amount);

            }
        }

    }
}