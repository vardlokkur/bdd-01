package warlock.entities.domain;

/**
 * Card.
 * 
 * @author warlock
 */
public class Card {

    private Account account;
    private boolean valid;

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @return <code>true</code> if the card is valid, <code>false</code>
     *         otherwise
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the account.
     * 
     * @param account
     *            the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Defines if the card is valid.
     * 
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
