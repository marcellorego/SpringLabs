package br.com.splessons.domain.impl;

import br.com.splessons.domain.IHelloWorld;

public class HelloWorldImpl implements IHelloWorld {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello World!");
	}
}