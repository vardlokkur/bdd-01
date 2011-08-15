package warlock.lang;

/**
 * Thrown when the account has insufficient funds for the requested operation.
 * 
 * @author warlock
 */
public class InsufficientFundsException extends RuntimeException {

    /**
     * Construct new instance.
     */
    public InsufficientFundsException() {
        super();
    }

}
