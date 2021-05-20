package by.htp.les.service;

import by.htp.les.bean.User;

public interface UserService {
	User authorization(String login, String password) throws ServiceException;

	public void registration(User user) throws ServiceException;
}
