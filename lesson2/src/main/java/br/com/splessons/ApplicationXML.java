package br.com.splessons;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.splessons.domain.ICommunication;
import br.com.splessons.domain.IHelloWorld;

public class ApplicationXML {

	private AbstractApplicationContext context;
	
	public ApplicationXML() {
		super();
		context = new ClassPathXmlApplicationContext("app-config.xml");
	}
	
	private void closeContext() {
		context.close();
	}
	
	public static void main(String args[]) {
        
		ApplicationXML instance = new ApplicationXML();
        try {
        	instance.sayHello();
        	instance.sendMessage();
        	instance.sendAutoWiredMessage();
        } finally {
        	instance.closeContext();
        }
    }
	
	private void sayHello() {
		IHelloWorld bean = (IHelloWorld) context.getBean("helloWorldBean");
    	bean.sayHello("Spring 4");		
	}
	
	private void sendMessage() {
		ICommunication app = (ICommunication) context.getBean("communication");
        app.communicate();
	}
	
	private void sendAutoWiredMessage() {
		ICommunication app = (ICommunication) context.getBean("communication2");
        app.communicate();
	}
	
}