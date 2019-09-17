import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) {
        InputStreamReader leitor = new InputStreamReader(System.in);
        StringBuilder string = new StringBuilder();
        try {
           System.out.println(leitor.read());
        }

        catch(Exception e) {
           System.out.println(".i.");
        }
    }
}
