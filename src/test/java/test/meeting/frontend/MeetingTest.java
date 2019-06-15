package test.meeting.frontend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.PropertySource;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@PropertySource(value = { "classpath:selenium.properties" })
public class MeetingTest {
	
	private Properties properties;
	private WebDriver driver;
	private WebDriverWait wait;
	
	private String WEB_URL;
	
	private static final String meetingSubject = "TEST SUBJECT";
	private static final String meetingTime = "12122019" + Keys.TAB + "1212P";
	private static final String meetingLocation = "TEST LOCATION";
	private static final String meetingHost = "1";
	private static final String meetingUpdate = "TEST UPDATE";
	
	@BeforeClass
	public void beforeClass() throws IOException, FileNotFoundException {
		
		// Load the selenium.properties file
		String propertyFilePath = "src/main/resources/selenium.properties";
		BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
		properties = new Properties();
		properties.load(reader);
		reader.close();
		
		// Load the chrome web driver
		System.setProperty("webdriver.chrome.driver", properties.getProperty("web.driver.location"));
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		
		// Set the WEB_URL
		WEB_URL = properties.getProperty("web.url");
		
		// Go to meetings page
		driver.get(WEB_URL + "meeting");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	@Test(priority=1)
	public void createMeeting() {

		// Wait for button to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newMeetingButton")));
		driver.findElement(By.id("newMeetingButton")).click();
		
		// Wait for the New Meeting form to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newMeetingSubject")));
		driver.findElement(By.id("newMeetingSubject")).sendKeys(meetingSubject);
		driver.findElement(By.id("newMeetingTime")).sendKeys(meetingTime);
		driver.findElement(By.id("newMeetingLocation")).sendKeys(meetingLocation);
		driver.findElement(By.id("newMeetingHost")).sendKeys(meetingHost);
		driver.findElement(By.id("newMeetingButton")).click();
		
		// Wait for AngularJS view to load all meetings view
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		
		// Try to find the meeting row
		WebElement meetingRow = findTestMeetingRow();
		Assert.assertNotNull(meetingRow);
		
	}
	
	@Test(priority=2)
	public void updateMeeting() {

		// Wait for meeting rows to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		
		// Try to find the test meeting row
		WebElement meetingRow = findTestMeetingRow();
		
		// Fail the test if the meetingRow does not exist
		Assert.assertNotNull(meetingRow);
		
		// Edit the meeting
		meetingRow.findElement(By.className("editMeetingButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("updateMeetingTable")));
		
		// Update the location of the meeting
		WebElement meetingLocation = driver.findElement(By.id("selectedMeetingLocation"));
		meetingLocation.clear();
		meetingLocation.sendKeys(meetingUpdate);
		driver.findElement(By.id("updateMeetingButton")).click();
		
		// Check if the update was made successfully
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		meetingRow = findTestMeetingRow();
		String location = meetingRow.findElement(By.className("meetingRowLocation")).getText();
		
		// Pass the test if the test row location has been updated
		Assert.assertTrue(meetingUpdate.equalsIgnoreCase(location));
		
	}
	
	@Test(priority=3)
	public void deleteMeeting() {

		// Wait for meeting rows to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		
		// Find the test meeting and select the delete button
		WebElement meetingRow = findTestMeetingRow();
		
		// Fail the test if meetingRow does not exist
		Assert.assertNotNull(meetingRow);
		
		// Click the delete button to delete the meeting
		meetingRow.findElement(By.className("deleteMeetingButton")).click();

		wait.until(ExpectedConditions.invisibilityOf(meetingRow));
		
		// Attempt to find the test meeting again
		meetingRow = findTestMeetingRow();
		
		// Pass if the test meeting was not found
		Assert.assertNull(meetingRow);
		
	}
	
	
	/**
	 * @return Table row where subject column = "TEST SUBJECT"
	 */
	public WebElement findTestMeetingRow() {
		
		// Find the test meeting
		List<WebElement> tableRows = driver.findElements(By.className("meetingRow"));
		WebElement meetingRow = tableRows.stream()
			.filter(row -> meetingSubject.equalsIgnoreCase(row.findElement(By.className("meetingRowSubject")).getText()))
			.findFirst()
			.orElse(null);
		
		// Return test meeting or null
		return meetingRow;
		
	}

}