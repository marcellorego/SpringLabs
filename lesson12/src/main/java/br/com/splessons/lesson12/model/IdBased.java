package br.com.splessons.lesson12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IdBased {

    @JsonIgnore
    Long getId();
}
