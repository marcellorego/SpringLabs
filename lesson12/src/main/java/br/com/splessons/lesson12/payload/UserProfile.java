package br.com.splessons.lesson12.payload;

import br.com.splessons.lesson12.model.IdBased;
import br.com.splessons.lesson12.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserProfile extends User implements IdBased {

    @JsonIgnore
    public String getPassword() {
        return null;
    }
}
