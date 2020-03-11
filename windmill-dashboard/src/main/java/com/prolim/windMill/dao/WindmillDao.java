
/**
 * 
 */
package com.prolim.windMill.dao;

/**
 * @author SalmanZubair
 *
 */
public interface WindmillDao {

	public String getLocation(String farmId) throws Exception;
	
	public String getAlerts(String windmillId) throws Exception;
	
	public String getWindSpeed(String windmillId) throws Exception;
	
	public String getPower(String windmillId) throws Exception;
	
	public String getTemperatures(String windmillId) throws Exception;
	
	public String getPowerTrend(String windmillId) throws Exception;
	
	public String getWindTrend(String windmillId) throws Exception;

	public String getEnergyTrend(String windmillId) throws Exception;
	
	public String updateAlerts(String windmillId, String alertId) throws Exception;
}
