package br.com.splessons.lesson16.domain.account.event;

import br.com.splessons.lesson16.BaseEvent;

public class MoneyWithdrawnEvent extends BaseEvent<String> {

    private final double amount;

    public MoneyWithdrawnEvent(String id, double amount) {
        super(id);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
