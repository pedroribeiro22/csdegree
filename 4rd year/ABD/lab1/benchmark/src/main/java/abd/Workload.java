package abd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

public class Workload {

    private static final int n = 10;

    public static void populate(Random rand, Connection c) throws Exception {

        String clientName = "gertrudes";
        String clientAddress = "Rua do Caires";
        Date clientDate = new Date(2020, 18, 10);

        String productName = "Arroz";
        String productDescription = "É de comer";
        Date productDate = new Date(2020, 18, 10);

        Statement s = c.createStatement();

        int maximum = (int) Math.pow(2, 10);

        // Criar tabelas e inserir valores
        for(int i = 0; i < maximum; i++) {

        }

        s.close();
    }

    public static void transaction(Random rand, Connection c) throws Exception {

        Statement s = c.createStatement();

        // Executar operação

        s.close();
    }
}
