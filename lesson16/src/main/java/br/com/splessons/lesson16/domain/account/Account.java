package br.com.splessons.lesson16.domain.account;

import br.com.splessons.lesson16.domain.AccountOwner;
import br.com.splessons.lesson16.domain.account.command.CreateAccountCommand;
import br.com.splessons.lesson16.domain.account.command.DepositMoneyCommand;
import br.com.splessons.lesson16.domain.account.command.WithdrawMoneyCommand;
import br.com.splessons.lesson16.domain.account.event.AccountCreatedEvent;
import br.com.splessons.lesson16.domain.account.event.MoneyDepositedEvent;
import br.com.splessons.lesson16.domain.account.event.MoneyWithdrawnEvent;
import br.com.splessons.lesson16.domain.account.exception.InsufficientBalanceException;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Aggregate
@NoArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @AggregateIdentifier
    private String id;

    private double balance;

    private AccountOwner owner;

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public AccountOwner getOwner() {
        return owner;
    }

    @CommandHandler
    public Account(CreateAccountCommand command) {
        String id = command.getId();
        AccountOwner owner = command.getAccountOwner();

        if (StringUtils.isEmpty(id)) throw new IllegalArgumentException("Missing id");
        if (StringUtils.isEmpty(owner)) throw new IllegalArgumentException("Missing account owner");

        AggregateLifecycle.apply(new AccountCreatedEvent(id, owner, 0));
    }

    @EventSourcingHandler
    public void onBankAccountCreatedEvent(AccountCreatedEvent event) {
        this.id = event.getId();
        this.owner = event.getAccountOwner();
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void onDepositMoneyCommand(DepositMoneyCommand command) {

        double ammount = command.getAmount();

        if (ammount <= 0.0) {
            throw new IllegalArgumentException("Deposit must be a positive number.");
        }

        AggregateLifecycle.apply(new MoneyDepositedEvent(command.getId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void onMoneyDepositedEvent(MoneyDepositedEvent event) {
        balance += event.getAmount();
    }

    @CommandHandler
    public void onWithdrawMoneyCommand(WithdrawMoneyCommand command) {

        double amount = command.getAmmount();

        if (amount <= 0.0) {
            throw new IllegalArgumentException("Withdraw must be a positive number.");
        }

        if(balance - amount < 0) {
            throw new InsufficientBalanceException(
                    String.format("Insufficient balance. Trying to withdraw: %.2f, but current balance is: %.2f", amount, balance));
        }

        AggregateLifecycle.apply(new MoneyWithdrawnEvent(id, amount));

    }

    @EventSourcingHandler
    public void onMoneyWithdrawnEvent(MoneyWithdrawnEvent event) {
        balance -= event.getAmount();
    }

}
