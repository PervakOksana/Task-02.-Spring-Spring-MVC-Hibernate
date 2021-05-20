package by.htp.les.dao;

import java.util.List;

import by.htp.les.bean.News;

public interface NewsDAO {

	List<News> all() throws DAOException;

	void saveChange(News news) throws DAOException;
	void saveNew(News news) throws  DAOException;
	void delete(int id) throws DAOException;
}
