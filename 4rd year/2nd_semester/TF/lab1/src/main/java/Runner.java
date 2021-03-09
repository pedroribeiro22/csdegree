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
            double balance = ms.balance(number);
            System.out.println("Final Balance: " + balance);
        } else {
            Random rand = new Random();
            String operation = getRandomOperation();
            int accountMovement = (int) (Math.round(50 * (-2.0 + rand.nextFloat() * 4.0)));
            if(operation == "movement") {
                ms.movement(number, accountMovement);
            } else {
                ms.balance(number);
            }
            runOperations(as, number - 1);
        }
    }

    public static String getRandomOperation() {
        Random random = new Random();
        int operation = random.ints(0, 1).findFirst().getAsInt();
        if(operation == 0) {
            return "balance";
        } else {
            return "movement";
        }
    }
}