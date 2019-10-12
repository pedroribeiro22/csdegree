public class Main {

    public static void main(String[] args) {

        // Cria um banco com 10 clientes
        Banco b = new Banco(10);

        // Depositar 1000 na conta de índice 0
        b.depositar(0, 1000);

        System.out.println("A conta do índice 0 tem: " + b.consultar(0));

        // Transferir 500€ para a conta de índice 1
        b.transferir(0, 1, 500);

        System.out.println("A conta de índice 0 tem: " + b.consultar(0));
        System.out.println("A conta de índice 1 tem: " + b.consultar(1));
    }
}