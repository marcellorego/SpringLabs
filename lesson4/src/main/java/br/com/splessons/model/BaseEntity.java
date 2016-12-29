package br.com.splessons.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 8870430728342379428L;
}