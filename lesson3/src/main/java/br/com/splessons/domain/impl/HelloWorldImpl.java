package br.com.splessons.domain.impl;

import org.springframework.stereotype.Component;

import br.com.splessons.domain.IHelloWorld;

@Component("helloWorldBean")
public class HelloWorldImpl implements IHelloWorld {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello World!");
	}
}