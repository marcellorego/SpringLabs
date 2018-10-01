package br.com.splessons.lesson16.domain.account.command;

import br.com.splessons.lesson16.BaseCommand;
import br.com.splessons.lesson16.domain.AccountOwner;

public class CreateAccountCommand extends BaseCommand<String> {

    private final AccountOwner accountOwner;

    public CreateAccountCommand(String id, AccountOwner accountOwner) {
        super(id);
        this.accountOwner = accountOwner;
    }

    public AccountOwner getAccountOwner() {
        return accountOwner;
    }
}
