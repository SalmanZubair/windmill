package com.prolim.windMill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.prolim.windMill.model.WindmillUser;
import com.prolim.windMill.utility.DataSource;

@Repository
public class WindmillLoginDaoImpl implements WindmillLoginDao {

	final MysqlDataSource mySqlDataSource = DataSource.getInstance().getMySqlDS();

	public WindmillUser getUserLoginData(String username)	{
		WindmillUser user = new WindmillUser();
		try (Connection conn = mySqlDataSource.getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT * FROM devicedata.windmill_user_login where username = ?")) {

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
