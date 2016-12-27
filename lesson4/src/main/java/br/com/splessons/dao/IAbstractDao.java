package br.com.splessons.dao;

import org.hibernate.Transaction;

public interface IAbstractDao<T> {
	void deleteAll();
	T findById(Long id);
	Transaction beginTransaction();
	void commitTransaction(Transaction transaction);
	void rollbackTransaction(Transaction transaction);
}