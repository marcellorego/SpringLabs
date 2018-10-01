package br.com.splessons.lesson16.api.accountsummary.query;

import br.com.splessons.lesson16.domain.accountsummary.AccountSummary;
import br.com.splessons.lesson16.exception.NotFoundException;
import br.com.splessons.lesson16.service.accountsummary.AccountSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${account.path}/summary")
public class AccountSummaryQueryController implements AccountQueryApi {

    @Autowired
    private AccountSummaryService summaryService;

    @Override
    @GetMapping("/{id}")
    public AccountSummary fetchAccountSummary(@PathVariable("id") String accountId) throws NotFoundException {
        return summaryService.fetchAccountSummary(accountId);
    }
}
