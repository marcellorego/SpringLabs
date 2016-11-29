package br.com.splessons.domain.impl;

import br.com.splessons.domain.IMessaging;

public class ActiveMQMessagingImpl implements IMessaging {

	@Override
	public String getMessage() {
		return "Sending Message via Active MQ";
	}
}