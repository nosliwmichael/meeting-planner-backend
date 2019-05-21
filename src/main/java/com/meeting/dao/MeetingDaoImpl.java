package com.meeting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meeting.model.Meeting;

@Repository
public class MeetingDaoImpl implements MeetingDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Meeting createMeeting(Meeting meeting) {
		
		sessionFactory.getCurrentSession().save(meeting);
		
		return meeting;
		
	}

	@Override
	public Meeting updateMeeting(Meeting meeting) {
		
		sessionFactory.getCurrentSession().update(meeting);
		return meeting;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		String hql = "delete from meeting where meeting_id= :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong("id", id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public Meeting findById(Long id) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class);
		criteria.add(Restrictions.eq("meetingId", id));
		return (Meeting)criteria.uniqueResult();
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Meeting> findByHost(Long id) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class);
		criteria.add(Restrictions.eq("hostUser.userId", id));
		criteria.addOrder(Order.asc("time"));
		return (List<Meeting>) criteria.list();
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Meeting> findAllMeetings() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class);
		criteria.addOrder(Order.asc("time"));
		return (List<Meeting>) criteria.list();
		
	}
	
}
