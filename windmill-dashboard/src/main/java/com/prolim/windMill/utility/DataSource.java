package com.prolim.windMill.utility;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DataSource {
    private static final String DB_CONNECTION_URL = "jdbc:mysql://devicedata.cfgwpu5nkbcz.us-east-1.rds.amazonaws.com:3306/";
    private static final String DB_USER = "prolim";
    private static final String DB_PWD = "prolim123";
    private static DataSource ds;
    private MysqlDataSource mySqlDS = new MysqlDataSource();

 

    // private constructor
    private DataSource() {
        mySqlDS.setUrl(DB_CONNECTION_URL);
        mySqlDS.setUser(DB_USER);
        mySqlDS.setPassword(DB_PWD);
    }

 

    /**
     * static method for getting instance.
     */
    public static DataSource getInstance() {
        if (ds == null) {
            ds = new DataSource();
        }
        return ds;
    }

 

    public MysqlDataSource getMySqlDS() {
        return mySqlDS;
    }

 

    public void setMySqlDS(MysqlDataSource mySqlDS) {
        this.mySqlDS = mySqlDS;
    }
}