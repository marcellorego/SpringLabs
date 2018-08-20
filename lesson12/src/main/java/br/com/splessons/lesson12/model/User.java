package br.com.splessons.lesson12.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.splessons.lesson12.security.domain.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import br.com.splessons.lesson12.model.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER",
        uniqueConstraints = {
            @UniqueConstraint(name = "UK_USER_USERNAME", columnNames = {
                    "USERNAME"
            }),
            @UniqueConstraint(name = "UK_USER_EMAIL", columnNames = {
                    "EMAIL"
            }
        )
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends UserDateAudit implements UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID",
                    foreignKey = @ForeignKey(name = "FK_USER_ROLE_ROLE")),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID",
                    foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER")))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String username, String email, String password, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
