package by.htp.les.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import by.htp.les.bean.News;
import by.htp.les.bean.User;
import by.htp.les.service.NewsService;
import by.htp.les.service.ServiceException;
import by.htp.les.service.ValidatorService;

@Controller
public class ControllerNews {

	@Autowired
	private NewsService newsService;
	@Autowired
	private ValidatorService validatorService;
	private static final Logger logger = LoggerFactory.getLogger(ControllerNews.class);

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		List<News> theNews = null;
		try {
			theNews = newsService.takeAll();
		} catch (ServiceException e) {
			return "error_page";
		}

		User userNew = new User();
		theModel.addAttribute("user", userNew);
		theModel.addAttribute("news", theNews);

		return "main_index";
	}

	@RequestMapping("/readNews")
	public String readNews(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("link_id") int id) {

		if (!validatorService.loginationValidator(request, response)) {

			return "redirect:/goToIndexPage";
		}

		try {
			List<News> news = newsService.takeAll();

			for (News n : news) {
				if (n.getId() == id) {

					request.setAttribute("news", n);

				}
			}

			return "read_news";

		} catch (ServiceException e) {

			return "error_page";
		}
	}

	@RequestMapping("/goToAddNewsPage")
	public String goToAddNewsPage(Model theModel) {

		News theNews = new News();
		theModel.addAttribute("news", theNews);
		return "add_news";
	}

	@RequestMapping("/saveNews")
	public String saveNews(@Valid News news, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning add_news.jsp page");
			return "add_news";
		}
		logger.info("Returning add_news.jsp page");
		news.setActive(1);
		try {
			newsService.saveNew(news);
		} catch (ServiceException e) {
			return "error_page";
		}
		return "redirect:/goToMainPage";

	}

	@RequestMapping("/updateNews")
	public String updateNews(Model theModel, HttpServletRequest request, HttpServletResponse response) {

		if (!validatorService.loginationValidator(request, response)) {

			return "redirect:/goToIndexPage";
		}
		int id = Integer.parseInt(request.getParameter("link_id"));

		try {
			List<News> news = newsService.takeAll();

			for (News n : news) {
				if (n.getId() == id) {

					theModel.addAttribute("news", n);
				}
			}
			return "update_news";

		} catch (ServiceException e) {

			return "error_page";
		}

	}

	@RequestMapping("/deleteNews")
	public String deleteNews(HttpServletRequest request, HttpServletResponse response) {

		if (!validatorService.loginationValidator(request, response)) {
			return "redirect:/goToIndexPage";
		}
		int id = Integer.parseInt(request.getParameter("link_id"));

		try {
			newsService.delete(id);

			return "redirect:/goToMainPage";

		} catch (ServiceException e) {
			return "error_page";
		}

	}
}
