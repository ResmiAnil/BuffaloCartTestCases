package buffalocart;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.Readexcel;
import utility.Webdrivermanager;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class LoginVerify {

	static String url = "https://erp.buffalocart.com/login";
	static String title = "Welcome to Codecarrots";
	
	WebDriver driver;
	Webdrivermanager objManager;

	@Test(priority = 1)
	public void VerifyUrl() {

		String currenturl = driver.getCurrentUrl();

		SoftAssert objSofturlverify = new SoftAssert();
		objSofturlverify.assertEquals(currenturl, url);
		System.out.println("Url verification result...");
		objSofturlverify.assertAll();

	}

	@Test(priority = 2)
	public void TitleVerify() {
		String titleCheck = driver.getTitle();
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(titleCheck, title);
		System.out.println("title verification result...");
		objSoftAssert.assertAll();
	}

	@Test(priority = 5, enabled = true)
	public void LoginVerification() throws InterruptedException {
		objManager.loginVerify();
		/*
		 * String validUserName = "admin"; String validPassword = "123456"; String
		 * actualUrl = "https://erp.buffalocart.com/admin/dashboard";
		 * 
		 * WebElement userName = driver.findElement(By.name("user_name")); WebElement
		 * password = driver.findElement(By.name("password")); WebElement signin =
		 * driver.findElement(By.xpath(
		 * "/html/body/div[1]/div/div/div[3]/div[2]/form/button[1]"));
		 * 
		 * userName.sendKeys(validUserName); password.sendKeys(validPassword);
		 * signin.click(); Thread.sleep(2000);
		 * 
		 * String expectedUrl = driver.getCurrentUrl(); SoftAssert objSoftAssert = new
		 * SoftAssert(); objSoftAssert.assertEquals(actualUrl, expectedUrl);
		 * objSoftAssert.assertAll();
		 */
	}

	@Test(priority = 3, enabled = true)
	public void LoginValidUserNameVerify() throws InterruptedException {
		String validUserName = "admin";
		String validPassword = "12345666";

		WebElement userName = driver.findElement(By.name("user_name"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement signin = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/form/button[1]"));

		userName.sendKeys(validUserName);
		password.sendKeys(validPassword);
		signin.click();
		Thread.sleep(2000);

		String errorMsg = driver.findElement(By.xpath("//div[@class='error_login']/div[@class='alert alert-danger']"))
				.getText();

		System.out.println(errorMsg);
		String expectedErrorMsg = "username or password information doesn't exist!";
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(errorMsg, expectedErrorMsg);
		objSoftAssert.assertAll();
	}

	@Test(priority = 4, enabled = false)
	public void LoginInValidUserNameVerify() throws InterruptedException {
		String inValidUserName = "abcge";
		String validPassword = "123456";

		WebElement userName = driver.findElement(By.name("user_name"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement signin = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/form/button[1]"));

		userName.sendKeys(inValidUserName);
		password.sendKeys(validPassword);
		signin.click();
		Thread.sleep(2000);

		String errorMsg = driver.findElement(By.xpath("//div[@class='error_login']/div[@class='alert alert-danger']"))
				.getText();

		System.out.println(errorMsg);
		String expectedErrorMsg = "username or password information doesn't exist!";
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(errorMsg, expectedErrorMsg);
		objSoftAssert.assertAll();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			objManager.screenShot();
		}
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		// init webdrivermanager
	    objManager = Webdrivermanager.getInstance();
	    //read excel
		Readexcel readExcel=new Readexcel();
		String urlFromExcel = readExcel.readExcelRowCloumn(1,0);
		String browserValue = readExcel.readExcelRowCloumn(1, 1);
		driver = objManager.launchbrowser(urlFromExcel, browserValue);
	}

}
