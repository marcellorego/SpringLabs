package br.com.splessons.lesson16.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class AccountOwner implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return !StringUtils.isEmpty(name) ? String.format("%s", name) : null;
    }
}
