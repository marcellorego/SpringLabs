package br.com.splessons.domain.impl;

import org.springframework.stereotype.Component;

import br.com.splessons.domain.IMessaging;

@Component("activeMqMessaging")
public class ActiveMQMessagingImpl implements IMessaging {

	@Override
	public String getMessage() {
		return "Sending Message via Active MQ";
	}
}