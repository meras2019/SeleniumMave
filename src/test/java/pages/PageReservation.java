package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import helpers.Helpers;

public class PageReservation {
private WebDriver driver;
private By titleText;
private By passengerDrop;
private By fromDrop;
private By toDrop;
	
	public PageReservation(WebDriver driver){
		this.driver=driver; 
		titleText=By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengerDrop=By.name("passCount");
		fromDrop=By.name("fromPort");
		toDrop=By.name("toPort");
		
	}
	
	public void assertPage(){
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Use our Flight Finder"));
	}
	
	public void passengerDrop(int cantidad){
		WebDriverWait wait= new WebDriverWait(driver,10);
		WebElement cantidadPasajero=wait.until(ExpectedConditions.presenceOfElementLocated(passengerDrop));
		Select selectpasajeros = new Select(cantidadPasajero);
		selectpasajeros.selectByVisibleText(Integer.toString(cantidad));
		Helpers helper = new Helpers();
		helper.TimeSeconds(3);
	}
	
	public void selectfronPort(int index){
		WebDriverWait wait= new WebDriverWait(driver,10);
		WebElement fromDep=wait.until(ExpectedConditions.presenceOfElementLocated(fromDrop));
		Select selectFrom = new Select(fromDep);
		selectFrom.selectByIndex(index);
		Helpers helper = new Helpers();
		helper.TimeSeconds(3);
	}
	
	public void selectToPrt(String city){
		WebDriverWait wait= new WebDriverWait(driver,10);
		WebElement ArriToPort=wait.until(ExpectedConditions.presenceOfElementLocated(toDrop));
		Select selectTo = new Select(ArriToPort);
		selectTo.selectByValue(city);
		Helpers helper = new Helpers();
		helper.TimeSeconds(3);
	}
}
