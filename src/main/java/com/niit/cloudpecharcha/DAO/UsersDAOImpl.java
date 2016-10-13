package com.niit.cloudpecharcha.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.cloudpecharcha.model.Users;

//@Repository
//@Transactional
public class UsersDAOImpl implements UsersDAO {
	
	
	@Autowired
	SessionFactory mysessionFactory;
	
	public UsersDAOImpl(SessionFactory mysessionFactory) {
		// TODO Auto-generated constructor stub
	}

	

	@Transactional
	public void addNewUser(Users user)
	{
		Session session = mysessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Serializable objId = session.save(user);
		tx.commit();
		System.out.println(objId.toString());
		if(objId!=null)
			System.out.println("User Added");
		else
			System.out.println("Registration Faild");
		
		session.close();
	}
	
	@Transactional
	public Users getUserById(int id)
	{
		
		Session session = mysessionFactory.getCurrentSession();
		
				Users u=(Users) session.load(Users.class, new Integer(id));
				System.out.println("get user by id:"+u);
				return u;
	}
	
	
	
	
	@Transactional
	public List<Users> getAllusers() {
		Session session = mysessionFactory.openSession();
		
		Criteria criteria=session.createCriteria(Users.class);
		List<Users> usersList = (List<Users>)(criteria.list());
		session.close();
		return usersList;
		
	}
	
	@Transactional
	public boolean getUser(Users user)
	{
		 Session session = mysessionFactory.openSession();
		// Serializable objId = (Serializable) session.get(user.getClass(), user.getId());
		// System.out.println(objId.toString());
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", user.getUsername() ));
		criteria.add(Restrictions.eq("password",user.getPassword() ));
		List myList = criteria.list();
		session.close();
		if(myList.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public void deleteUser(Users user) {
		Session session = this.mysessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		if(null!= user)
		{
			session.delete(user);
		}
		tx.commit();
		}
	
	@Override
	public Users UpdateUser(Users user, int id) {
		Session session = this.mysessionFactory.openSession();
		Users u=(Users) session.load(Users.class, new Integer (id));
		Transaction tx= session.beginTransaction();
		session.update(user);
		tx.commit();
		return u;
		}

	
}
