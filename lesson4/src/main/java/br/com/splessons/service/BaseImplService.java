package br.com.splessons.service;

import javax.annotation.Resource;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.com.splessons.model.IdBasedEntity;

public abstract class BaseImplService<T extends IdBasedEntity> {
	
	@Resource
    protected PlatformTransactionManager transactionManager;
	
	protected TransactionDefinition getDefaultTransactionDefinition() {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be done programmatically
		def.setName("BaseImplServiceTransaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		
		return def;
	}
}
