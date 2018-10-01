package br.com.splessons.lesson16.domain.account.command;

import br.com.splessons.lesson16.BaseCommand;

public class DepositMoneyCommand extends BaseCommand<String> {

    private final double amount;

    public DepositMoneyCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
