Narrative: 
In order to get money when the bank is closed
As a Account Holder
I want to withdraw cash from an ATM
 
Scenario: Account has sufficient funds
Given the account balance is 100
    and the card is valid
    and the machine contains enough money
When the account holder requests 20
Then the ATM should dispense 20
    and the account balance should be 80
    and the card should be returned
    
Scenario: Account has insufficient funds
Given the account balance is 10
    and the card is valid
    and the machine contains enough money
When the account holder requests 20
Then the ATM should not dispense any money
    and the ATM should say there are insufficient funds
    and the account balance should be 10
    and the card should be returned
 
Scenario: Card has been disabled
Given the card is disabled
When the account holder requests 20
Then the ATM should retain the card   
 