package by.htp.les.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.les.bean.News;
import by.htp.les.dao.DAOException;
import by.htp.les.dao.NewsDAO;

@Repository
public class SQLNewsDAO implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> all() throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<News> newsQuery = currentSession.createQuery("from News where active=1", News.class);

		List<News> news = newsQuery.getResultList();

		return news;
	}

	@Override
	public void saveChange(News news) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(news);

	}

	@Override
	public void delete(int id) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("update News set active ='0' where id=:newsId");
		theQuery.setParameter("newsId", id);

		theQuery.executeUpdate();

	}

	@Override
	public void saveNew(News news) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(news);

	}

}
