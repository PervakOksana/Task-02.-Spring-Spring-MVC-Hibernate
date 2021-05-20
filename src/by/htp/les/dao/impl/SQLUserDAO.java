package by.htp.les.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.les.bean.User;
import by.htp.les.dao.DAOException;
import by.htp.les.dao.UserDAO;

@Repository
public class SQLUserDAO implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User authorization(String login, String password) throws DAOException {

		User user = null;
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> newsQuery = currentSession.createQuery("from User where password= :paramName and login=:theLogin",
				User.class);
		newsQuery.setParameter("paramName", password);
		newsQuery.setParameter("theLogin", login);
		List<User> users = newsQuery.getResultList();
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public void registration(User user) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);

	}

}
