package com.shatskiy.logHib.controller;

import java.util.List;
import java.util.Map;
import org.hibernate.cfg.Configuration;
import javax.validation.Valid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shatskiy.logHib.domain.LoginData;

@Controller
@RequestMapping("/")
public class ControllerTask implements DisposableBean {
	
	private final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(LoginData.class).buildSessionFactory();

	@Value("#{countryOptions}")
	private Map<String, String> countryOptions;
	@Value("#{genderOptions}")
	private Map<String, String> genderOptions;
	
	@Override
	public void destroy() throws Exception {
		factory.close();	
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/loginForm")
	public String execute(Model model) {

		LoginData loginData = new LoginData();

		model.addAttribute("loginData", loginData);

		model.addAttribute("genderOptions", genderOptions);
		model.addAttribute("countryOptions", countryOptions);

		return "login-page";
	}

	@RequestMapping("/showAllUsers")
	public String showAllUsers(Model model) {
		
		Session session = factory.openSession();

		try {
			session.beginTransaction();

			List<LoginData> list = session.createQuery("FROM LoginData").list();

			session.getTransaction().commit();

			model.addAttribute("loginDatas", list);

		} finally {
			session.close();
		}
		return "main-page";
	}

	@RequestMapping("/resultForm")
	public String showResultForm(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("genderOptions", genderOptions);
			model.addAttribute("countryOptions", countryOptions);

			return "login-page";
		} else {
			Session session = factory.getCurrentSession();
			try {
				session.beginTransaction();

				session.save(loginData);

				session.getTransaction().commit();
			} finally {
				session.close();
			}
			return "main-page";
		}
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") Integer userId, Model model) {

		Session session = factory.openSession();
		LoginData loginData;

		try {

			session.beginTransaction();

			loginData = session.get(LoginData.class, userId);

			session.delete(loginData);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

		String message = loginData.getLogin() + " deleted";
		model.addAttribute("messageDelete", message);
		return "main-page";
	}

	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("userId") Integer userId, Model model) {
		
		Session session = factory.openSession();
		LoginData loginData;

		try {

			session.beginTransaction();

			loginData = session.get(LoginData.class, userId);

			model.addAttribute("loginData", loginData);
	
			model.addAttribute("genderOptions", genderOptions);
			model.addAttribute("countryOptions", countryOptions);
		
		} finally {
			session.close();
		}
		return "update-page";
	}
	
	
	@RequestMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute("loginData") LoginData newLoginData, BindingResult bindingResult,
			Model model, @RequestParam("userId") Integer userId) {
		    LoginData updetingLoginData;
		if (bindingResult.hasErrors()) {
			model.addAttribute("genderOptions", genderOptions);
			model.addAttribute("countryOptions", countryOptions);

			return "login-page";
		} else {
			Session session = factory.getCurrentSession();
			try {
				session.beginTransaction();

				updetingLoginData = session.get(LoginData.class, userId);
				
				updetingLoginData.setLogin(newLoginData.getLogin());
				updetingLoginData.setPassword(newLoginData.getPassword());
				updetingLoginData.setName(newLoginData.getName());
				updetingLoginData.setSurname(newLoginData.getSurname());
				updetingLoginData.setGender(newLoginData.getGender());
				updetingLoginData.setAge(newLoginData.getAge());
				updetingLoginData.setCountry(newLoginData.getCountry());
				

				session.getTransaction().commit();
				
			} finally {
				session.close();
			}
			String message = updetingLoginData.getLogin() + " updated";
			model.addAttribute("messageUpdate", message);
			return "main-page";
		}
	}
}
