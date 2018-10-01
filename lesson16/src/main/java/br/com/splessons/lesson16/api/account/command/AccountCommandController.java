package br.com.splessons.lesson16.api.account.command;

import br.com.splessons.lesson16.domain.AccountOwner;
import br.com.splessons.lesson16.domain.account.command.CreateAccountCommand;
import br.com.splessons.lesson16.domain.account.command.DepositMoneyCommand;
import br.com.splessons.lesson16.domain.account.command.WithdrawMoneyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "${account.path}")
//, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
public class AccountCommandController implements AccountCommandApi {

    private final CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    @PostMapping
    public CompletableFuture<String> createAccount(@Valid @RequestBody AccountOwner user) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateAccountCommand(id, user));
    }

    @PutMapping(path = "{accountId}/deposit/{amount}")
    public CompletableFuture<String> depositMoney(
            @PathVariable("accountId") String accountId,
            @PathVariable("amount") double amount) {
        return commandGateway.send(new DepositMoneyCommand(accountId, amount));
    }


    @PutMapping(path = "{accountId}/withdraw/{amount}")
    public CompletableFuture<String> withdrawMoney(
            @PathVariable("accountId") String accountId,
            @PathVariable("amount") double amount) {
        return commandGateway.send(new WithdrawMoneyCommand(accountId, amount));
    }

    @ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(AggregateNotFoundException exception) {
        return exception.getMessage();
    }
}
