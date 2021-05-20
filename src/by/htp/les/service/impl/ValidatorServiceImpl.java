package by.htp.les.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import by.htp.les.service.ValidatorService;
@Service
public class ValidatorServiceImpl implements ValidatorService {

	@Override
	public boolean loginationValidator(HttpServletRequest request, HttpServletResponse response) {
		boolean result = true;
		HttpSession session = request.getSession();
		if (session == null) {
			result = false;

		}
		Boolean iaAuth = (Boolean) session.getAttribute("auth");
		if (iaAuth == null || !iaAuth) {
			result = false;

		}
		return result;
	}

	@Override
	public boolean registrationValidator(HttpServletRequest request) {
		String password;

		password = request.getParameter("password");

		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}");
		Matcher matcher = pattern.matcher(password);
		matcher.lookingAt();
		return matcher.lookingAt();
	}

	@Override
	public boolean dateValidator(Date date) {
		
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String sDate = formatter.format(date);
		System.out.println("dateValidator Ser"+ sDate.toString());
		//Date date;

		//date =request.getParameter("date");

		//Pattern pattern = Pattern.compile("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
		Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
		Matcher matcher = pattern.matcher(sDate);
		matcher.lookingAt();
		return matcher.lookingAt();
	}

}
