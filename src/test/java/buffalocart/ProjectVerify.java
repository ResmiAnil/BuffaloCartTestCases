package buffalocart;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.Homepage;
import utility.Webdrivermanager;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class ProjectVerify {

	WebDriver driver;
	Webdrivermanager objManager;
	Homepage objHomePage;

	@Test(priority = 6, enabled = true)
	public void clickingProject() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.projectItem));
		objManager.click(objHomePage.projectItem, driver);
	}

	@Test(priority = 7)
	public void enterProjectNo() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.newProject));
		objManager.click(objHomePage.newProject, driver);
		// for debug
		WebElement projectNo = driver.findElement(By.name("project_no"));
		System.out.println(projectNo.getText());
	}

	@Test(priority = 8)
	public void verifyProjectdropdown() throws InterruptedException {
		WebElement projectName = driver.findElement(By.name("project_name"));
		projectName.sendKeys("Remytest");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='select2-selection__rendered']")));

		WebElement dropdownSelectClient = driver.findElement(By.xpath("//span[@class='select2-selection__rendered']"));
		dropdownSelectClient.click();
		objManager.setSelection(4);
		Thread.sleep(2000);
	}

	@Test(priority = 9)
	public void verifyProjectProgress() {

		objManager.showProgress(30, objHomePage.progressBar);

		objManager.click(objHomePage.billingType, driver);
		// selecting 4th item
		objManager.setSelection(4);
	}

	@Test(priority = 10)
	public void verifyProjectDate() throws InterruptedException {
		objHomePage.startDate.sendKeys("2021-07-27");
		objHomePage.endDate.sendKeys("2021-07-31");
		Thread.sleep(2000);
	}

	@Test(priority = 11)
	public void verifyProjectPrice() {
		String fp = objHomePage.fixedPrice.getAttribute("placeholder");
		System.out.println("fixed price is " + fp);
		String demo = objHomePage.demoUrl.getAttribute("placeholder");
		System.out.println("Demo url is " + demo);
	}

	@Test(priority = 12)
	public void verifyProjectStatus() throws InterruptedException {
		// WebElement status =
		// driver.findElement(By.xpath("(//span[@class='select2-selection__rendered'])[3]"));
		objManager.click(objHomePage.status, driver);

		objManager.setSelection(4);

		objHomePage.description.sendKeys("Test Description");

		Thread.sleep(2000);
	}

	@Test(priority = 13)
	public void verifyProjectDetailSave() throws InterruptedException {
		objManager.click(objHomePage.projectSave, driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.projectItem));
		objManager.click(objHomePage.projectItem, driver);
		Thread.sleep(2000);

		List<WebElement> col = driver.findElements(By.xpath("//*[@id=\"DataTables\"]/thead/tr/th"));
		System.out.println("col number is" + col.size());

		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[1]"));
		System.out.println("row number is" + rows.size());

		// WebElement nextButton =
		// driver.findElement(By.xpath("//*[@id=\"DataTables_next\"]"));
		// finding table item
		boolean resultFindData = findTableData();
		if (resultFindData) {
			System.out.println("Element Found");
		} else {
			System.out.println("Element Not Found!!!");
		}

		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(resultFindData, true);
		objSoftAssert.assertAll();
	}

	public boolean findTableData() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[1]"));

		for (int i = 1; i <= rows.size(); i++) {

			WebElement projectName = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 2 + "]"));
			System.out.println("Project Name = " + projectName.getText());

			WebElement projectShell = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 3 + "]"));
			System.out.println("Project Shell=" + projectShell.getText());

			WebElement projectEndDate = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 4 + "]"));
			System.out.println("Project EndDate= " + projectEndDate.getText());

			WebElement projectStatus = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 6 + "]"));
			System.out.println("Status = " + projectStatus.getText());

			if (projectName.getText().contains("Remytest") && projectShell.getText().contains("Shell")
					&& projectEndDate.getText().contains("07.31.2021") && projectStatus.getText().contains("Cancel")) {
				return true;
			}

		}
		return false;
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		// get instance of driver manager
		objManager = Webdrivermanager.getInstance();
		// get driver
		driver = objManager.getDriver();
		// initialize the home page class
		objHomePage = new Homepage(driver);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			objManager.screenShot();
		}
	}
}
