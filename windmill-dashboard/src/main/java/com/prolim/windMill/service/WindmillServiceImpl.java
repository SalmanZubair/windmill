/**
 * 
 */
package com.prolim.windMill.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.prolim.windMill.dao.WindmillDao;

/**
 * @author SalmanZubair
 *
 */
public class WindmillServiceImpl implements WindmillService {

	@Autowired
	WindmillDao windmillDao;

	@Override
	public String getLocation(String farmId) throws Exception {
		try {
			return windmillDao.getLocation(farmId);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public String getPower(String windmillId) throws Exception {
		try {
			return windmillDao.getPower(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String getPowerTrend(String windmillId) throws Exception {
		try {
			return windmillDao.getPowerTrend(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String getWindSpeed(String windmillId) throws Exception {
		try {
			return windmillDao.getWindSpeed(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String getWindTrend(String windmillId) throws Exception {
		try {
			return windmillDao.getWindTrend(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String getEnergyTrend(String windmillId) throws Exception {
		try {
			return windmillDao.getEnergyTrend(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}


	@Override
	public String getTemperatures(String windmillId) throws Exception {
		try {
			return windmillDao.getTemperatures(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public String getAlerts(String windmillId) throws Exception {
		try {
			return windmillDao.getAlerts(windmillId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String updateAlerts(String windmillId, String alertId) throws Exception {
		try {
			return windmillDao.updateAlerts(windmillId, alertId);
		}

		catch (Exception e) {
			throw new Exception();
		}
	}

}