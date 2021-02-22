import com.beust.jcommander.Parameter;

public class Options {

    @Parameter(names = {"-h", "-?", "--help"}, help = true, description = "display usage information")
    public boolean help;

    @Parameter(names = {"-d", "--database"}, description = "JDBC database url")
    public String database = "jdbc:postgresql://localhost/abd"; // This value is taken by default

    @Parameter(names = {"-U", "--user"}, description = "database username")
    public String username = "abduser"; // This value is taken by default

    @Parameter(names = {"-P", "--password"}, description = "database password")
    public String password = "segredo";

    @Parameter(names = {"-p", "--populate"}, description = "populate the database")
    public boolean populate = true;
}
