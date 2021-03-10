import java.nio.charset.StandardCharsets;
import java.util.Random;

import Stubs.AsyncStub;

public class Runner {
    
    public static void main(String[] args) throws Exception {
        AsyncStub as = new AsyncStub(1500, args);
        runOperations(as, 1500);
    }

    private static void runOperations(AsyncStub as, int number) {
        System.out.println(number);
        if(number == 0) {
            as.sendAndReceiveAsync(number, "balance", 0).thenAccept(response -> {
                String responseString = new String(response, StandardCharsets.UTF_8);
                System.out.println("Final Balance: " + responseString);
            });
        } else {
            Random rand = new Random();
            String operation = getRandomOperation();
            int accountMovement = (int) (Math.round(50 * (-2.0 + rand.nextFloat() * 4.0)));
            as.sendAndReceiveAsync(number, operation, accountMovement).thenAccept(response -> {
                runOperations(as, number - 1);
            });
            runOperations(as, number - 1);
        }
    }

    public static String getRandomOperation() {
        Random random = new Random();
        int operation = random.nextInt(2);
        if(operation == 0) {
            return "balance";
        } else {
            return "movement";
        }
    }
}