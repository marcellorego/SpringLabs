package br.com.splessons.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

@Data
@NonFinal
//@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper=false)
//@ToString(includeFieldNames=true)
@MappedSuperclass
public class IdBasedEntity implements Serializable {

	private static final long serialVersionUID = 1866948616837656628L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}