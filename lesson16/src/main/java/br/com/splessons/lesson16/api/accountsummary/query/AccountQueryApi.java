package br.com.splessons.lesson16.api.accountsummary.query;

import br.com.splessons.lesson16.domain.accountsummary.AccountSummary;
import br.com.splessons.lesson16.exception.NotFoundException;

public interface AccountQueryApi {

    AccountSummary fetchAccountSummary(String accountId) throws NotFoundException;
}
