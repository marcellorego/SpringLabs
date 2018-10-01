package br.com.splessons.lesson16;

import br.com.splessons.lesson16.domain.AccountOwner;
import br.com.splessons.lesson16.domain.account.Account;
import br.com.splessons.lesson16.domain.account.command.CreateAccountCommand;
import br.com.splessons.lesson16.domain.account.command.DepositMoneyCommand;
import br.com.splessons.lesson16.domain.account.command.WithdrawMoneyCommand;
import br.com.splessons.lesson16.domain.account.event.AccountCreatedEvent;
import br.com.splessons.lesson16.domain.account.event.MoneyDepositedEvent;
import br.com.splessons.lesson16.domain.account.event.MoneyWithdrawnEvent;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.eventsourcing.eventstore.EventStoreException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountTest {

    private static String ACCOUNT_ID = UUID.randomUUID().toString();
    private static AccountOwner ACCOUNT_OWNER = new AccountOwner(0L, "John");

    private FixtureConfiguration<Account> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Account.class);
    }

    @Test
    public void createAndDeposit() {
        Account account = new Account();
        account.onBankAccountCreatedEvent(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0.0));
        account.onMoneyDepositedEvent(new MoneyDepositedEvent(ACCOUNT_ID, 10.0));

        Assert.assertEquals(10.0, account.getBalance(), 0.0);
    }


    @Test
    public void testCreateAccountCommand() {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand(ACCOUNT_ID, ACCOUNT_OWNER))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0));
    }

    @Test
    public void testCreateExistingAccountCommand() {
        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0.0))
                .when(new CreateAccountCommand(ACCOUNT_ID, ACCOUNT_OWNER))
                .expectException(EventStoreException.class);
    }

    @Test
    public void testDepositMoneyValidCommand() {
        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0))
                .when(new DepositMoneyCommand(ACCOUNT_ID, 1000))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new MoneyDepositedEvent(ACCOUNT_ID, 1000));
    }

    @Test
    public void testDepositMoneyInvalidCommand() {
        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0))
                .when(new DepositMoneyCommand(ACCOUNT_ID, 0))
                .expectNoEvents()
                .expectException(IllegalArgumentException.class);

        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0))
                .when(new DepositMoneyCommand(ACCOUNT_ID, -1000))
                .expectNoEvents()
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testDepositMoneyOnInexistentAccount() {
        fixture.given()
                .when(new DepositMoneyCommand(ACCOUNT_ID, 12.0))
                .expectException(AggregateNotFoundException.class);
    }

    @Test
    public void testWithdrawMoneyValidCommand() {

        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, ACCOUNT_OWNER, 0),
                new MoneyDepositedEvent(ACCOUNT_ID, 10.0))
                .when(new WithdrawMoneyCommand(ACCOUNT_ID, 2.0))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new MoneyWithdrawnEvent(ACCOUNT_ID, 2.0))
                .expectState(account -> Assert.assertEquals(8.0, account.getBalance(), 0.0));
    }

}
