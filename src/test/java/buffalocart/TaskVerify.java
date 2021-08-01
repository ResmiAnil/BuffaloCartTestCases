package buffalocart;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.Homepage;
import utility.Webdrivermanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class TaskVerify {

	WebDriver driver;
	Webdrivermanager objManager;
	Homepage objHomePage;

	@Test(priority = 23)
	public void clickTaskPage() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.taskItem));

		objManager.click(objHomePage.taskItem, driver);
	}

	@Test(priority = 24)
	public void clicknewTask() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.newTask));
		objManager.click(objHomePage.newTask, driver);

	}

	@Test(priority = 25)
	public void enterTaskName() {
		WebElement TaskName = driver.findElement(By.name("task_name"));
		TaskName.sendKeys("tasktesting");
	}

	@Test(priority = 26)
	public void enterRelatedto() {
		WebElement relatedTo = driver.findElement(By.name("related_to"));
		Select objMultiselect = new Select(relatedTo);

		objMultiselect.selectByVisibleText("Leads");
	}

	@Test(priority = 27)
	public void entertaskDate() {

		objHomePage.taskStartDate.sendKeys("2021-07-28");
	}

	@Test(priority = 28)
	public void enterTaskDueDate() {

		objHomePage.taskDueDate.sendKeys("2021-07-31");
	}

	@Test(priority = 29)
	public void enterHrRate() {
		WebElement hrRate = driver.findElement(By.name("hourly_rate"));
		hrRate.sendKeys("12");
	}

	@Test(priority = 30)
	public void enterEstimatedHrs() {
		WebElement estimatedHr = driver.findElement(By.name("task_hour"));
		estimatedHr.sendKeys("24");
	}

	@Test(priority = 31)
	public void saveTaskDetails() throws InterruptedException {
		objManager.showProgress(40, objHomePage.taskProgressBar);

		WebElement status = driver.findElement(By.name("task_status"));
		Select objMultiselect = new Select(status);
		objMultiselect.selectByVisibleText("Deferred");

		objHomePage.taskDescription.sendKeys("success tasks");

		WebElement savetaskBtn = driver.findElement(By.xpath("//*[@id=\"assign_task\"]/div/div/form/div[15]/button"));
		if (savetaskBtn.isDisplayed())
			objManager.click(savetaskBtn, driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(objHomePage.taskItem));

		objManager.click(objHomePage.taskItem, driver);
		Thread.sleep(2000);
		objManager.click(objHomePage.allTaskbtn, driver);
		Thread.sleep(2000);

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

			WebElement taskName = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 2 + "]"));
			System.out.println("Task Name: " + taskName.getText());

			WebElement dueDate = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 3 + "]"));
			System.out.println("Due Date: " + dueDate.getText());

			WebElement status = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 4 + "]"));
			System.out.println("Status: " + status.getText());

			if (taskName.getText().contains("tasktesting") && dueDate.getText().contains("07.31.2021")
					&& status.getText().contains("Deferred")) {
				return true;
			}

		}
		return false;
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("----- Task Verify beforeClass------- ");
		// get instance of driver manager
		objManager = Webdrivermanager.getInstance();
		// get driver
		driver = objManager.getDriver();
		// initialize the home page class
		objHomePage = new Homepage(driver);
		System.out.println("----- Task Verify beforeClass Ends------- ");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
