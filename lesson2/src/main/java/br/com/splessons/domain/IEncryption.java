package br.com.splessons.domain;

public interface IEncryption {
	void setSalt(int salt);
	String encryptData(String value);
}