public class Banco {

    private Conta[] contas;

    public Banco(int n) {
        this.contas = new Conta[n];
        for(int i = 0; i < n; i++) {
            this.contas[i] = new Conta();
        }
    }

    public double consultar(int n) {
        return this.contas[n].consultar();
    }

    public void depositar(int n, float amount) {
        this.contas[n].depositar(amount);
    }

    public void levantar(int n, float amount) {
        this.contas[n].levantar(amount);
    }

    public void transferir(int c1, int c2, float amount) {
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
