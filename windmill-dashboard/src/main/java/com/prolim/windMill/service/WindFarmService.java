package com.prolim.windMill.service;

public interface WindFarmService {
	
	public String getWindFarmEnergy(String windFarmId) throws Exception;
	public String getWindmillAlertCountForWindFarm(String windFarmId) throws Exception;
	public String getFarmEnergyWindspeed(String windFarmId) throws Exception;
	public String getWindFarmLocation() throws Exception;
	public String getFarmMetaData(String windFarmId) throws Exception;
	public String getTopFiveWindmillEnergy(String windFarmId) throws Exception;

}
