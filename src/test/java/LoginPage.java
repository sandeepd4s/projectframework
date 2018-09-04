

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.seleniumutils.SafeActions;
import com.common.utils.GetProperties;

public class LoginPage extends SafeActions {
	private static WebDriver driver;
	public static LoginPage loginPage;
	private WebDriver webDriver;
	public static String mainWindow;
	public static String timestamp = getTimeStamp();
	public static Calendar now = Calendar.getInstance();
	public static String timeStampString = toAlphabetic(Long.valueOf(getTimeStamp()));
	
	public LoginPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
		this.driver = webDriver;
	}
	
	public static LoginPage getLoginPage(WebDriver webDriver){
		if(loginPage==null)
			return new LoginPage(webDriver);
		else
			return loginPage;
	}
	
	public static String toAlphabetic(Long i) {
	    if( i<0 ) {
	        return "-"+toAlphabetic(-i-1);
	    }

	    long quot = i/26;
	    long rem = i%26;
	    char letter = (char)((int)'A' + rem);
	    if( quot == 0 ) {
	        return ""+letter;
	    } else {
	        return toAlphabetic(quot-1) + letter;
	    }
	    
	}
	
	public static String getAlphaString(){
		String act = "";
		if(timeStampString.length()<6){
			for(int i=0;i<5;i++){
				act=timeStampString.charAt(i)+"";
			}
		}
		return act;
	}


	private static By loginUserNam = By.id("username");
	private static By loginPswrd = By.id("password");
	private static By loginBtn = By.id("Login");
	private static By sandBox = By.xpath("//div[@class='msgContent']/span/span[2]");
	
	
	
	private By userNameLabel = By.xpath("//span[@id='userNavLabel']");
	private By logoutLink = By.xpath("//div[@id='userNav-menuItems']/a[@title='Logout']");
	private By loginErrorMsg = By.xpath("//div[@id='error']");
	private By reportsTab = By.xpath("//li[@id='report_Tab']");
	private By dashboardsTab = By.xpath("//li[@id='Dashboard_Tab']");
	private By accountsTab = By.xpath("//li[@id='Account_Tab']");
	private By contactsTab = By.xpath("//li[@id='Contact_Tab']");
	private By leadTab = By.xpath("//li[@id='Lead_Tab']");
	private By opportunitiesTab = By.xpath("//li[@id='Opportunity_Tab']");
	
	private String profileDashboard = "//td[@class='pbTitle']/h2[text()='%s']"; //td[@class='pbTitle']/h2[text()='Dashboard']
	private String profileObjects = "//td[@class='pbTitle']/h3[text()='%s']";
	private By reports = By.xpath("//li[@id='report_Tab']");
	private By dashboards = By.xpath("//li[@id='Dashboard_Tab']");

	// **********************
	// launchBrowser() is to launch the browser
	// ***********************
	public WebDriver launchBrowser() {
		System.out.println("*************" + System.getProperty("user.dir"));

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

		new WebDriverWait(webDriver, 10);
		return webDriver;
	}
	
	public static By getUserName(){
		return loginUserNam;
	}
	
	public static By getPassword(){
		return loginPswrd;
	}
	
	public static By getLoginButton(){
		return loginBtn;
	}
	
	public boolean isReportsVisble(){
		return isElementVisible(reportsTab);
	}
	
	public boolean isDashboardVisible(){
		return isElementVisible(dashboardsTab);
	}
	
	public static String getTimeStamp(){
		//sdf.format(new Timestamp(System.currentTimeMillis()))
		return new SimpleDateFormat("yyyymmddhhmmss").format(new Timestamp(System.currentTimeMillis()));
	}
	
	public boolean isLeadTabPresent(){
		boolean flag= false;
		if(isElementPresent(leadTab)){
			flag = true; 
		}
		return flag;
	}
	
	public boolean isAccountTabPresent(){
		boolean flag= false;
		if(isElementPresent(accountsTab)){
			flag = true; 
		}
		return flag;
	}
	
	public boolean isContactTabPresent(){
		boolean flag= false;
		if(isElementPresent(contactsTab)){
			flag = true; 
		}
		return flag;
	}
	
	public boolean isOpportunityTabPresent(){
		boolean flag= false;
		if(isElementPresent(opportunitiesTab)){
			flag = true; 
		}
		return flag;
	}
	
	public boolean isReportsTabPresent(){
		boolean flag= false;
		if(isElementPresent(reportsTab)){
			flag = true; 
		}
		return flag;
	}
	
	public boolean isDashboardPresent(){
		boolean flag= false;
		if(isElementPresent(dashboardsTab)){
			flag = true; 
		}
		return flag;
	}

	public void setCredentials(String usrName, String password) {
		safeType(loginUserNam, usrName, "user name text field in the Login Page");
		safeType(loginPswrd, password, "Password field in the login page");
	}
	
	public void get(String url){
		webDriver.get(url);
		timestamp=getTimeStamp();
		System.out.println("****************** timstamp for current test execution is:**"+timestamp);
	}
	

	public void setLoginUserNam(String usrNm) {
		safeType(loginUserNam, usrNm, "user name tex field in the Login Page");
		// webDriver.findElement(loginUserNam).sendKeys(usrNm);
	}

	public void setLoginPasswrd(String pswrd) {
		webDriver.findElement(loginPswrd).sendKeys(pswrd);
	}

	// Function to click login button
	public void clickLoginBtn() {
		webDriver.findElement(loginBtn).click();
		waitUntilInvisible(loginBtn, "Login button in login page after clicked on login button", 1);
	}
	
	public String getErrMsg(){
		String errMsg = null;
		if(isElementPresent(loginErrorMsg)){
			errMsg = webDriver.findElement(loginErrorMsg).getText();
		}
		return errMsg;
	}

	public boolean validateUserLoggedIn() {
		boolean flag = false;
		if (isElementPresent(userNameLabel,1))
			flag = true;
		return flag;
	}

	public String getUserNameInLoggedIn() {
		String text = null;
		if (validateUserLoggedIn())
			text = getText(userNameLabel, "Login name in looged in page", 1).trim();
		return text;
	}

	/***
	 * This method is to derive username from given username
	 * @param userName
	 * @return
	 */
	public String getUserFromData(String userName) {
		 String text, text1;
		String[] str = userName.split("@");
		String str1=str[0].toString().replace('.', '_');
		String []str2=str1.split("_");
		text1 = str2[1].toUpperCase();
		text = str2[0].substring(0, 1).toUpperCase();
		return text+str2[0].substring(1)+" "+text1;
	}

	public void logout() throws InterruptedException {
		switchToMainWindow();
		safeSwitchToFrame(userNameLabel, 2);
		Thread.sleep(100);
		if (isElementPresent(userNameLabel, 5))
			jsClick(userNameLabel, 1);
//			webDriver.findElement(userNameLabel).click();
		if (isElementPresent(logoutLink, 5))
			jsClick(logoutLink, 1);
//			webDriver.findElement(logoutLink).click();
		//waitUntilInvisible(logoutLink, "Logout link after clicked on user name after logged in", 10);
	}

	public boolean validateUserLoggedOut() {
		boolean flag = false;
		if (isElementPresent(loginUserNam, 5))
			flag = true;
		return flag;
	}
	
	public By locatorForDashboardObject(String text){
		return By.xpath(String.format(profileDashboard, text));
	}
	
	public By locatorForProfileObject(String text){
		return By.xpath(String.format(profileObjects, text));
	}
	
	public boolean isDashboardProfileObjectExist(String objectName){
		return isElementPresent(locatorForDashboardObject(objectName),1);
	}
	
	public boolean isProfileObjectExist(String objectName){
		return isElementPresent(locatorForProfileObject(objectName),1);
	}
	
	public static int getCurrentYear(){
		return now.get(Calendar.YEAR);
	}
	
	/***
	 * redict() is to navigate to app url based on the given environment
	 * @author SandeepReddyD
	 */
	public void redirect(){
		if(GetProperties.getConfigPropety("Envionment").equals("FTQuality"))
			driver.navigate().to(GetProperties.getConfigPropety("FTQuality"));
	    if(GetProperties.getConfigPropety("Envionment").equals("FirstSand"))
	    	driver.navigate().to(GetProperties.getConfigPropety("FirstSand"));
	}
	
	/***
	 * 
	 */
	public void switchToProductTabAndCloseTab(String productName){
		switchToNewTabAndCloseTab(productName);
	}
	
	public void closeAllProdWindows(){
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		for(int i=1;i<windows.size();i++){
			driver.switchTo().window(windows.get(i));
			driver.close();
		}
	}
	
	/**
	 * @throws InterruptedException *
	 * 
	 */
	public void switchToProductTab(String productName) throws InterruptedException{
		Thread.sleep(10000);
		ArrayList<String> allWindows= new ArrayList<String>(driver.getWindowHandles());
		String windowTitle = driver.getTitle();
		System.out.println("***********Current Window which is on focus*****:"+windowTitle);
		int i=0;
		while(!windowTitle.equals(productName)){
			driver.switchTo().window(allWindows.get(i));
			windowTitle = driver.getTitle();
			System.out.println("***********Inside loop Current Window which is on focus*****:"+windowTitle);
			i++;
		}
	}
	
	/***
	 * To switch main Window
	 * @author SandeepReddyD
	 */
	public void switchToMainWindow(){
		driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(0));
	}
	
	/***
	 * To refresh the page
	 */
	public void pageRefresh(){
		driver.navigate().refresh();
	}
	
	/***
	 * login() is to login in to app based on given channel
	 * @param channel
	 * @author SandeepReddyD
	 */
	public void login(String channel){
		if((GetProperties.getConfigPropety("Envionment").equals("FTQuality"))&& channel.equals("Branch")){
			safeType(loginUserNam, GetProperties.getConfigPropety("FTQualtyBranchUserId"), "Enter userid in username field in login page, When environment:"+GetProperties.getConfigPropety("Envionment")+" and channel: "+channel,2);
			safeType(loginPswrd, GetProperties.getConfigPropety("FTQualityBranchPassword"), "Enter password in password field in login page, When environment:"+GetProperties.getConfigPropety("Envionment")+" and channel: "+channel);
		}else if((GetProperties.getConfigPropety("Envionment").equals("FTQuality"))&& channel.equals("Contact Center")){
			safeType(loginUserNam, GetProperties.getConfigPropety("FTQualtyContactUserId"), "Enter userid in username field in login page, When environment:"+GetProperties.getConfigPropety("Envionment")+" and channel: "+channel,2);
			safeType(loginPswrd, GetProperties.getConfigPropety("FTQualityContactPassword"), "Enter password in password field in login page, When environment:"+GetProperties.getConfigPropety("Envionment")+" and channel: "+channel);
		}
		safeClick(loginBtn, "Login button in Login page");
	}
	
	public String getSandBoxName(){
		return getText(sandBox, "Sandbox name after login into :"+GetProperties.getConfigPropety("Envionment"), 2);
	}
	
	public void closeTitledWindow(String title){
		switchToWindowWithTitleContainsAndCloseWindow(title);
	}
	

}
