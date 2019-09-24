public class Banco {

    private double contas[];

    public Banco(int n) {
        this.contas = new double[n];
        for(int i = 0; i < n; i++) contas[i] = 0;
    }

    public double consultar(int n) {
        return this.contas[n];
    }

    public void depositar(int n, double value) {
        if(n >= 0 || n < this.contas.length) {
            synchronized (contas) {
                this.contas[n] += value;
            }
        }
    }

    public void levantar(int n, double value) {
        if(this.contas[n] - value < 0);
        else {
            synchronized (contas) {
                this.contas[n] -= value;
            }
        }
    }

    public void transferir(int c1, int c2, double value) {
        if(this.contas[c1] >= value) {
                this.contas[c1] -= value;
                this.contas[c2] += value;
        }
    }
}
