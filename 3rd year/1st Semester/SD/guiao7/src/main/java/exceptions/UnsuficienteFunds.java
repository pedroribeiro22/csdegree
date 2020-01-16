package exceptions;

public class UnsuficienteFunds extends Exception {

    public UnsuficienteFunds(final int account_id) {
        super("Account " + account_id + "enough funds to performe this action.");
    }
}
