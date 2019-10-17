public class Main {

    public static void main(String[] args) {

        Bank b = new Bank();

        createAccounts(b, 10);

        try {

            b.transfer(1, 3, 100);

        }

        catch(InvalidAccount | NotEnoughFounds e) {

            System.out.println(e.getMessage());

        }

        showClientBalance(b, 1);
        showClientBalance(b, 3);

    }


    // Cria `n` contas com saldo inicial de 1000 €
    private static void createAccounts(Bank b, int n) {

        for(int i = 0; i < n; i++)
            b.createAccount(1000);

    }

    // Mostra o saldo de um cliente
    private static void showClientBalance(Bank b, int client) {

        System.out.println("O cliente " + client + " tem " + b.getClientBalance(client) + " €.");

    }
}
