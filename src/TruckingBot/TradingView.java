package TruckingBot;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*  TruckBot Assignment
    Author: Iliyan Dimitrov
    Date:   Jan 5, 2020
    Class:  CS163
    Email:  iliyan@s.colostate.edu */
public class TradingView 
{
public static void main(String[] args) throws Exception 
{

	ArrayList<String> upTrend = new ArrayList<String>();
	ArrayList<String> downTrend = new ArrayList<String>();
	ArrayList<String> all = new ArrayList<String>();

	System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\Iliyan\\\\eclipse-CS163\\\\TruckBot\\\\src\\\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

        driver.get("https://www.tradingview.com/#signin");
        //Thread.sleep(4000);
        WebElement element;
       while(true)
       {
       try
       {
        element = driver.findElement(By.name("username"));
        element.sendKeys("kevinzhu380@gmail.com");
        element = driver.findElement(By.name("password"));
        element.sendKeys("KevinZhu380");
        
        break;
       }
       catch(Exception e)
       {
    	   Thread.sleep(100);
       }
       }
       element.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
       
        driver.get("https://www.tradingview.com/chart/Now3RA71/");
       
        int i = 1;

       boolean loop = true;



       while(loop) 
       {
     try 
     {
     
      element = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div["+ i +"]"));
      element.click();
           
      element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[1]/div/table/tr[3]/td[2]/div/div[2]/div/div[2]/div[2]/div[3]/div/div[1]/div"));
      String d = element.getText();
      element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[1]/div/table/tr[3]/td[2]/div/div[2]/div/div[2]/div[2]/div[3]/div/div[2]/div"));
      String k = element.getText();
      element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[1]/div/table/tr[1]/td[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]"));
      String stockName = element.getText();
      element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[1]/div/table/tr[1]/td[2]/div/div[2]/div[1]/div[2]/div/div[4]/div[2]"));
      String stockPrice = element.getText();
     
     
      double fast = Double.parseDouble(d);
      double slow = Double.parseDouble(k);
      String trend = "";
     
      if (fast>slow) 
      {
      trend = "Up Trend";
      upTrend.add(stockName);
      }
      else {
      trend = "Down Trend";
      downTrend.add(stockName);
      }
      all.add(stockName + " " + stockPrice + " " + trend);
      i++;
      // printing things out
      System.out.println();
      System.out.println(trend);
      System.out.println("Stock Name: " + stockName);
      System.out.println("Stock Price: " + stockPrice);
      System.out.println("Fast: "+d);
      System.out.println("Slow: "+k);
      System.out.println();
      
       }
     catch (Exception e) 
     {
    	 Thread.sleep(150);

    if(i >= 42)
    {
    	driver.close();
    	loop = false;
    }
      }
       
       
       
       }
	}
}
