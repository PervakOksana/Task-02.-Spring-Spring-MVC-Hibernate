package by.htp.les.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import by.htp.les.bean.News;
import by.htp.les.bean.User;
import by.htp.les.service.NewsService;
import by.htp.les.service.ServiceException;
import by.htp.les.service.UserService;
import by.htp.les.service.ValidatorService;

@Controller

public class ControllerUser {

	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ValidatorService validatorService;

	@RequestMapping("/logination")
	public String logination(@ModelAttribute("user") User user, HttpServletRequest request, Model theModel) {

		HttpSession session = request.getSession();
		User userNew = new User();
		String page;
		theModel.addAttribute("user", userNew);

		try {
			user = userService.authorization(user.getLogin(), user.getPassword());
			if (user == null) {
				session.setAttribute("messageLog", "errorLog");
				page = "redirect:/goToIndexPage";
				return page;

			} else {

				session.setAttribute("auth", true);
				session.setAttribute("role", user.getRole());
				session.setAttribute("message", user.getName());
				System.out.println("user.getRole() - "+user.getRole().toString());
				System.out.println("user.getName() - "+user.getName().toString());
				page = "redirect:/goToMainPage";
				return page;
			}
		} catch (ServiceException e) {
			page = "redirect:/goToIndexPage";
			return page;
		}

	}

	@RequestMapping("/goToRegistrationPage")
	public String goToRegistrationPage(@ModelAttribute("user") User user, HttpServletRequest request, Model theModel) {

		String page = "registration";
		List<News> theNews = null;
		try {
			theNews = newsService.takeAll();
		} catch (ServiceException e) {
			page = "error_page";
		}

		theModel.addAttribute("news", theNews);

		return page;
	}

	@RequestMapping("/registration")
	public String saveNewUser(@ModelAttribute("user") User user, HttpServletRequest request, Model theModel) {

		HttpSession session = request.getSession();
		String page = "redirect:/goToIndexPage";
		try {
			if (!validatorService.registrationValidator(request)) {

				session.setAttribute("message", "errorNew");
				page = "redirect:/goToIndexPage";
				return page;
			}

			userService.registration(user);

			if (user == null) {
				return page;
			}
			if (user != null) {

				session.setAttribute("auth", true);
				session.setAttribute("role", user.getRole());
				session.setAttribute("message", "registration");
				page = "redirect:/list";
				return page;

			}
		} catch (ServiceException e) {
			page = "redirect:/goToIndexPage";
			return page;

		}
		return page;
	}

	@RequestMapping("/goToIndexPage")
	public String goToIndexPage(@ModelAttribute("user") User user, HttpServletRequest request, Model theModel) {

		try {
			HttpSession session = request.getSession();
			List<News> news = newsService.takeAll();

			request.setAttribute("news", news);
			session.removeAttribute("messageLog");
			session.removeAttribute("message");
			return "main_index";

		} catch (ServiceException e) {

			return "error_page";
		}
	}

	@RequestMapping("/goToMainPage")
	public String goToMainPage(@ModelAttribute("user") User user, HttpServletRequest request,
			HttpServletResponse response, Model theModel) {

		String page = "redirect:/goToIndexPage";

		if (!validatorService.loginationValidator(request, response)) {

			page = "redirect:/goToIndexPage";
			return page;
		}

		try {
			List<News> news = newsService.takeAll();

			request.setAttribute("news", news);

			page = "main";
			return page;

		} catch (ServiceException e) {

			page = "error_page";
			return page;
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session != null) {
			session.removeAttribute("auth");
		}

		return "redirect:/goToIndexPage";
	}

}
