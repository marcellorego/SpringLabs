package br.com.splessons;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.NonFinal;

@Data
@NonFinal
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=false)
@MappedSuperclass
public abstract class IdBasedEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="SEQUENCE")
    private Long id;
}