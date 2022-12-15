package testNGPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorMessage {
	WebDriver driver;
	
	@BeforeClass(description = "Start the chrome browser")
	public void startBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("=======Browser Started=======");
	}
	
	@Test(description = "This is Error message check test case")
	public void checkErrorMessage() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/auth/login"), "URL Doesn't Match");
		
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
		loginBtn.click();
		
		String errorMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/span")).getText();
		String actualErrorMsg = "Required";
		Assert.assertTrue(errorMessage.equals(actualErrorMsg), "Error message doesn't match !!");
		
		Thread.sleep(2000);
		
		System.out.println("Error check test case excuted successfully and error message is: "+errorMessage);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("======Browser Closed=======");
	}
}
