package com.prolim.windMill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolim.windMill.dao.WindFarmDao;

@Service
public class WindFarmServiceImpl implements WindFarmService{
	
	@Autowired
	WindFarmDao windfarmDao;

	@Override
	public String getWindFarmEnergy(String windFarmId) throws Exception {
		try {
			return windfarmDao.getWindFarmEnergy(windFarmId);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public String getWindmillAlertCountForWindFarm(String windFarmId) throws Exception {
		try {
			return windfarmDao.getWindmillAlertCountForWindFarm(windFarmId);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public String getFarmEnergyWindspeed(String windFarmId) throws Exception {
		try {
			return windfarmDao.getFarmEnergyWindspeed(windFarmId);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public String getWindFarmLocation() throws Exception {
		try {
			return windfarmDao.getWindFarmLocation();
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String getFarmMetaData(String windFarmId) throws Exception {
		try {
			return windfarmDao.getFarmMetaData(windFarmId);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String getTopFiveWindmillEnergy(String windFarmId) throws Exception {
		try {
			return windfarmDao.getTopFiveWindmillEnergy(windFarmId);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

}