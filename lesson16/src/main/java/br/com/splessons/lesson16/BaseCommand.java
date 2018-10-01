package br.com.splessons.lesson16;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.springframework.util.Assert;

import java.io.Serializable;

public class BaseCommand<T> implements Serializable {

    @TargetAggregateIdentifier
    private final T id;

    public BaseCommand(T id) {
        Assert.notNull(id, "Id cannot be null");
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
