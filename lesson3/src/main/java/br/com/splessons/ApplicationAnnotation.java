package br.com.splessons;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.com.splessons.configuration.ApplicationConfiguration;
import br.com.splessons.domain.ICommunication;
import br.com.splessons.domain.IHelloWorld;

public class ApplicationAnnotation {

	private AbstractApplicationContext context;
	
	public ApplicationAnnotation() {
		super();
		context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
	}
	
	private void closeContext() {
		context.close();
	}
	
	public static void main(String args[]) {
        
		ApplicationAnnotation instance = new ApplicationAnnotation();
        try {
        	instance.sayHello();
        	instance.sendMessage();
        	instance.sendMessageQualified();
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
	
	private void sendMessageQualified() {
		ICommunication app = (ICommunication) context.getBean("communicationQualified");
        app.communicate();
	}
}