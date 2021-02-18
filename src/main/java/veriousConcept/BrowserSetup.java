package veriousConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserSetup {
	WebDriver driver;
	
	String browser = null;
	String url=null;
	@BeforeClass
	public void readconfig() {
		
		Properties prop= new Properties();
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser =prop.getProperty("browser");
			url=prop.getProperty("url");
			System.out.println("Used Browser:"+ browser);	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
public void init() {
	
	if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver","Driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	else if(browser.equalsIgnoreCase("firefox")){
		System.setProperty("webdriver.gecko.driver","Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	}
	
	@Test
	
	public void logIn() {
		
		
		
		WebElement USER_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));
		
		USER_FIELD_ELEMENT.clear();
		USER_FIELD_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_FIELD_ELEMENT.clear();
		PASSWORD_FIELD_ELEMENT.sendKeys("abc123");
		SIGNIN_BUTTON_ELEMENT.click();
		
		

}
   @AfterMethod	
   public void teardown() {
	    driver.close();
	  driver.quit();
   }
}
