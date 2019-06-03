package com.meeting.frontend;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebAppTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private static final String WEB_URL = "http://localhost:8080/meeting-planner/";
	
	private static final String testEmail = "test@email.com";
	private static final String testPassword = "password";
	
	@BeforeClass
	public void beforeSuite() {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@AfterClass
	public void afterSuite() {
		driver.close();
	}
	
	@Test(priority=1)
	public void login() {
		// Navigate to login page
		driver.get(WEB_URL + "login");
		
		// Wait until login form is available
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailInput")));
		
		// Fill out and submit login form
		driver.findElement(By.id("emailInput")).sendKeys(testEmail);
		driver.findElement(By.id("passwordInput")).sendKeys(testPassword);
		driver.findElement(By.id("loginButton")).click();
		
		// Wait until the browser loads the expected page
		wait.until(ExpectedConditions.titleContains("Meeting Planner"));
		
		// Pass the test if the browser title is what we expect
		Assert.assertTrue("Meeting Planner".equalsIgnoreCase(driver.getTitle()));
	}
	
	@Test(priority=2)
	public void createMeeting() {
		
		// Go to meetings page
		driver.get(WEB_URL + "meeting");

		// Wait for button to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newMeetingButton")));
		driver.findElement(By.id("newMeetingButton")).click();
		
		// Wait for the New Meeting form to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newMeetingSubject")));
		driver.findElement(By.id("newMeetingSubject")).sendKeys("TEST SUBJECT");
		driver.findElement(By.id("newMeetingTime")).sendKeys("12122019" + Keys.TAB + "1212P");
		driver.findElement(By.id("newMeetingLocation")).sendKeys("TEST LOCATION");
		driver.findElement(By.id("newMeetingHost")).sendKeys("1");
		driver.findElement(By.id("newMeetingButton")).click();
		
		// Wait for AngularJS view to load all meetings view
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		
		// Try to find the meeting row
		WebElement meetingRow = findTestMeetingRow();
		Assert.assertNotNull(meetingRow);
		
	}
	
	@Test(priority=3)
	public void updateMeeting() {
		
		// Go to meetings page
		driver.get(WEB_URL + "meeting");

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
		meetingLocation.sendKeys("TEST UPDATE");
		driver.findElement(By.id("updateMeetingButton")).click();
		
		// Check if the update was made successfully
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		meetingRow = findTestMeetingRow();
		String location = meetingRow.findElement(By.className("meetingRowLocation")).getText();
		
		Assert.assertTrue("TEST UPDATE".equalsIgnoreCase(location));
		
	}
	
	@Test(priority=4)
	public void deleteMeeting() {
		
		// Go to meetings page
		driver.get(WEB_URL + "meeting");

		// Wait for meeting rows to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("meetingRow")));
		
		// Find the test meeting and select the delete button
		WebElement meetingRow = findTestMeetingRow();
		
		// Fail the test if meetingRow does not exist
		Assert.assertNotNull(meetingRow);
		
		// Click the delete button to delete the meeting
		meetingRow.findElement(By.className("deleteMeetingButton")).click();
				
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
			.filter(row -> "TEST SUBJECT".equalsIgnoreCase(row.findElement(By.className("meetingRowSubject")).getText()))
			.findFirst()
			.orElse(null);
		
		// Return test meeting or null
		return meetingRow;
		
	}

}