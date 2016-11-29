package br.com.splessons.domain.impl;

import org.springframework.stereotype.Component;

import br.com.splessons.domain.IEncryption;

@Component("asciiEncryption")
public class AsciiEncryptionImpl implements IEncryption {

	private int salt;
	
	@Override
	public void setSalt(int salt) {
		this.salt = salt;
	}
	
	@Override
	public String encryptData(String value) {
		int charInt;
		String[] chars = value.split("(?<=\\G...)");
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	    	charInt = Integer.parseInt(chars[i], 16);
	    	charInt += charInt - this.salt;
	        hex.append(String.valueOf(charInt));
	    }
	    return hex.toString();
	}
}