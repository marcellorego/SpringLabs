package br.com.splessons.dao;

public interface IAbstractDao<T> {
	void deleteAll();
	T findById(Long id);
}