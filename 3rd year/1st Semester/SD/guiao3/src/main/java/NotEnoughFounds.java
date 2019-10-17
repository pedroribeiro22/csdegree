public class NotEnoughFounds extends Exception {

    public NotEnoughFounds(double amount) {
        super("Not enough funds: " + amount + "€");
    }
}
