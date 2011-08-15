package warlock.stories;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;

import warlock.entities.CardRetainedException;
import warlock.entities.InsufficientFundsException;
import warlock.entities.domain.ATM;
import warlock.entities.domain.Account;
import warlock.entities.domain.Card;

/**
 * Defines cash withdrawing steps.
 * 
 * @author warlock
 */
@Component
public class CashWithdrawingSteps {

    private Account account;
    private ATM atm;
    private Card card;
    private BigDecimal dispense;
    private Throwable throwable;

    /**
     * Callback method triggered before each scenario.
     */
    @BeforeScenario
    public void beforeScenario() {
        this.card = null;
        this.account = null;
        this.atm = null;
        this.dispense = null;
        this.throwable = null;
    }

    @Given("the card is disabled")
    public void givenCardIsDisabled() {
        card = new Card();
        card.setValid(false);

        atm = new ATM();
    }

    /**
     * @param balance
     *            the account's balance
     */
    @Given("the account balance is $balance and the card is valid and the machine contains enough money")
    public void givenValidCardAndAccount(@Named("balance") BigDecimal balance) {
        account = new Account();
        account.setBalance(balance);

        card = new Card();
        card.setValid(true);
        card.setAccount(account);

        atm = new ATM();
        atm.setBalance(balance);
    }

    @Then("the ATM should retain the card")
    public void thenATMShouldRetainCard() {
        Assert.assertNull(dispense);
        Assert.assertTrue(throwable instanceof CardRetainedException);
    }

    @Then("the ATM should dispense $amount and the account balance should be $balance and the card should be returned")
    public void thenDispensedSuccessfully(@Named("amount") BigDecimal amount, @Named("balance") BigDecimal balance) {
        Assert.assertNull(throwable);
        Assert.assertEquals(dispense, amount);
        Assert.assertEquals(account.getBalance(), balance);

    }

    @Then("the ATM should not dispense any money and the ATM should say there are insufficient funds and the account balance should be $balance and the card should be returned")
    public void thenInsufficientFundsShouldBeReported(@Named("balance") BigDecimal balance) {
        Assert.assertNull(dispense);
        Assert.assertTrue(throwable instanceof InsufficientFundsException);
        Assert.assertEquals(account.getBalance(), balance);
    }

    /**
     * @param amount
     *            the amount of requested money
     */
    @When("the account holder requests $amount")
    public void whenAccountHolderRequestsMoney(@Named("amount") BigDecimal amount) {
        try {
            dispense = atm.withdraw(card, amount);
        } catch (CardRetainedException exception) {
            throwable = exception;
        } catch (InsufficientFundsException exception) {
            throwable = exception;
        }
    }

}
