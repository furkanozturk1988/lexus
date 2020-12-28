package lexus;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lexus {

	public static void main(String[] args) throws InterruptedException {
		
System.setProperty("webdriver.chrome.driver", "/Users/furkanozturk/Documents/SeleniumFiles/Drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.lexus.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String title = "Experience Amazing";
		String privacy = "Privacy Hub";
		if(driver.getTitle().contains(title)) {
			System.out.println("Title is right, PASS");
		}else {System.out.println("Landed on wrong webpage, FAIL");}
		String parentWindow = driver.getWindowHandle();

	driver.findElement(By.xpath("//a[@href='https://privacy.toyota.com/']")).click();
	Set<String>windows = driver.getWindowHandles();
	for(String s : windows) {
		if(!s.equals(parentWindow)) {
			driver.switchTo().window(s);
		}
	}
	if(driver.getTitle().contains(privacy)) {
		System.out.println("Landed on privacy window, PASS");
	}else {
		System.out.println("not security page, FAIL");
	}
	String privacyWindow=driver.getWindowHandle();
	driver.findElement(By.xpath("//a[@href='privacy-hub/privacyright.html']")).click();
	
	String html = "https://privacy.toyota.com/privacy-hub/privacyright.html";
	
	if(driver.getCurrentUrl().equals(html)) {
		System.out.println("Landed on right web page, PASS");
	}else {System.out.println("not customer rights page, FAIL");}
	
	
	driver.switchTo().window(parentWindow);
	driver.findElement(By.xpath("//a[@href='/build-your-lexus/#!/']")).click();
	driver.findElement(By.xpath("//input[@id='zip-overlay']")).sendKeys("22182",Keys.ENTER);
	
	
	driver.findElement(By.xpath("//img[@src='/byl2014/pub-share/images/series/gs.png']")).click();
	WebElement price = driver.findElement(By.xpath("(//span[@class='title-price-med float-right'])[4]"));
	//System.out.println(price.getText());
	String priceInNumbers=price.getText().substring(1, 3) + price.getText().substring(4, 7);
	//System.out.println(priceInNumbers);
	int lexusPrice = Integer.parseInt(priceInNumbers);
	//System.out.println(lexusPrice);
	WebElement problem = driver.findElement(By.xpath("(//a[@href='javascript:void(0)'])[3]"));
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView(true);", problem);
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//span[@class='list-title'])[4]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[@id='total-price']")).click();
	WebElement fee = driver.findElement(By.xpath("(//span[@class='title-price-small'])[1]"));
	WebElement fee1 = driver.findElement(By.xpath("(//span[@class='title-price-small'])[2]"));
	WebElement fee2 = driver.findElement(By.xpath("(//span[@class='title-price-small'])[3]"));
	WebElement fee3 = driver.findElement(By.xpath("(//span[@class='title-price-small'])[4]"));
	//System.out.println(fee.getText());
	int f=Integer.parseInt(fee.getText().substring(1,3) + fee.getText().substring(4,7));
	int f1=Integer.parseInt(fee1.getText().substring(1,2));
	int f2=Integer.parseInt(fee2.getText().substring(1,2));
	int f3=Integer.parseInt(fee3.getText().substring(1,2) + fee3.getText().substring(3,6));
	String total=driver.findElement(By.xpath("//span[@id='total-price']")).getText();
	String total1=total.substring(0,2) + total.substring(3,6);
	int totalPrice=Integer.parseInt(total1);
	
	if(totalPrice==(f+f1+f2+f3)) {
		System.out.println("Price is right, PASS");
	}else {
		System.out.println("Price is wrong, FAIL");
	}
	driver.findElement(By.xpath("(//a[@href='javascript: void(0)'])[7]")).click();
	driver.findElement(By.xpath("((//ul[@class='list-thumbs'])[1]/li)[6]")).click();
	driver.findElement(By.xpath("//span[@id='total-price']")).click();
	List<WebElement>priceBreakDown = driver.findElements(By.xpath("//span[@class='title-price-small']"));
	List<String>priceBreakDownStr = new ArrayList<>();
	for(WebElement w: priceBreakDown) {
		priceBreakDownStr.add(w.getText());
	}
	//System.out.println(priceBreakDownStr);
	int m=Integer.parseInt(priceBreakDownStr.get(0).replace("$", "").replace(",", ""));
	int m1=Integer.parseInt(priceBreakDownStr.get(1).replace("$", ""));
	int m2=Integer.parseInt(priceBreakDownStr.get(2).replace("$", ""));
	int m3=Integer.parseInt(priceBreakDownStr.get(3).replace("$", "").replace(",", ""));
	int totalAll=Integer.parseInt(priceBreakDownStr.get(4).replace("$", "").replace(",", ""));
	if(totalAll==(m+m1+m2+m3)) {System.out.println("Total price is ok , PASS");}
	else {System.out.println("Total price doesnt match, FAIL");}
	
	driver.findElement(By.xpath("(//a[@href='javascript: void(0)'])[8]")).click();
	driver.findElement(By.xpath("//div[@class='list-total list-close byl-js-price-breakdown-close']//a")).click();
	driver.findElement(By.xpath("(//a[@class='byl-js-color'])[2]")).click();
	driver.findElement(By.xpath("(//a[@role='button'])[1]")).click();
	driver.findElement(By.xpath("(//a[@class='byl-js-opt-select addRemove'])[1]")).click();
	driver.findElement(By.xpath("//span[@id='total-price']")).click();
	
	String newTotalAmount = driver.findElement(By.xpath("//span[@id='total-amount']")).getText();
	//System.out.println(newTotalAmount);
	int newTotalAmount1=Integer.parseInt(newTotalAmount.replace(",", ""));
	String soundSystem=driver.findElement(By.xpath("(//span[@class='title-price-small'])[11]")).getText();
	//System.out.println(soundSystem);
	int soundSystem1=Integer.parseInt(soundSystem.replace("$", "").replace(",", ""));
	if(newTotalAmount1==(totalAll + soundSystem1 )) {
	System.out.println("Sound system added and it matches the total price , PASS");
	}
	else {System.out.println("Something wrong with the price , FAIL");}
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//span[@id='FooterLink']/div/a)[2]")).click();
	
	
	String summary = driver.findElement(By.xpath("//h2[@class='title-price']")).getText();
	int summary1=Integer.parseInt(summary.replace("$", "").replace(",", "").replace("*", ""));
	if(summary1==newTotalAmount1) {System.out.println("Summary price matches, PASS");}
	else {System.out.println("Summary price doesn't match, FAIL");}
	
	
	driver.findElement(By.xpath("//a[@href='purchase-inquiry.html']")).click();
	Thread.sleep(2000);
	Set<String>openWindows = driver.getWindowHandles();
	for(String s:openWindows) {
		if (!( s.equals(parentWindow) || s.equals(privacyWindow) )) {
			driver.switchTo().window(s);
		}
	}
	
	driver.findElement(By.xpath("//input[@name='firstname']")).click();
	driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");
	driver.findElement(By.xpath("//input[@name='lastname']")).click();
	driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Doe");
	driver.findElement(By.xpath("//input[@name='email']")).click();
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("kmlgnc66@gmail.com");
	driver.findElement(By.xpath("//input[@name='phone']")).click();
	driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("469-810-1244");
	driver.findElement(By.xpath("(//div[@class='check-label'])[2]")).click();
	WebElement radio = driver.findElement(By.xpath("//label[@for='64504']"));
	js.executeScript("arguments[0].click();", radio);
	driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	String message = "WE'LL BE IN TOUCH SHORTLY";
	WebElement confirm = driver.findElement(By.xpath("//div[@class='list-title-sub']"));
	if(confirm.getText().contains(message)) {
		System.out.println("CONGRATS! EVERYTHING LOOKS GOOD");
	}else {System.out.println("Something is wrong! Check the last step");}	
	
	}

}
