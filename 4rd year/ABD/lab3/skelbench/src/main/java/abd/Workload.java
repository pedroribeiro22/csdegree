package abd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Workload {

    private static class Product {

        int id;
        String description;
        int stock;
        int min;
        int max;

        Product(int id, String description, int stock, int min, int max) {
            this.id = id;
            this.description = description;
            this.stock = stock;
            this.min = min;
            this.max = max;
        }

    }

    private static final int n = 10;
    private static final int clients = (int) Math.pow(2, Workload.n);
    private static final int products = (int) Math.pow(2, Workload.n);
    private static final int invoices = (int) Math.pow(2, Workload.n);
    private static final int invoiceLines = (int) Math.pow(2, Workload.n);
    private static final int orders = (int) Math.pow(2, Workload.n);
    private static final List<String> suppliers = Workload.createSuppliers(n);
    private static final Map<Integer, Product> products_map = Workload.generateProducts((int) Math.pow(2, Workload.n));
    private final Random rand;
    private final Connection c;

    private static List<String> createSuppliers(int n) {
        List<String> suppliers = new ArrayList<>();
        for(int i = 0; i < n; i++) {
           suppliers.add(Handy.randomString(10));
        }
        return suppliers;
    }

    private static Map<Integer, Product> generateProducts(int n) {
        Map<Integer, Product> products = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String desc = Handy.stringIt(Handy.randomString(50));
            int min = 100;
            int max = 1000;
            int stock = (int) ((Math.random() * (max - min)) + min);
            products.put(i, new Product(i, desc, min, max, stock));
        }
        return products;
    }

    public Workload(Random rand, Connection c) throws Exception {
        this.rand = rand;
        this.c = c;

        //---- DEMO WORKLOAD ----
        // initialize connection, e.g. c.setAutoCommit(false);
        // or create prepared statements...
        //-----------------------
    }

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
                new ArrayList<>(Arrays.asList("int", "varchar", "varchar")),
                new ArrayList<>(Arrays.asList("id", "name", "address"))
        );

        // Products table
        createTable(
                c,
                "product",
                new ArrayList<>(Arrays.asList("int", "varchar", "int", "int", "int")),
                new ArrayList<>(Arrays.asList("id", "description", "stock", "min", "max"))
        );

        // Invoices table
        createTable(
                c,
                "invoice",
                new ArrayList<>(Arrays.asList("int", "int")),
                new ArrayList<>(Arrays.asList("id", "client_id"))
        );

        // InvoiceLine table
        createTable(
                c,
                "invoiceLine",
                new ArrayList<>(Arrays.asList("int", "int", "int")),
                new ArrayList<>(Arrays.asList("id", "invoice_id", "product_id"))
        );

        // Orders table
        createTable(
                c,
                "orders",
                new ArrayList<>(Arrays.asList("int", "int", "varchar", "int")),
                new ArrayList<>(Arrays.asList("id", "product_id", "supplier", "items"))
        );

        // Populate clients
        for(int i = 0; i < Workload.clients; i++) {

            int client_id = i;
            String clientName = Handy.stringIt(Handy.randomString(20));
            String clientAddress = Handy.stringIt(Handy.randomString(20));
            insertValueIntoTable(c, "client", new ArrayList<>(Arrays.asList(
                    String.valueOf(client_id),
                    clientName,
                    clientAddress)));

        }

        // Populate products
        for(int i = 0; i < Workload.products; i++) {

            Product p = Workload.products_map.get(i);
            insertValueIntoTable(c, "product", new ArrayList<>(Arrays.asList(
                        String.valueOf(p.id),
                        p.description,
                        String.valueOf(p.stock),
                        String.valueOf(p.min),
                        String.valueOf(p.min))));
        }

        // Populate invoices
        for(int i = 0; i < Workload.invoices; i++) {
            int id = i;
            int client_id = rand.nextInt(Workload.clients);
            insertValueIntoTable(c, "invoice", new ArrayList<>(Arrays.asList(
                    String.valueOf(id),
                    String.valueOf(client_id)
            )));
        }

        // Populate InvoiceLine
        for(int i = 0; i < Workload.invoiceLines; i++) {
            int id = i;
            int invoice_id = rand.nextInt(Workload.invoices);
            int product_id = rand.nextInt(Workload.products);
            insertValueIntoTable(c, "invoiceLine", new ArrayList<>(Arrays.asList(
                    String.valueOf(id),
                    String.valueOf(invoice_id),
                    String.valueOf(product_id)
            )));
        }

        // Populate Orders
        for(int i = 0; i < Workload.orders; i++) {
            int id = i;
            int product_id = rand.nextInt(Workload.products);
            Product p = Workload.products_map.get(i);
            String supplier = Workload.suppliers.get(rand.nextInt(Workload.n));
            int items = p.max - p.stock;
            insertValueIntoTable(c, "orders", new ArrayList<>(Arrays.asList(
                    String.valueOf(id),
                    String.valueOf(product_id),
                    Handy.stringIt(supplier),
                    String.valueOf(items)
            )));
        }

    }


    public void transaction() throws Exception {
        Statement s = c.createStatement();

        //---- DEMO WORKLOAD ----
        // replace with your workload!
        switch(rand.nextInt(2)) {
            case 0:
                s.executeUpdate("update demo set c=c+1 where a=1");
                break;
            case 1:
                ResultSet rs = s.executeQuery("select * from demo");
                while(rs.next())
                    ;
                break;
        }
        //-----------------------

        s.close();
    }
}
