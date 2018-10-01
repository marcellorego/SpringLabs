package br.com.splessons.lesson16.domain.account.event;

import br.com.splessons.lesson16.BaseEvent;

public class MoneyDepositedEvent extends BaseEvent<String> {

    private final double amount;

    public MoneyDepositedEvent(String id, double amount) {
        super(id);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
