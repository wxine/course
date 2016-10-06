package com.wxine.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wxine.domain.User;

@Component		
@Transactional	
public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);	//获得Hibernate Session
	}
	
	public void save(User user) {
		getSession().save(user);
	}
	
	public User get(String id){
		return getSession().get(User.class, id);
	}
	
	public  List<User> findAll() {
		List<User> user =  getSession().createCriteria(User.class).list();
		return user;
	}
	
	public User findById(User user){
		return getSession().find(User.class, user.getId());
	}

	
	public void delete(String id){
		getSession().delete(get(id));
	}
	
	public void update(User user){
		getSession().update(user);
	}
	
	
}
