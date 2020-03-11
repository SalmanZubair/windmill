package com.prolim.windMill.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prolim.windMill.dao.WindmillLoginDao;
import com.prolim.windMill.model.LoginBean;
import com.prolim.windMill.model.WindmillUser;
import com.prolim.windMill.service.WindFarmService;

@Configuration
@PropertySource("classpath:/application.properties")
@Controller
public class WindFarmController {

	@Autowired
	private Environment env;

	final static Logger logger = Logger.getLogger(WindFarmController.class);

	@Autowired
	WindmillLoginDao loginDao;

	@Autowired
	WindFarmService windfarmService;

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean) {
		if (loginBean != null && loginBean.getUserName() != null & loginBean.getPassword() != null) {
			WindmillUser user = loginDao.getUserLoginData(loginBean.getUserName());
			if (loginBean.getUserName().equals(user.getUsername())
					&& loginBean.getPassword().equals(user.getPassword())) {
				model.addAttribute("msg", loginBean.getUserName());
				return "WindFarmMap";
			} else {
				model.addAttribute("error", "Invalid Details");
				return "login";
			}
		} else {
			model.addAttribute("error", "Please enter Details");
			return "login";
		}
	}

	
	@RequestMapping(path = "/farmdashboard/{windFarmId}", method = RequestMethod.GET)
	public ModelAndView farmDashboard(@PathVariable String windFarmId) {
	logger.info("In Calling Monitor to fetch the Dashboard");
		ModelAndView model = new ModelAndView("FarmDashboard");
		model.addObject("windFarmId", windFarmId);
		return model;
	}
	
	
	@RequestMapping(path = "/WindMillMap", method = RequestMethod.GET)
	public ModelAndView windmillMap() {
		logger.info("In Calling Monitor to fetch the Dashboard");
		ModelAndView model = new ModelAndView("welcome");
		// model.addObject("windmillId", windmillId);
		return model;
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String home1() {
		return "error";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(value = "/redirectFarmMap", method = RequestMethod.GET)
	public String redirectFarmMap(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "WindFarmMap";
	}

	@RequestMapping(path = "/farmdashboard/getWindFarmEnergy/{windFarmId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWindFarmEnergy(@PathVariable String windFarmId) {
		logger.info("In Controller calling Service layer to fetch Energy Trend for WindMill Id  :  " + windFarmId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windfarmService.getWindFarmEnergy(windFarmId);
			System.out.println("the json of energy is:" + json);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Energy Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}
	
	
	@RequestMapping(path = "/farmdashboard/getWindfarmAlert/{windFarmId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWindfarmAlert(@PathVariable String windFarmId) {
		logger.info("In Controller calling Service layer to fetch Energy Trend for WindMill Id  :  " + windFarmId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windfarmService.getWindmillAlertCountForWindFarm(windFarmId);
			System.out.println("the json of energy is:" + json);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Energy Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}
	
	
	@RequestMapping(path = "/farmdashboard/getFarmEnergyWindspeed/{windFarmId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getFarmEnergyWindspeed(@PathVariable String windFarmId) {
		logger.info("In Controller calling Service layer to fetch Energy Trend for WindMill Id  :  " + windFarmId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windfarmService.getFarmEnergyWindspeed(windFarmId);
			System.out.println("the json of energy is:" + json);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Energy Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}


	@RequestMapping(path = "/getWindFarmLocation", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWindFarmLocation() {

		logger.info("In Controller calling Service layer to fetch details of all WindFarm.");
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windfarmService.getWindFarmLocation();
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning WindFarm data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}
	
	@RequestMapping(path = "/farmdashboard/getFarmMetaData/{windFarmId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getFarmMetaData(@PathVariable String windFarmId) {
		logger.info("In Controller calling Service layer to fetch Energy Trend for WindMill Id  :  " + windFarmId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windfarmService.getFarmMetaData(windFarmId);
			System.out.println("the json of energy is:" + json);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Energy Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}
	
	@RequestMapping(path = "/farmdashboard/getTopFiveWindmillEnergy/{windFarmId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getTopFiveWindmillEnergy(@PathVariable String windFarmId) {
		logger.info("In Controller calling Service layer to Top 5 performing Windmills for the Farm  :  " + windFarmId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windfarmService.getTopFiveWindmillEnergy(windFarmId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Energy Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}

}