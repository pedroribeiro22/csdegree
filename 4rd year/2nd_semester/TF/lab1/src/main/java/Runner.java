import java.util.Random;

public class Runner {
    
    public static void main(String[] args) throws Exception {

        MessageSender ms = new MessageSender(args, args.length, 1500);

        for(int i = 0; i < 1500; i++) {
            String operation = Runner.getRandomOperation();
            if(operation == "balance") {
                ms.balance(i);
            } else {
                Random rand = new Random();
                double amount =  (Math.round(50 * (-2.0 + rand.nextDouble() * 4.0)));
                ms.movement(i, amount);
            }
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