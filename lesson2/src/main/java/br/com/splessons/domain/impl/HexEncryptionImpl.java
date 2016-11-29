package br.com.splessons.domain.impl;

import br.com.splessons.domain.IEncryption;

public class HexEncryptionImpl implements IEncryption {

	private int salt;
	
	@Override
	public void setSalt(int salt) {
		this.salt = salt;
	}
	
	@Override
	public String encryptData(String value) {
		int charInt;
		char[] chars = value.toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	    	charInt = (int) chars[i];
	    	charInt += this.salt + charInt;
	        hex.append(Integer.toHexString(charInt));
	    }
	    return hex.toString();
	}
}