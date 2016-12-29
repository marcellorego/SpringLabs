package br.com.splessons.dao;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDao<T> {

	protected Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractHibernateDao() {
		super();

		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity) {
		getSession().persist(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		Session session = getSession();
		T result = (T) session.get(persistentClass, id);
		return result;
	}

	public void deleteAll() {
		getSession().createQuery("delete from " + persistentClass.getSimpleName().toUpperCase()).executeUpdate();
	}

	protected Criteria getCriteria(Class<T> clazz, String alias) {
		Criteria criteria = null;
		if (alias != null) {
			criteria = this.sessionCreateCriteria(clazz, alias);
		} else {
			criteria = this.sessionCreateCriteria(clazz);
		}
		return criteria;
	}

	protected Criteria sessionCreateCriteria(Class<T> clazz) {
		Session session = this.getSession();
		return session.createCriteria(clazz);
	}

	protected Criteria sessionCreateCriteria(Class<T> classeNegocio, String alias) {
		Session session = this.getSession();
		return session.createCriteria(classeNegocio, alias);
	}

	protected Criteria getDefaultCriteria(String alias) {
		Criteria criteria = this.getCriteria(this.persistentClass, alias);
		return criteria;
	}
}