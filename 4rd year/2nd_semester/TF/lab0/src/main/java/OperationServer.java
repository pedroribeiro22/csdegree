import BankImplementations.Bank;
import BankImplementations.BankNaive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OperationServer {

    private static final int port = 12345;
    private ServerSocket server;

    public static void main(String[] args) {
        Bank banco = new BankNaive();
       new OperationServer().launch(banco);
    }

    public void launch(Bank banco) {

        try {
            this.server = new ServerSocket(this.port);

            while(true) {

                Socket socket = this.server.accept();
                System.out.println("Someone has connected. Here's the info: ");
                System.out.println("Address: " + socket.getLocalAddress() + "Port: " + socket.getPort());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line = null;

                while((line = in.readLine()) != null) {

                    String[] arguments = line.split(" ");
                    switch(arguments[0]) {
                        case "balance":
                            double balance = banco.balance();
                            System.out.println("Saldo:" + balance);
                            break;
                        case "movement":
                            if(arguments.length != 2) System.out.println("O número de argumentos fornecidos foi diferente de 2");
                            double amount = Double.parseDouble(arguments[1]);
                            boolean completed = banco.movement(amount);
                            if(completed) System.out.println("Operação completa!");
                            else System.out.println("Não foi possível completar a operação");
                            break;
                    }
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
