package pages;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import helpers.Helpers;

public class PageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;

	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passwordField = By.name("password");
		loginButton = By.name("login");
		fields=By.tagName("input");
	}

	public void Login(String user, String pass) {
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passwordField).sendKeys(pass);
		driver.findElement(loginButton).click();
		/*
		File myScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(myScreenshot, new File ("LOGIN " + System.currentTimeMillis()+".png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void verifyFields(){
		List<WebElement> loginFields=driver.findElements(fields);
		System.out.println("La cantidad de campos son : " + loginFields.size());
		Assert.assertTrue(loginFields.size()==5);
	}
	
}
