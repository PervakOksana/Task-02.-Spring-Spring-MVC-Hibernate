package by.htp.les.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.les.bean.User;
import by.htp.les.dao.DAOException;
import by.htp.les.dao.UserDAO;
import by.htp.les.service.ServiceException;
import by.htp.les.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public User authorization(String login, String password) throws ServiceException {
		// validation
		if (login == null || "".equals(login)) {
			throw new ServiceException("Wrong login or password");
		}

		User user = null;
		try {
			user = userDAO.authorization(login, password);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Transactional
	@Override
	public void registration(User user) throws ServiceException {

		try {
			
			userDAO.registration(user);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
