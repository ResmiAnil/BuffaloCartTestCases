package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	WebDriver driver;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[5]/a/span")
	public WebElement projectItem;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[3]/div/div[2]/ul/li[2]/a")
	public WebElement newProject;

	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[4]/div/div[1]/span")
	public WebElement progressBar;

	@FindBy(how = How.XPATH, using = "(//span[@class='select2-selection__rendered'])[2]")
	public WebElement billingType;

	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[5]/div/div/input")
	public WebElement startDate;
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[6]/div/div/input")
	public WebElement endDate;
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[8]/div/input")
	public WebElement fixedPrice;
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[12]/div/input")
	public WebElement demoUrl;
	@FindBy(how = How.XPATH, using = "(//span[@class='select2-selection__rendered'])[3]")
	public WebElement status;
	@FindBy(how = How.XPATH, using = "//*[@id='create']/form/div/div[3]/div[1]/div/div/div[6]")
	public WebElement description;

	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[3]/div[2]/button")
	public WebElement projectSave;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[7]/a/span")
	public WebElement bugItem;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[3]/div/div[2]/ul/li[2]/a")
	public WebElement newBugs;
	@FindBy(how = How.XPATH, using = "//span[@role='combobox']//span[1]")
	public WebElement reporter;
	@FindBy(how = How.NAME, using = "priority")
	public WebElement priority;
	@FindBy(how = How.NAME, using = "severity")
	public WebElement severity;

	@FindBy(how = How.XPATH, using = "//div[@class='note-editable']")
	public WebElement bugDescription;

	@FindBy(how = How.XPATH, using = "(//div[@class='note-editable'])[2]")
	public WebElement bugReproductability;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[6]/a/span")
	public WebElement taskItem;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[4]/div/div[2]/ul/li[2]/a")
	public WebElement newTask;
	@FindBy(how = How.NAME, using = "task_start_date")
	public WebElement taskStartDate;
	@FindBy(how = How.NAME, using = "due_date")
	public WebElement taskDueDate;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'ui-slider-handle ui-corner-all')]")
	public WebElement taskProgressBar;

	@FindBy(how = How.NAME, using = "task_status")
	public WebElement taskStatus;

	@FindBy(how = How.XPATH, using = "//*[@id=\"assign_task\"]/div/div/form/div[11]/div/div/div[6]")
	public WebElement taskDescription;

	@FindBy(how = How.XPATH, using = "//*[@id=\"assign_task\"]/div/div/form/div[15]/button")
	public WebElement saveTaskBtn;

	@FindBy(how = How.XPATH, using ="/html/body/div[1]/section/div/div[2]/div/div[4]/div/div[2]/ul/li[1]/a")

	public WebElement allTaskbtn;

}
