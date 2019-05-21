package com.meeting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meeting.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User createUser(User user) {
		
		sessionFactory.getCurrentSession().save(user);
		return user;
		
	}

	@Override
	public User updateUser(User user) {
		
		sessionFactory.getCurrentSession().update(user);
		return user;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		String hql = "delete from user where user_id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong("id",  id);
		return (query.executeUpdate() > 0);
		
	}

	@Override
	public User findById(Long id) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userId", id));
		return (User) criteria.uniqueResult();
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return (List<User>) criteria.list();
		
	}
	
}
