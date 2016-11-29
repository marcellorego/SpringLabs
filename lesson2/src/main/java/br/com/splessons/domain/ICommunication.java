package br.com.splessons.domain;

public interface ICommunication {
	void setMessaging(IMessaging messaging);
	void communicate();
	
	IEncryption getEncryption();
	void setEncryption(IEncryption encryption);
}