package br.com.splessons.lesson16.repository.accountsummary;

import br.com.splessons.lesson16.domain.accountsummary.AccountSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountSummaryRepository extends MongoRepository<AccountSummary, String> {

}
