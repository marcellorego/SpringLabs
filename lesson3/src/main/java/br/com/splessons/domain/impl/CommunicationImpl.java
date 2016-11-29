package br.com.splessons.domain.impl;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.splessons.domain.ICommunication;
import br.com.splessons.domain.IEncryption;
import br.com.splessons.domain.IMessaging;

@Component("communication")
public class CommunicationImpl implements ICommunication {

	private static AtomicInteger atomic = new AtomicInteger();
	
	@Resource(name="hexEncryption")
	private IEncryption encryption;
	
	@Resource(name="asciiEncryption")
	private IEncryption decryption;
	
	@Autowired
	private IMessaging messaging;
    
	public CommunicationImpl() {
		super();
	}
	
	public CommunicationImpl(IEncryption value) {
		super();
		this.encryption = value;
	}
	
	public IEncryption getEncryption() {
		return encryption;
	}

	public void setEncryption(IEncryption encryption) {
		this.encryption = encryption;
	}	
	
	public void setMessaging(IMessaging messaging){
		this.messaging = messaging;
	}
	
	public void communicate(){
		String msg = encryption.encryptData(messaging.getMessage());
		System.out.println(atomic.incrementAndGet() + "-" + msg);
		System.out.println(atomic.incrementAndGet() + "-" + decryption.encryptData(msg));
	}
}