package br.com.splessons.lesson16.service.accountsummary;

import br.com.splessons.lesson16.domain.accountsummary.AccountSummary;
import br.com.splessons.lesson16.exception.NotFoundException;
import br.com.splessons.lesson16.repository.accountsummary.AccountSummaryRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountSummaryService {

    private final AccountSummaryRepository repository;

    public AccountSummaryService(AccountSummaryRepository repository) {
        this.repository = repository;
    }

    public AccountSummary fetchAccountSummary(String accountId) {
        AccountSummary summary = repository.findOne(accountId);
        if (summary == null) {
            throw new NotFoundException(accountId);
        }
        return summary;
    }
}
