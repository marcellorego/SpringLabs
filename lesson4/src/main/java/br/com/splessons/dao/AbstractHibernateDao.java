package br.com.splessons.dao;

import java.lang.reflect.ParameterizedType;

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
    	
    	this.persistentClass = (Class<T>)
		   ((ParameterizedType)getClass().getGenericSuperclass())
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
}