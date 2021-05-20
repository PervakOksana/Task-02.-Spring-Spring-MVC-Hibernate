package by.htp.les.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidatorService {
	
	boolean loginationValidator(HttpServletRequest request,HttpServletResponse response) ;
	boolean registrationValidator(HttpServletRequest request) ;
	boolean dateValidator(Date date) ;
}
