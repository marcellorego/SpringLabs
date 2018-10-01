package br.com.splessons.lesson16.api.account.command;

import br.com.splessons.lesson16.domain.AccountOwner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandApi {

    CompletableFuture<String> createAccount(@RequestBody AccountOwner user);
    CompletableFuture<String> depositMoney(String accountId, double amount);
    CompletableFuture<String> withdrawMoney(String accountId, double amount);
}
