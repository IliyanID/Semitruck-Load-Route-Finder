package TruckingBot;

import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.*;

import javax.swing.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Point;

/*  TruckBot Assignment
    Author: Iliyan Dimitrov
    Date:   Jan 8, 2020
    Class:  CS163
    Email:  iliyan@s.colostate.edu */
public class YahooMail
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebElement element;
		driver.get("https://login.yahoo.com/?.src=ym&.lang=en-US&.intl=us&.done=https%3A%2F%2Fmail.yahoo.com%2Fd");
		element = driver.findElement(By.name("username"));
		element.sendKeys("iliyancool@yahoo.com");
		element.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		element = driver.findElement(By.name("password"));
		element.sendKeys("RainballFalls");
		element.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.get("https://mail.yahoo.com/d/folders/1?.src=fp&guce_referrer=aHR0cHM6Ly9sb2dpbi55YWhvby5jb20v&guce_referrer_sig=AQAAADO3drJb5Pys4rUxMRNrPTf8PhQh9c2neifbSs6VfmIa3ShI4wadsf9SoMxB1Mu-oq1mCR3qLtwcpqFcKZIxEPalhrIK87veFnEE1l9klsPWfu8VlJ8wvMf7msoMf_4-zJQMsjDOvwq-Tc1yCSf1C2Wth2uDo8GaO0KZ-Veq1BqA");
		Thread.sleep(2000);
		
	
			
			List<WebElement> elements = driver.findElements(By.xpath("//span[@data-test-id = 'message-subject']"));
			for(WebElement data:elements)
				if(data.getText().equals("Your Email Verification Code"))
				{
					data.click();
					Thread.sleep(2000);
					element = driver.findElement(By.xpath("//td[@valign='top']/table/tbody/tr/td/h3"));
					System.out.println(element.getText());
					driver.close();
					break;
					
				}
		
	}
}
