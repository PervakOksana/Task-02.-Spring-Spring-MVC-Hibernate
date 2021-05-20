package by.htp.les.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ControllerLocalization {

	@GetMapping("/local")
	public String local(Model model, @RequestParam("localpage") String localpage, @RequestParam("lang") String lang,
			HttpServletRequest request) {

		request.getSession(true).setAttribute("local", lang);
		return "redirect:/" + localpage;
	}

	@GetMapping("/local.link")
	public String local_link(Model model, @RequestParam("localpage") String localpage,
			@RequestParam("lang") String lang, @RequestParam("link_id") String link_id, HttpServletRequest request) {

		request.getSession(true).setAttribute("local", lang);
		model.addAttribute("link_id", link_id);
		return "redirect:/" + localpage;
	}

}
