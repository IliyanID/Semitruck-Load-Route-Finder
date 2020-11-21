package TruckingBot;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*  TruckBot Assignment
    Author: Iliyan Dimitrov
    Date:   Jan 3, 2020
    Class:  CS163
    Email:  iliyan@s.colostate.edu */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class SeleniumTesting
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Iliyan\\eclipse-CS163\\TruckBot\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.google.com/");
        //WebDriver driver2 = new ChromeDriver();
        //driver2.get("https://www.google.com/maps");
     
        
       
       // Robot robot = new Robot();
		/*robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_TAB);*/
		while(true)
		{
        try
        {
        while(true)
        {
        for(int i = 0; i < 100; i++)
        {
		WebElement element = driver.findElement(By.name("q"));
		WebElement element2 = driver2.findElement(By.name("q"));
		element.sendKeys(String.valueOf(i));
		element.submit();
		//element2.sendKeys(String.valueOf(i));
		//element2.sendKeys(Keys.ENTER);
        }
        }
        }
        catch(Exception e)
        {
        	try
        	{
        		WebElement element = driver.findElement(By.xpath("//*[@id=\"rc-anchor-container\"]/div[3]/div[1]/div/div"));
        		element.click();
        		Thread.sleep(10000);
        		
        	}
        	catch(Exception e1)
        	{
        		
        	}
        }
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
	}
	}
}
