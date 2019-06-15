package test.meeting.frontend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

	private Properties properties;
	private WebDriver driver;
	private WebDriverWait wait;
	
	private String WEB_URL;
	
	private static final String testEmail = "test@email.com";
	private static final String testPassword = "password";
	
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
		wait = new WebDriverWait(driver, 2); // TODO Change timeout value
		
		// Set the WEB_URL
		WEB_URL = properties.getProperty("web.url");
		
		// Navigate to login page
		driver.get(WEB_URL + "login");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	@Test(priority=1)
	public void loginFail() {
		
		// Wait until login form is available
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailInput")));

		// Clear login form
		clearLogin();
		
		// Fill out and submit login form
		driver.findElement(By.id("emailInput")).sendKeys(testEmail);
		driver.findElement(By.id("submitButton")).click();
		
		String errorMessage = driver.findElement(By.id("errorMessage")).getText();
		Assert.assertTrue(errorMessage.length() > 0);
		
	}
	
	@Test(priority=2)
	public void loginPass() {
		
		// Wait until login form is available
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailInput")));

		// Clear login form
		clearLogin();
		
		// Fill out and submit login form
		driver.findElement(By.id("emailInput")).sendKeys(testEmail);
		driver.findElement(By.id("passwordInput")).sendKeys(testPassword);
		driver.findElement(By.id("submitButton")).click();
		
		// Wait until the browser loads the expected page
		wait.until(ExpectedConditions.titleContains("Meeting Planner - Meetings"));
		
		// Pass the test if the browser title is what we expect
		Assert.assertTrue("Meeting Planner - Meetings".equalsIgnoreCase(driver.getTitle()));
		
	}
	
	private void clearLogin() {
		driver.findElement(By.id("emailInput")).clear();;
		driver.findElement(By.id("passwordInput")).clear();
	}
	
}
