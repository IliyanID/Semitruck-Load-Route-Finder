package TruckingBot;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*  Trading Bot Assignment
    Author: Iliyan Dimitrov
    Date:   Jan 22, 2020
    Class:  CS163
    Email:  iliyan@s.colostate.edu */
public class robinhood
{
	public static void main(String[] args) throws Exception
	{	
		String robinhood_username = "kevinzhu380@gmail.com";
		String robinhood_password = "bitcoinENERGY30";
		String email_username = "TradingRobinhood@yahoo.com";
		String email_password = "Dcsd243158";	
		
		System.setProperty("webdriver.chrome.driver","src\\chromedriver.exe");
		WebDriver email = new ChromeDriver();
		email.manage().window().maximize();
		email.get("https://login.yahoo.com/?.src=ym&.lang=en-US&.intl=us&.done=https%3A%2F%2Fmail.yahoo.com%2Fd");
		
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
			
		driver.get("https://robinhood.com/login");
		WebDriverWait wait = new WebDriverWait(driver,2000);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
			        
		// Log into RobinHood			        
		element.sendKeys(robinhood_username);
			        
		element = driver.findElement(By.name("password"));
		element.sendKeys(robinhood_password);
	    element.sendKeys(Keys.ENTER);
			        
			       
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='react_root']/div/div[1]/div[2]/div/div[2]/div/div/div/footer/div[2]")));			        
		element.click();
			        
		//Gets Confirmation Code and Inputs it
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("response")));						 
		element.sendKeys(emailConf(email,email_username,email_password));
		element.sendKeys(Keys.ENTER);
			       
		//Goes to Options Spread
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("  //*[@id='react_root']/div/main/div[2]/div/div/div/div/main/div/div[2]/div/div/section[1]/a")));
		System.out.println("\n" + element.getText());
		element.click();
			       
		//Monitors Total Percent Return
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class = 'table']/tbody/tr/td/span/span[2]")));
		String url = driver.getCurrentUrl();
			       
		String stringData = "";
			       
		//Only Updates when Total Percent Return Changes
		while(true)
		{			    	   
			try
			{
				if(!driver.getCurrentUrl().equals(url))
					driver.get(url);
			    else
			    {
			    	element = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div/div/div/div/div/div[1]/section[2]/div/div[1]/table/tbody/tr[3]/td[3]/span/span[2]"));
			    	String currentData = element.getText().replace("(", "").replace(")", "").replace("%", "");
			    	if(!stringData.equals(currentData))
			    	{
			    		stringData = currentData;
			    		Double doubleData = Double.valueOf(stringData);
			    		if(doubleData <= -25)
			    		{
			    			if(closeOrder(driver,wait));	
			    			{
			    				System.out.println("Warning Over 25% Loss: " + stringData + "%");
			    				break;
			    			}
			    		}
			    			
			    	}
			    			   
			    }
			}
			catch(Exception e) {}			    	 
		}		
	}
	private static boolean closeOrder(WebDriver driver,WebDriverWait wait) throws Exception
	{
		try
		{
		WebElement element;
		/*Thread.sleep(10000);
		WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div/div/div/div/div/div[1]/section[2]/div/div[1]/table/tbody/tr[3]/td[3]/span/span[2]"));
		String currentData = element.getText().replace("(", "").replace(")", "").replace("%", "");
		Double doubleData = Double.valueOf(currentData);
		if(doubleData > -25)
			return false;*/
		System.out.println("Get out");
		element = driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div/div/div/div/div[1]/section[2]/div/div[2]/table/tbody/tr[1]/td[3]"));
		Double contracts = Math.abs(Double.valueOf(element.getText()));
		element = driver.findElement(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div/div/div/div/div[1]/section[2]/div/div[1]/header/h2"));
		Double contract_value = (Double.valueOf(element.getText().replace("$", ""))/100) - .01;
		element = driver.findElement(By.xpath("//input[@id = 'OptionOrderForm-FormFields-limitPrice']"));
		element.sendKeys(String.valueOf(contract_value));
		System.out.println("Get out 1");
		element = driver.findElement(By.xpath("//input[@id = 'OptionOrderForm-FormFields-quantity']"));
		element.sendKeys(String.valueOf(contracts));
		element.sendKeys(Keys.ENTER);
		System.out.println("Get out 2");
		Thread.sleep(2000);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"react_root\"]/div/main/div[2]/div/div/div/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/div[2]/div/button")));
		element.click();
		}
		catch(Exception e) {e.printStackTrace();}
		return true;
	}
	
	private static String emailConf(WebDriver driver,String username,String password) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,2000);
		
		//Logs into Yahoo Mail
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		element.sendKeys(username);
		element.sendKeys(Keys.ENTER);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		element.sendKeys(password);
		element.sendKeys(Keys.ENTER);
		
		//Goes to Unread Mail
		driver.get("https://mail.yahoo.com/d/search/referrer=unread&keyword=is%253Aunread&accountIds=1&excludefolders=ARCHIVE");
		while(true)
		{
			try
			{
				//Try's to Click on Newest Mail
				element = driver.findElement(By.xpath("//*[@id='mail-app-component']/div/div/div[2]/div/div/div[2]/div/div[1]/ul/li[2]/a/div"));
				element.click();
				break;
			}
			catch(Exception e) {}
		}

		//Find Confirmation Code
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@valign = 'top']/table/tbody/tr/td/table/tbody/tr/td/h3")));
		element = driver.findElement(By.xpath("//td[@valign = 'top']/table/tbody/tr/td/table/tbody/tr/td/h3"));
		
		System.out.println("Confirmation Code: " + element.getText());
		String number = element.getText();
		
		//Try's 20 Times to Delete All New Mail
		driver.get("https://mail.yahoo.com/d/search/referrer=unread&keyword=is%253Aunread&accountIds=1&excludefolders=ARCHIVE");
		for(int i = 0; i < 20; i++)
		{
			try
			{
				//Selects all and Selects Delete
				element = driver.findElement(By.xpath("//*[@id=\"mail-app-component\"]/div/div/div[1]/div/div[1]/div/div/ul/li[1]"));
				element.click();
				element = driver.findElement(By.xpath("//*[@id=\"mail-app-component\"]/div/div/div[1]/div/div[2]/div/div[3]"));
				element.click();	
				break;
			}
			catch(Exception e) {}
		}

		driver.close();
		return number;
		
		
	
	}
}
