package br.com.splessons;

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
public class IdBasedEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}