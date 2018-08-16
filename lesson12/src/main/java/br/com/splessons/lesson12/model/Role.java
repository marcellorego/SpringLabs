package br.com.splessons.lesson12.model;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 60)
    private String name;
}