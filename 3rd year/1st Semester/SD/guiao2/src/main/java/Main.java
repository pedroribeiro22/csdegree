public class Main {

    public static void main(String[] args) {

        Banco b = new Banco(10);

        // Clientes (índices)
        int c1 = 1;
        int c2 = 2;

        // Depositar 1000 na conta 1
        b.depositar(1, 1000);
        System.out.println("A conta de índice " + c1 + "tem: " + b.consultar(c1));

        // Transferir 500 para a conta 2
        // Solução questionável, uma vez que bloqueamos o objeto `b` inteiro para fazer uma transferência
        synchronized (b) {

            b.levantar(1, 500);
            b.depositar(2, 500);

        }

        System.out.println("A conta de índice " + c1 + "tem: " + b.consultar(c1));
        System.out.println("A conta de índice " + c2 + "tem: " + b.consultar(c2));

    }
}
