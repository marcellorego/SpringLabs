package br.com.splessons.lesson16.domain.account.command;

import br.com.splessons.lesson16.BaseCommand;

public class WithdrawMoneyCommand extends BaseCommand<String> {

    private final double ammount;

    public WithdrawMoneyCommand(String id, double ammount) {
        super(id);
        this.ammount = ammount;
    }

    public double getAmmount() {
        return ammount;
    }
}
