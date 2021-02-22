import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Populate {

    private Connection connection;

    public Populate(final String database_url, final String database_user, final String database_password) {

        try {

            this.connection = DriverManager.getConnection(
                    database_url,
                    database_user,
                    database_password
            );

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    // This method assumes that the object is already passed in it's String form
    public void insertValueIntoTable(final String table, final List<String> values) {

        try {

            Statement s = this.connection.createStatement();
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

    public void populateClients(final int n) {

        int maximum = (int) Math.pow(2, n);

        Random rand = new Random();

        for(int i = 0; i < maximum; i++) {

            int clientId = rand.nextInt(maximum);
            String clientName = Handy.stringIt(Handy.randomString(20));
            String clientAddress = Handy.stringIt(Handy.randomString(20));
            String clientData = Handy.stringIt(Handy.randomString(1000));
            insertValueIntoTable("client", new ArrayList<>(Arrays.asList(
                    String.valueOf(clientId),
                    clientName,
                    clientAddress,
                    clientData)));
        }

    }

    public void populateProducts(final int n) {

        int maximum = (int) Math.pow(2, n);

        Random rand = new Random();

        for(int i = 0; i < maximum; i++) {

            int productId = rand.nextInt(maximum);
            String productDescription = Handy.stringIt(Handy.randomString(50));
            String productData = Handy.stringIt(Handy.randomString(1000));
            insertValueIntoTable("product", new ArrayList<>(Arrays.asList(
                    String.valueOf(productId),
                    productDescription,
                    productData
            )));

        }
    }


}
