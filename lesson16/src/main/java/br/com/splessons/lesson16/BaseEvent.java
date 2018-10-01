package br.com.splessons.lesson16;

import org.springframework.util.Assert;

import java.io.Serializable;

public class BaseEvent<T> implements Serializable {

    private final T id;

    public BaseEvent(T id) {
        Assert.notNull(id, "Id cannot be null");
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
