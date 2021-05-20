package by.htp.les.service;



import java.util.List;

import by.htp.les.bean.News;

public interface NewsService {
	List <News> takeAll () throws ServiceException;
	void  saveChange (News news) throws ServiceException;
	void  saveNew (News news) throws ServiceException;
	void  delete (int id) throws ServiceException;

}
