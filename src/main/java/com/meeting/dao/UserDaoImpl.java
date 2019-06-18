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
	public User create(User user) {
		
		sessionFactory.getCurrentSession().save(user);
		return user;
		
	}

	@Override
	public User update(User user) {
		
		sessionFactory.getCurrentSession().update(user);
		return user;
		
	}

	@Override
	public int deleteById(Long id) {
		
		String hql = "delete User where user_id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		return query.executeUpdate();
		
	}

	@Override
	public User findById(Long id) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userId", id));
		return (User) criteria.uniqueResult();
		
	}

	@Override
	public User findByEmail(String email) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
		
	}
	
	@Override
	public User findByLogin(String username, String password) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", username));
		criteria.add(Restrictions.eq("password", password));
		return (User) criteria.uniqueResult();
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return (List<User>) criteria.list();
		
	}
	
}
