package br.com.splessons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.com.splessons.model.IdBasedEntity;

public abstract class BaseImplService<T extends IdBasedEntity> {
	
	@Autowired
    protected PlatformTransactionManager transactionManager;
	
	/*@Autowired
	protected TransactionTemplate transactionTemplate;*/
	
	protected TransactionDefinition getDefaultTransactionDefinition() {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be done programmatically
		def.setName("BaseImplServiceTransaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		
		return def;
	}
}
