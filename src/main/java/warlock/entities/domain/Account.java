package warlock.entities.domain;

import java.math.BigDecimal;

/**
 * Account.
 * 
 * @author warlock
 */
public class Account {

    private BigDecimal balance;

    /**
     * @return the account's balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the account's balance.
     * 
     * @param balance
     *            the account's balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
