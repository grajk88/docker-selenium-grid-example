package com.selenium.docker.example.tests;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestsChrome {

	public WebDriver driver;

	public String testURL = "https://www.saucedemo.com/index.html";

	@BeforeMethod
	public void setupTest() {

		try {

			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();

			desiredCapabilities.setPlatform(Platform.LINUX);
			
			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), desiredCapabilities);
			
			//System.setProperty("webdriver.chrome.driver" ,  "/Users/giridhar/eclipse-workspace/Zalenium/drivers/chromedriver");
			
			//driver = new ChromeDriver();

			
			driver.manage().window().maximize();

			driver.navigate().to(testURL);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@AfterMethod
	public void teardownTest() {

		driver.quit();

	}

	@Test
	public void titleTest() {

		String title = driver.getTitle();

		System.out.println("Page Title: " + title);

		AssertJUnit.assertEquals(title, "Swag Labs");
	}

	@Test
	public void loginTest() {

		String title = driver.getTitle();

		System.out.println("Page Title: " + title);

		AssertJUnit.assertEquals(title, "Swag Labs");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("user-name")).submit();

		WebElement element = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.id("inventory_filter_container")));

	}

	@Test
	public void logoutTest() {

		String title = driver.getTitle();

		System.out.println("Page Title: " + title);

		AssertJUnit.assertEquals(title, "Swag Labs");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("user-name")).submit();

		WebElement element = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.id("inventory_filter_container")));

		driver.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button")).click();

		WebElement logOutLink = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));

		driver.findElement(By.linkText("Logout")).click();

	}
}
