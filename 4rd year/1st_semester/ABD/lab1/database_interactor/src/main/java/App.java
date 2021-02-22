import com.beust.jcommander.JCommander;

public class App {

    private static Options options = new Options();

    public static void main(String[] args) {

        JCommander parser = JCommander.newBuilder().addObject(options).build();

        try {

            parser.parse(args);

            if(options.help) {
                // Show help menu
                parser.usage();
                return; // End the program
            }

        } catch(Exception e) {

            // If any unknown options are used, display the help menu
            parser.usage();
            return;
        }

        System.out.println("Populate: " + options.populate);
        System.out.println("BD: " + options.database);
        System.out.println("User: " + options.username);
        System.out.println("Password: " + options.password);

        if(options.populate) {

            // TODO: Run the method that populates the database

            Populate p = new Populate(
                    options.database,
                    options.username,
                    options.password);

            p.populateClients(4);
            p.populateProducts(4);

        }

    }

}
