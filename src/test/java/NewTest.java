

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.functors.SwitchTransformer;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.seleniumutils.SharedDriver;
import com.common.utils.ConfigReader;
import com.common.utils.GetProperties;
import com.common.utils.ScenariosFactory;
import com.relevantcodes.extentreports.ExtentReports;


public class NewTest {

	WebDriver driver;
	public SharedDriver sd = new SharedDriver();
	ExtentReports eReport;

	LoginPage loginPage;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException {
		System.out.println("Before Method");

		driver = sd.setDriver();
		eReport = sd.setExtentReport();
		loginPage = LoginPage.getLoginPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws InterruptedException {
		System.out.println("After Method in New Test");
		sd.endExtentReport(result, driver);
		loginPage.closeAllProdWindows();
		loginPage.logout();
		loginPage.redirect();
	}

	@BeforeClass(alwaysRun = true)
	public void nameBefore() throws Exception {

	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		System.out.println("Before Suite");
		sd.setExtentReport();
	}

	@AfterSuite
	public void afterSuite() throws IOException, InterruptedException {
		System.out.println("After Suite");
		sd.driverQuit();
		eReport.close();
	}

	//@Test(dataProvider = "testSource")
	@Test
	public void scenarios() throws InterruptedException, IOException, Exception {

	}	

	@DataProvider(name = "testSource")
	public Iterator<Object[]> getTestDataSet(Method method) {
		ScenariosFactory scenariosFactory = new ScenariosFactory();
		return scenariosFactory.getTestDataSet(ConfigReader.getConfigProps().get("scenariosData").split("\\-")[0],
				method.getName());
	}
}
