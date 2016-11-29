package br.com.splessons.domain.impl;

import java.util.concurrent.atomic.AtomicInteger;

import br.com.splessons.domain.ICommunication;
import br.com.splessons.domain.IEncryption;
import br.com.splessons.domain.IMessaging;

public class CommunicationImpl implements ICommunication {

	private static AtomicInteger atomic = new AtomicInteger();
	
	private IEncryption encryption;
	
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
		System.out.println(atomic.incrementAndGet() + "-" + encryption.encryptData(messaging.getMessage()) );
	}
}