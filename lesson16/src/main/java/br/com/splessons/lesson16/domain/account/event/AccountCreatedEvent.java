package br.com.splessons.lesson16.domain.account.event;

import br.com.splessons.lesson16.BaseEvent;
import br.com.splessons.lesson16.domain.AccountOwner;

public class AccountCreatedEvent extends BaseEvent<String> {

    private final AccountOwner accountOwner;
    private final double balance;

    public AccountCreatedEvent(String id, AccountOwner accountOwner, double balance) {
        super(id);
        this.accountOwner = accountOwner;
        this.balance = balance;
    }

    public AccountOwner getAccountOwner() {
        return accountOwner;
    }

    public double getBalance() {
        return balance;
    }
}
