package com.prolim.windMill.dao;

import com.prolim.windMill.model.WindmillUser;

public interface WindmillLoginDao {
	
	public WindmillUser getUserLoginData(String username);

}
