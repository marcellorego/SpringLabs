package br.com.splessons.model;

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
public abstract class IdBasedEntity extends BaseEntity {

	private static final long serialVersionUID = 1866948616837656628L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}