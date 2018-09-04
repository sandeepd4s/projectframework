package com.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.sun.jna.platform.win32.OaIdl.SAFEARRAYBOUND;

public class GetProperties {
	private static FileInputStream file;
	private static FileOutputStream fo;
	private static Properties prop = new Properties();
	
	
	/**Method Name Specifies the file name, from which we are getting the property 
	 * argument : property, which should be exist in the config properties file
	 * @author SandeepReddyD
	 * Date : 25/05/2018
	 */
	public static String getConfigPropety(String property) {
		String val="";
		prop = new Properties();
		try{
			file = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			prop.load(file);
			val =prop.getProperty(property);
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}
	
	
	/***
	 * Method Name Specifies the file name, from which we are setting the specified property
	 * argument : property, which should be exist in the config properties file
	 * @author SandeepReddyD
	 * Date : 13/06/2018
	 */
	public static void setConfigProperty(String property, String value){
		try{
			file = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			prop.load(file);
			file.close();
			
			fo = new FileOutputStream(System.getProperty("user.dir")+"\\config.properties");
			prop.setProperty(property, value);
			prop.store(fo, null);
			fo.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/***
	 * Method Name Specifies the file name, from which we are update the specified property
	 * argument : property, which should be exist in the config properties file
	 * @author SandeepReddyD
	 * Date : 13/06/2018
	 */
	public static void updateConfigProperty(String property, String value){
		String propValue="";
		try{
			file = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			prop.load(file);
			propValue = prop.getProperty(property);
			file.close();
			propValue = propValue+" "+value;
			fo = new FileOutputStream(System.getProperty("user.dir")+"\\config.properties");
			prop.setProperty(property, propValue);
			prop.store(fo, null);
			fo.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
