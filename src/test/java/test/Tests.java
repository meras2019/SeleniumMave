package test;


import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.ScreenShooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;


public class Tests {

	private WebDriver driver;
	ArrayList<String> tabs;
		
	@BeforeMethod
	
	public void setUp(){
		
		DesiredCapabilities caps =new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().window().fullscreen();
		driver.manage().window().setSize(new Dimension(800,600));
		driver.navigate().to("http://newtours.demoaut.com/");
		JavascriptExecutor javaScript = (JavascriptExecutor)driver;
		String google="window.open('http://www.google.com')";
		javaScript.executeScript(google);
		tabs=new ArrayList<String>(driver.getWindowHandles());
		
		Helpers helper = new Helpers();
		helper.TimeSeconds(2);	
	}
	
	//@Test
	public void Login_uno(){
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.Login("user", "user");
		pageLogon.assertLogonPage();
		
	}
	
	//@Test
	public void Login_dos(){
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation =new PageReservation(driver);
		pageLogin.Login("mercury", "mercury");
		pageReservation.assertPage();
		
	}
	
	@Test
	public void Login_tres(){
		WebDriverManager.setWindowSize(driver, "maximized");
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com");
		driver.switchTo().window(tabs.get(0));
		
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation =new PageReservation(driver);
		pageLogin.Login("mercury", "mercury");
		pageReservation.assertPage();
		pageReservation.passengerDrop(2);
		pageReservation.selectfronPort(3);
		pageReservation.selectToPrt("Zurich");
	}
	
	//@Test
	public void cantidadCampos(){
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result){
		if(!result.isSuccess()){
			ScreenShooter.takeScreenshot("Error", driver);
		}
		
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
		
	}
}
