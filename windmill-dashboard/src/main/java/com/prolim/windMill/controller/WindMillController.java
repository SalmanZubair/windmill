package com.prolim.windMill.controller;

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
import com.prolim.windMill.service.WindmillService;

@Configuration
@PropertySource("classpath:/application.properties")

@Controller
public class WindMillController {

	@Autowired
	private Environment env;

	final static Logger logger = Logger.getLogger(WindMillController.class);

	
	@Autowired
	WindmillService windmillService;
	
	
	
	@RequestMapping(path = "farmdashboard/monitor/{windmillId}", method = RequestMethod.GET)
	public ModelAndView monitor(@PathVariable String windmillId) {
	logger.info("In Calling Monitor to fetch the Dashboard");
		ModelAndView model = new ModelAndView("monitor");
		model.addObject("windmillId", windmillId);
		return model;
	}

	@RequestMapping(path = "farmdashboard/monitor/getPower/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getPower(@PathVariable String windMillId) throws Exception {

		logger.info("In Controller calling Service layer to fetch Power Efficiency for WindMill Id  :  " + windMillId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getPower(windMillId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning Power Efficiency data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();

	}

	@RequestMapping(path = "farmdashboard/monitor/getPowerTrend/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getPowerTrend(@PathVariable String windMillId) throws Exception {

		logger.info("In Controller calling Service layer to fetch Power Trend for WindMill Id  :  " + windMillId);

		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getPowerTrend(windMillId);
			System.out.println(json);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning Power Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}

	@RequestMapping(path = "farmdashboard/monitor/getWindSpeed/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWindSpeed(@PathVariable String windMillId) throws Exception {

		logger.info(
				"In Controller calling Service layer to fetch current Wind Speed for WindMill Id  :  " + windMillId);
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getWindSpeed(windMillId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning current Wind Speed data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();

	}

	@RequestMapping(path = "farmdashboard/monitor/getWindSpeedTrend/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWindSpeedTrend(@PathVariable String windMillId) throws Exception {

		logger.info("In Controller calling Service layer to fetch Wind Speed Trend for WindMill Id  :  " + windMillId);
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getWindTrend(windMillId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning Wind Speed Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();

	}

	@RequestMapping(path = "farmdashboard/monitor/getEnergyTrend/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getEnergyTrend(@PathVariable String windMillId) {
		logger.info("In Controller calling Service layer to fetch Energy Trend for WindMill Id  :  " + windMillId);
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getEnergyTrend(windMillId);
			System.out.println("the json of energy is:"+json);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Energy Trend data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}

	@RequestMapping(path = "farmdashboard/monitor/getTemperatures/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getTemperatures(@PathVariable String windMillId) throws Exception {

		logger.info("In Controller calling Service layer to fetch Temperature for WindMill Id  :  " + windMillId);
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getTemperatures(windMillId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning Temperature data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "farmdashboard/monitor/getAlerts/{windMillId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAlerts(@PathVariable String windMillId) throws Exception {

		logger.info("In Controller calling Service layer to fetch Alerts for WindMill Id  :  " + windMillId);
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		JSONObject finalResponse = new JSONObject();
		
		try {
			String json = windmillService.getAlerts(windMillId);
			System.out.println(json);
			res = (JSONObject) parser.parse(json);
			
			finalResponse.put("newRes",res.get("data").toString());
			finalResponse.put("WindMillId",windMillId);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning Alert data set from Controller  :  " + res.get("data").toString());
		return finalResponse.toString();
	}
	
	
	@RequestMapping(path = "farmdashboard/monitor/updateAlerts/{windMillId}/{alertId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String updateAlerts(@PathVariable String windMillId,@PathVariable String alertId) throws Exception {

		logger.info("In Controller calling Service layer to fetch updateAlerts for WindMill Id  :  " + windMillId);
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.updateAlerts(windMillId,alertId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning update Alerts data set from Controller  :  " + res.get("data").toString());
		return res.toString();
	}
	
	
	@RequestMapping(path = "farmdashboard/monitor/updateWindmillStatus/{windmillId}/{status}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String updateWindMillStatus(@PathVariable String windmillId, @PathVariable String status) {
		logger.info("In Controller received request to turn " + status +" windmill :: " +windmillId);
	return null;
	}
	
	
	@RequestMapping(path = "/farmdashboard/getLocation/{farmId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getLocation(@PathVariable String farmId) {
		
		logger.info("In Controller calling Service layer to fetch details of all WindMill.");
		JSONParser parser = new JSONParser();
		JSONObject res = null;
		try {
			String json = windmillService.getLocation(farmId);
			res = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Returning Windmill data set from Controller  :  " + res.get("data").toString());
		return res.get("data").toString();
	}

}
