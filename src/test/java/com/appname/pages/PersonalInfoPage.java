package com.appname.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.common.seleniumutils.SafeActions;
import com.common.seleniumutils.SharedDriver;

import sun.security.util.Length;

public class PersonalInfoPage extends SafeActions
{	
	SharedDriver sd = new SharedDriver();
	static WebDriverWait wait;
	public static String AppName;
	public PersonalInfoPage(WebDriver webDriver) {
		super(webDriver);
		wait = new WebDriverWait(webDriver, 50);
	}
	
	private By Non_Members = By.xpath("//button[span[contains(text(),'Continue as Our Guest')]]");
}