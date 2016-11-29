package br.com.splessons.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import br.com.splessons.domain.ICommunication;
import br.com.splessons.domain.IEncryption;
import br.com.splessons.domain.IHelloWorld;
import br.com.splessons.domain.IMessaging;
import br.com.splessons.domain.impl.ActiveMQMessagingImpl;
import br.com.splessons.domain.impl.CommunicationImpl;
import br.com.splessons.domain.impl.HelloWorldImpl;
import br.com.splessons.domain.impl.HexEncryptionImpl;

@Configuration
public class ApplicationConfiguration {
	
	@Bean(name = "helloWorldBean")
    public IHelloWorld helloWorldBean() {
        IHelloWorld hello = new HelloWorldImpl();
        return hello;
    }
	
	@Bean(name = "communication")
    public ICommunication communication() {
        ICommunication communication = new CommunicationImpl(encryption());
        communication.setMessaging(messaging());
        return communication;
    }
 
    @Bean(name = "encryption")
    @Description("This bean will be injected via setter injection")
    public IEncryption encryption() {
        IEncryption result = new HexEncryptionImpl();
        result.setSalt(10);
        return result;
    }
 
    @Bean(name = "messaging")
    @Description("This bean will be injected via setter injection")
    public IMessaging messaging() {
        return new ActiveMQMessagingImpl();
    }
    
    /*@Bean(name = "communication2")
    public ICommunication communication2() {
        ICommunication communication = new CommunicationImpl(encryption());
        communication.setMessaging(messaging());
        return communication;
    }*/
}