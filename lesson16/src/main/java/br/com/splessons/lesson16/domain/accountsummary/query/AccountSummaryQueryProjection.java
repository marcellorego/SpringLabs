package br.com.splessons.lesson16.domain.accountsummary.query;

import br.com.splessons.lesson16.domain.accountsummary.AccountSummary;
import br.com.splessons.lesson16.repository.accountsummary.AccountSummaryRepository;
import br.com.splessons.lesson16.domain.account.event.AccountCreatedEvent;
import br.com.splessons.lesson16.domain.account.event.MoneyDepositedEvent;
import br.com.splessons.lesson16.domain.account.event.MoneyWithdrawnEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountSummaryQueryProjection {

    private final AccountSummaryRepository repository;

    public AccountSummaryQueryProjection(AccountSummaryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void onAccountCreated(AccountCreatedEvent event) {
        AccountSummary summary = new AccountSummary(event.getId(), event.getBalance(), event.getBalance());
        this.repository.save(summary);
    }

    @EventHandler
    public void onAccountDeposit(MoneyDepositedEvent event) {

        AccountSummary summary = repository.findOne(event.getId());
        summary.increaseValueBalance(event.getAmount());
        repository.save(summary);
    }

    @EventHandler
    public void onAccountWithdrawn(MoneyWithdrawnEvent event) {

        AccountSummary summary = repository.findOne(event.getId());
        summary.deductValueBalance(event.getAmount());
        repository.save(summary);
    }
}
