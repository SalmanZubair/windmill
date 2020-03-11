/**
 * 
 */
package com.prolim.windMill.service;

/**
 * @author SalmanZubair
 *
 */
public interface WindmillService {

	public String getLocation(String farmId) throws Exception;
	
	public String getAlerts(String windMillId) throws Exception;
	
	public String getWindSpeed(String windMillId) throws Exception;
	
	public String getPower(String windMillId) throws Exception;
	
	public String getTemperatures(String windMillId) throws Exception;
	
	public String getPowerTrend(String windMillId) throws Exception;
	
	public String getWindTrend(String windMillId) throws Exception;
	
	public String getEnergyTrend(String windMillId) throws Exception;
	
	public String updateAlerts(String windMillId, String alertId) throws Exception;

	
}
