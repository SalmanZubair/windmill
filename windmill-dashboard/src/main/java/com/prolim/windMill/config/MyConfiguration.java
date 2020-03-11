package com.prolim.windMill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prolim.windMill.dao.WindmillDao;
import com.prolim.windMill.dao.WindmillDaoImpl;
import com.prolim.windMill.service.WindmillService;
import com.prolim.windMill.service.WindmillServiceImpl;
import com.prolim.windMill.utility.TokenManagement;
import com.prolim.windMill.utility.TokenManagementImpl;

@Configuration
class MyConfiguration {
	@Bean
	public WindmillService windMillService() {
		return new WindmillServiceImpl();
	}

	@Bean
	public WindmillDao windmillDao() {
		return new WindmillDaoImpl();
	}
	
	/*@Bean
	public TokenManagement tokenManagementImpl() {
		return new TokenManagementImpl();
	}*/
}