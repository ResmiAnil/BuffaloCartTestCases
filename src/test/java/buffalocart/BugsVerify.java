package buffalocart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.Homepage;
import utility.Webdrivermanager;

public class BugsVerify {

	static String url = "https://erp.buffalocart.com/login";

	WebDriver driver;
	Webdrivermanager objManager;
	Homepage objHomePage;

	@Test(priority = 14)
	public void loginPage() throws InterruptedException {
		System.out.println("----- Bug Verify loginPage------- ");
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.bugItem));

		objManager.click(objHomePage.bugItem, driver);
	}

	@Test(priority = 15)
	public void clickbugs() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.newBugs));
		objManager.click(objHomePage.newBugs, driver);

	}

	@Test(priority = 16)
	public void enterIssue() {
		WebElement issueNo = driver.findElement(By.name("issue_no"));
		System.out.println(issueNo.getText());
		WebElement Bugtitle = driver.findElement(By.name("bug_title"));
		Bugtitle.sendKeys("testverify");

	}

	@Test(priority = 17)
	public void enterRelatedDropdown() {
		WebElement relatedTo = driver.findElement(By.id("check_related"));

		Select objMultiselect = new Select(relatedTo);

		objMultiselect.selectByVisibleText("Projects");
	}

	@Test(priority = 18)
	public void enterDataComboBox() {

		objManager.click(objHomePage.reporter, driver);
		objManager.setSelection(3);

	}

	@Test(priority = 19)
	public void selectPrioritydata() {

		Select objMultisel = new Select(objHomePage.priority);
		objMultisel.selectByVisibleText("Medium");

		Select objMultis = new Select(objHomePage.severity);

		objMultis.selectByVisibleText("Major");
	}

	@Test(priority = 20)
	public void enterDescription() throws InterruptedException {

		objHomePage.bugDescription.sendKeys("Test success11");

		objHomePage.bugReproductability.sendKeys("reproducatbility  test");

	}

	@Test(priority = 21)
	public void enterBugStatus() throws InterruptedException {
		WebElement bugStatus = driver.findElement(By.name("bug_status"));
		Select objMult = new Select(bugStatus);

		objMult.selectByVisibleText("Resolved");

		WebElement radioBtn = driver.findElement(By.xpath("//*[@id=\"border-none\"]/div/div[2]/label/span"));
		SoftAssert objSoftAssert = new SoftAssert();
		// radio displayed or not
		boolean displayed = radioBtn.isDisplayed();
		objSoftAssert.assertEquals(displayed, true);

		radioBtn.click();
		Thread.sleep(3000);
		WebElement checkBoxadmin = driver.findElement(By.xpath("//*[@id=\"permission_user_1\"]/div/div[1]/label/span"));
		if (checkBoxadmin.isDisplayed())
			checkBoxadmin.click();

		WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"assign_task\"]/div/div/form/div[13]/button"));

		if (saveButton.isEnabled())
			saveButton.click();

	}

	@Test(priority = 22)
	public void savingbugsDetails() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.bugItem));
		objManager.click(objHomePage.bugItem, driver);

		WebElement allBugs = driver
				.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[3]/div/div[2]/ul/li[1]/a"));
		allBugs.click();

		Thread.sleep(3000);

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
		// *[@id="DataTables"]

		for (int i = 1; i <= rows.size(); i++) {

			WebElement BugTitle = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 1 + "]"));
			System.out.println("Bug Title = " + BugTitle.getText());

			WebElement Severity = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 4 + "]"));
			System.out.println("Severity = " + Severity.getText());

			WebElement Reporter = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 5 + "]"));
			System.out.println("Reporter = " + Reporter.getText());

			if (BugTitle.getText().contains("testverify") && Severity.getText().contains("Major")
					&& Reporter.getText().contains("xty")) {
				return true;
			}

		}
		return false;
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("----- Bug Verify beforeClass------- ");
		// get instance of driver manager
		objManager = Webdrivermanager.getInstance();
		// get driver
		driver = objManager.getDriver();
		// initialize the home page class
		objHomePage = new Homepage(driver);
		System.out.println("----- Bug Verify beforeClass Ends------- ");
	}

}
