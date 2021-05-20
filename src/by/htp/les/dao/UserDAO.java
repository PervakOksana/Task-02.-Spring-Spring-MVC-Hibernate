package by.htp.les.dao;

import by.htp.les.bean.User;

public interface UserDAO {
	User authorization(String login, String password) throws DAOException;
	void registration (User user) throws DAOException;
}
