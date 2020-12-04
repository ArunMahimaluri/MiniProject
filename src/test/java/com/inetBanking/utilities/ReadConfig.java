package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig()
	{
		File src= new File("C:\\Users\\Arun\\eclipse-workspace\\MiniProject\\Configuration\\config.properties");
		
		try
		{
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}catch(Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url=prop.getProperty("baseUrl");
		return url;
	}
	
	public String getUserName()
	{
		String usrname=prop.getProperty("username");
		return usrname;
	}
	
	public String getPassword()
	{
		String pswrd = prop.getProperty("password");
		return pswrd;
	}

}
