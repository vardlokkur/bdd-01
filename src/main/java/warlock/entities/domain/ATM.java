package warlock.entities.domain;

import java.math.BigDecimal;

import warlock.lang.CardRetainedException;
import warlock.lang.InsufficientFundsException;

/**
 * ATM.
 * 
 * @author warlock
 */
public class ATM {

    private BigDecimal balance;

    /**
     * @return the ATM's balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the ATM's balance.
     * 
     * @param balance
     *            the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Withdraws given <code>amount</code> of cash using specified
     * <code>card</code>.
     * 
     * @param card
     *            the card
     * @param amount
     *            the amount
     * @return dispensed amount of cash
     * @throws CardRetainedException
     *             if given card is invalid
     * @throws InsufficientFundsException
     *             if the card's account has insufficient funds for the
     *             requested operation
     */
    public BigDecimal withdraw(Card card, BigDecimal amount) throws CardRetainedException, InsufficientFundsException {
        if (!card.isValid()) {
            throw new CardRetainedException();
        }
        Account account = card.getAccount();
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        account.setBalance(account.getBalance().subtract(amount));

        this.balance = this.balance.subtract(amount);
        return amount;
    }

}
