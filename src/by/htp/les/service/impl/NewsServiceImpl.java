package by.htp.les.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.les.bean.News;
import by.htp.les.dao.DAOException;
import by.htp.les.dao.NewsDAO;
import by.htp.les.service.NewsService;
import by.htp.les.service.ServiceException;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Transactional
	@Override
	public List<News> takeAll() throws ServiceException {

		List<News> news = null;

		try {
			news = newsDAO.all();

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return news;
	}

	@Transactional
	@Override
	public void saveChange(News news) throws ServiceException {

		try {
			newsDAO.saveChange(news);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Transactional
	@Override
	public void delete(int id) throws ServiceException {

		try {
			newsDAO.delete(id);

		} catch (DAOException e) {

			throw new ServiceException(e);
		}

	}

	@Transactional
	@Override
	public void saveNew(News news) throws ServiceException {

		try {
			System.out.println("saveNew Service- " + news.toString());
			newsDAO.saveNew(news);

		} catch (DAOException e) {

			throw new ServiceException(e);
		}

	}

}
