package abd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Workload {

    private static final int n = 10;
    private static final int clients = (int) Math.pow(2, Workload.n);
    private static final int products = (int) Math.pow(2, Workload.n);
    private static final int invoices = (int) Math.pow(2, Workload.n);

    public static void createTable(final Connection c,
                                   final String tableName, final List<String> fieldTypes, final List<String> fieldNames) {

        try {

            Statement s = c.createStatement();
            int values_count = fieldTypes.size();
            StringBuilder statement = new StringBuilder();
            statement.append("CREATE TABLE " + tableName + "(");
            for(int i = 0; i < fieldTypes.size(); i++) {

                if(i != (values_count - 1)) {
                   statement.append(fieldNames.get(i) + " " + fieldTypes.get(i) + ", ");
                } else {
                   statement.append(fieldNames.get(i) + " " + fieldTypes.get(i) + ");");
                }
            }

            s.executeUpdate(statement.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertValueIntoTable(final Connection c, final String table, final List<String> values) {

        try {

            Statement s = c.createStatement();
            int values_count = values.size();
            StringBuilder statement = new StringBuilder();
            statement.append("INSERT INTO " + table + " VALUES (");
            for(int i = 0; i < values_count; i++) {

                if(i != (values_count - 1)) { // If the value isn't the last one
                    statement.append(values.get(i) + ", ");
                } else { // If it is the last value
                    statement.append(values.get(i) + ");");
                }
            }
            s.executeUpdate(statement.toString());

        } catch(SQLException e) {

            e.printStackTrace();

        }

    }

    public static void populate(Random rand, Connection c) throws Exception {

        // Create the necessary tables
        // Clients table
        createTable(
                c,
                "client",
                new ArrayList<>(Arrays.asList("int", "varchar", "varchar", "varchar")),
                new ArrayList<>(Arrays.asList("id", "name", "address", "data"))
        );

        // Products table
        createTable(
                c,
                "product",
                new ArrayList<>(Arrays.asList("int", "varchar", "varchar")),
                new ArrayList<>(Arrays.asList("id", "description", "data"))
        );

        // Invoices table
        createTable(
                c,
                "invoice",
                new ArrayList<>(Arrays.asList("int", "int", "int", "varchar")),
                new ArrayList<>(Arrays.asList("id", "product_id", "client_id", "data"))
        );

        // Populate clients
        for(int i = 0; i < Workload.clients; i++) {

            int client_id = i;
            String clientName = Handy.stringIt(Handy.randomString(20));
            String clientAddress = Handy.stringIt(Handy.randomString(20));
            String clientData = Handy.stringIt(Handy.randomString(1000));
            insertValueIntoTable(c, "client", new ArrayList<>(Arrays.asList(
                    String.valueOf(client_id),
                    clientName,
                    clientAddress,
                    clientData)));

        }

        // Populate products
        for(int i = 0; i < Workload.products; i++) {

            int product_id = i;
            String productDescription = Handy.stringIt(Handy.randomString(50));
            String productData = Handy.stringIt(Handy.randomString(1000));
            insertValueIntoTable(c, "product", new ArrayList<>(Arrays.asList(
                    String.valueOf(product_id),
                    productDescription,
                    productData)));

        }

        // Populate invoices
        for(int i = 0; i < Workload.invoices; i++) {

            int id = i;
            int product_id = rand.nextInt(Workload.products);
            int client_id = rand.nextInt(Workload.clients);
            String invoiceData = Handy.stringIt(Handy.randomString(1000));
            insertValueIntoTable(c, "invoice", new ArrayList<>(Arrays.asList(
                    String.valueOf(id),
                    String.valueOf(product_id),
                    String.valueOf(client_id),
                    invoiceData
            )));
        }

    }


    public static void sellProduct(Random rand, Connection c) throws Exception {

        // Creates a new invoice
        int invoice_id = rand.nextInt(Workload.invoices);
        int product_id = rand.nextInt(Workload.products);
        int client_id = rand.nextInt(Workload.clients);
        String invoice_data = Handy.stringIt(Handy.randomString(1000));
        insertValueIntoTable(c, "invoice", new ArrayList<>(Arrays.asList(
                String.valueOf(invoice_id),
                String.valueOf(product_id),
                String.valueOf(client_id),
                invoice_data
        )));

    }

    public static void productSoldToClient(int client_id, Connection c) {

        // SELECT invoice.id FROM invoice WHERE invoice.client_id = client_id;
        try {

            Statement s = c.createStatement();
            StringBuilder query = new StringBuilder("SELECT invoice.product_id FROM invoice WHERE invoice.client_id = ");
            query.append(String.valueOf(client_id) + ";");
            ResultSet rs = s.executeQuery(query.toString());
            // At the moment we are ignoring the result of the query as it is not important in this study

        } catch(SQLException e) {

            e.printStackTrace();

        }
    }

    public static void top10products(Connection c) {

        // Gets the 10 most sold products, according to the current invoices

        // SELECT product.id, COUNT(product.id) AS count FROM invoice
        //      GROUP BY product.id
        //      ORDER BY count
        //      LIMIT 10;

        try {

            Statement s = c.createStatement();
            StringBuilder query = new StringBuilder("SELECT invoice.product_id, COUNT(invoice.product_id) AS count FROM invoice\n");
            query.append("\tGROUP BY invoice.product_id\n");
            query.append("\tORDER BY count desc\n");
            query.append("\tLIMIT 10;");
            Statement getFromView = c.createStatement();
            StringBuilder n = new StringBuilder("SELECT * from NewTop10");
            ResultSet rs = s.executeQuery(n.toString());
            // At the moment we are ignoring the result of the query as it is not import in this study

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void transaction(Random rand, Connection c) throws Exception {

        int maximum = (int) Math.pow(2, Workload.n);

        Statement s = c.createStatement();

        for(int i = 0; i < maximum; i++) {

            // int transaction_id = rand.nextInt(3);
            int transaction_id = 3;

            switch(transaction_id) {

                case 1:
                    // Create an invoice entry (random product and random client)
                    sellProduct(rand, c);
                    break;
                case 2:
                    // Get all the product identifiers of the products that were sold to a specified client
                    int client_id = rand.nextInt(maximum);
                    productSoldToClient(client_id, c);
                    break;
                case 3:
                    // Discover the 10 most sold products, considering the current entries in the invoice table
                    top10products(c);
                    break;
                default:
                    break;

            }
        }

        s.close();
    }
}
