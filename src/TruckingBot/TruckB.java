package TruckingBot;

import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TruckB
{
	double price,PPM,distance,load_weight,priceChange_perHour,profitPerDay;
	String company_name,contact_info,origin,destination,load_type,pickup_date;
	
	TruckB(double price, double PPM, double distance,double load_weight,double priceChange_perHour,double profitPerDay, String hour_last_price_change, String company_name, String origin, String destination, String contact_info, String load_type,String pickup_date)
	{
		if(!(this.load_type == null))
		{
		if(!this.load_type.equals("fr") || !this.load_type.equals("rz"))
		{
			this.price = price;
			this.PPM = PPM;
			this.distance = distance;
			
		}
		}
		else
		{
			this.price = -1;
			this.PPM = -1;
			this.distance = -1;
			
		}
		this.company_name = company_name;
		this.origin = origin;
		this.destination = destination;
		this.load_type = load_type;
		this.pickup_date = pickup_date;
		this.load_weight = load_weight;
		this.priceChange_perHour = priceChange_perHour;

		this.profitPerDay = profitPerDay;
	}
	
	public static void main(String[] args) throws Exception
	{
		String homeAdress = "Denver, CO";
		int possiblemilesperday = 500;
      
		System.setProperty("webdriver.chrome.driver","src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebElement element;
        while(true)
        {
        	try
        	{
        		driver.get("https://power.dat.com/login?ReturnUrl=%2f");

        		element = driver.findElement(By.xpath("//input[@id = 'username']"));
        		element.sendKeys("vdimitrov");
        		element = driver.findElement(By.xpath("//input[@id = 'password']"));
        		element.sendKeys("parker4321");

        		element.submit();
        		break;
        	}
        	catch(Exception e) {}
        }

        System.out.println("Succesful Login Proceeding to Loads Page");
        driver.get("https://power.dat.com/search/loads");
        		
        		
        ArrayList<TruckB> current_location = getJobs(driver,"origin",homeAdress);        		
       	System.out.println("Succesfully finished getting origin Jobs");

  		ArrayList<TruckB> future_location = getJobs(driver,"destination",homeAdress); 
  		System.out.println("Succesfully finished Getting destination jobs");
        	
  		driver.close();
   		ArrayList<ArrayList<TruckB>> potential_jobs = new ArrayList<ArrayList<TruckB>>();
		ArrayList<TruckB> temp = new ArrayList<>();
        		
  		for(int i = 0; i < current_location.size(); i++)        		
        			
  			for(int j = 0; j < future_location.size();j++)        			
  			{	
 
  							temp.clear();
  							temp.add(future_location.get(j));
  							temp.add(current_location.get(i));
  							potential_jobs.add(temp);
  			}

        		
        System.out.println("Sucesfully finished getting " + potential_jobs.size() + " potential jobs");
        
        double largestPPM = -1;
        
        TruckB[] confirmed_job = new TruckB[2];
   		for(int i = 0; i< potential_jobs.size();i++)
   		{
   			if(potential_jobs.get(i).get(0).PPM + potential_jobs.get(i).get(1).PPM > largestPPM )
   			{
   				confirmed_job[0] =  potential_jobs.get(i).get(0);
   				confirmed_job[1] =  potential_jobs.get(i).get(1);
   				largestPPM = potential_jobs.get(i).get(0).PPM + potential_jobs.get(i).get(1).PPM;
   			}        			
        }
   		
   		System.out.println("\n\n" + confirmed_job[0] + "\n\n" + confirmed_job[1]);
        		
        					       
       
}
	
	boolean parseData(WebDriver driver,int i,String html)
	{
		try
		{
			Document doc = Jsoup.parse(html);
			String pickup_date =  doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.avail").text();	
			String load_type =    doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.truck").text();
			String origin =       doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.origin").text();
			String distance=      doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.trip").text().replace(",", "");
			String destination =  doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.dest").text();
			String company_name = doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.company.dropdown").text();
			String contact_info = doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.contact").text();
			String load_weight =  doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.weight").text().replace(",", "");
			String price =        doc.select("#searchResults > div.fixed-table-container-inner.groupsClosed > table > tbody:nth-child(" + (i+1) +") > tr > td.rate").text().replace(",", "").replace("$", "");
		
			if(price.contains("—"))
			return false;	//price = "0";
		
		
		
			this.pickup_date = pickup_date;
			this.price = Double.valueOf(price);
			this.distance = Double.valueOf(distance);
			this.load_type = load_type;
			this.origin = origin;
			this.destination = destination;
			this.company_name = company_name;
			this.contact_info = contact_info;
			this.load_weight = Double.valueOf(load_weight);
			this.PPM = Double.valueOf(price) / Double.valueOf(distance);
		
			return true;
		}
		catch(Exception e) 
		{
			return false;
		}
	}
	 
	public String toString()
	{
			String content = "\nPick up date: "  + this.pickup_date + "\n";
			content += "Price: "  + this.price + "\n"; 
			content += "Distance: "  + this.distance + "\n"; 
			content += "Load Type:  "  + this.load_type + "\n"; 
			content += "Origin: "  + this.origin + "\n"; 
			content += "Destination: "  + this.destination + "\n"; 
			content += "Company Name: "  + this.company_name + "\n"; 
			content += "Contact Information: "  + this.contact_info + "\n"; 
			content += "Load Weight: "  + this.load_weight + "\n"; 
			content += "Price Per Mile: "  + this.PPM + "\n"; 
			return content;
	}
	 
	static ArrayList<TruckB> getJobs(WebDriver driver,String preferance, String origin) throws Exception
	{	
		 ArrayList<TruckB> jobs = new ArrayList<>();
		 while(true)
	        {
			 	System.out.println("Begining to get Jobs");
			 	checkSelected_origin_destination(driver,preferance,origin);
			 	System.out.println("Confirmed search selection");
	        	WebElement element;
	    		element = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[2]/table/thead/tr[2]/th/div/span")); // Gets the total results
	    		int result = Integer.valueOf((element.getText().substring(0,element.getText().indexOf(" "))).replace(" ", "")); // extract the number of total results
	    		result = (result > 1000) ? 1000 : result; //if total results is greater that 1000 it set to 1000 since 1000 is the max
	        	try
	        	{
	        		element = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[2]/table/tbody[" + result + "]")); //Checks to see if it can access all the results. If not it will fail and scroll down

	        		try
	        		{	  
	        			
	        			String html = driver.getPageSource();
	        			
	        			for(int i = 1;i <= result + 3; i++)
	        			{
	        				checkSelected_origin_destination(driver,preferance,origin);
	    					TruckB o = new TruckB(0,0,0,0,0,0,"","","","","","","");
	        				    					
	        					if(o.parseData(driver,i,html))
	        					{        							        							
	        						jobs.add(o);    							     					       						       					
	        					}
	        			} 
	        			return jobs;
	        		}
	        		catch(Exception e)
	        		{
	        			System.out.println("Code has failed retrying...");
	        			return jobs;
	        		}
	        	}
	        	catch(Exception e1)
	        	{
	        		
	        		
	        		int i = 1;
	        		while(true)
	        		{
	        		try
	        		{
	        			JavascriptExecutor js = ((JavascriptExecutor) driver);
	        			for(; i <= result;i += 20)
	        			{
	        				System.out.println("Scrolling down page to " + i + "load");
	        				element = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[2]/table/tbody[" + i + "]"));//Keeps selecting every tenth entry 
	        				js.executeScript("arguments[0].scrollIntoView();", element); //Scroll down until it can view every tenth entry
	        			}
	        			break;
	        		}
	        		catch(Exception e2) {}
	        		}
	        		
	        		Thread.sleep(100);       		  
	    			
	        		try
	    			{
	        			System.out.println("Expanding Collapsed Loads");
	    				element = driver.findElement(By.xpath("/a[@class  = 'collapsed']")); // Checks to see if extra result is collapsed
	    				element = driver.findElement(By.xpath("//*[@id='similarMatchesHeader']/tr/td")); //If it is collapsed it will expand the list
	    				element.click(); //Expands the similar results
	    				Thread.sleep(2000);
	    			}
	    			catch(Exception e3){}
	        	}
	        	
	        }
	}
	
	static void checkSelected_origin_destination(WebDriver driver,String preferance,String origin) throws Exception
	{

		WebElement element = null;
		 try
		 {
			 if(preferance.equals("origin"))
				 element = driver.findElement(By.xpath("//tbody[@class = 'searchItem ng-scope isView currentSearch']/tr/td[@class = 'origin ng-binding']"));
			 if(preferance.equals("destination"))
				 element = driver.findElement(By.xpath("//tbody[@class = 'searchItem ng-scope isView currentSearch']/tr/td[@class = 'dest ng-binding']"));
		 
			 if(!element.getText().equals(origin))
			 {
			 
				 ArrayList <WebElement> elements = null;
				 if(preferance.equals("origin"))
					 elements = (ArrayList<WebElement>) driver.findElements(By.xpath ("//td[@class = 'origin ng-binding']")); //Selects all the origins in the search bar
				 if(preferance.equals("destination"))
					 elements = (ArrayList<WebElement>) driver.findElements(By.xpath ("//td[@class = 'dest ng-binding']")); //Selects all the origins in the search bar

				 for(int i = 0; i < elements.size(); i++)
				 {
					 element = elements.get(i);
					 if(element.getText().equals(origin))
					 {
						 element.click();
						 Thread.sleep(2000);
						 break;
					 }
				 }  
     		
				 element = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[2]/table/thead/tr[2]/th/div/span")); // Gets the total results
				 int result = Integer.valueOf((element.getText().substring(0,element.getText().indexOf(" "))).replace(" ", "")); // extract the number of total results
				 result = (result > 1000) ? 1000 : result; //if total results is greater that 1000 it set to 1000 since 1000 is the max
				 int i = 1;
				 while(true)
				 {
					 try
					 {
						 JavascriptExecutor js = ((JavascriptExecutor) driver);
						 for(; i <= result;i += 20)
						 {
							 element = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[2]/table/tbody[" + i + "]"));//Keeps selecting every tenth entry 
							 js.executeScript("arguments[0].scrollIntoView();", element); //Scroll down until it can view every tenth entry
						 }
						 break;
					 }
					 catch(Exception e2) {}
				 }
    		
				 Thread.sleep(100);       		  
			
				 try
				 {
					 element = driver.findElement(By.xpath("/a[@class  = 'collapsed']")); // Checks to see if extra result is collapsed
					 element = driver.findElement(By.xpath("//*[@id='similarMatchesHeader']/tr/td")); //If it is collapsed it will expand the list
					 element.click(); //Expands the similar results
					 Thread.sleep(2000);
					 System.out.println("Expanded Extra Loads");
				 }
				 catch(Exception e3){}    		
			 }
		 }
		 catch(Exception e)
		 {
			 System.out.println("Attemptin to Find Origin Selection");
			 ArrayList <WebElement> elements = (ArrayList<WebElement>) driver.findElements(By.xpath ("//td[@class = 'origin ng-binding']"));
			 try
			 {
				 element = elements.get(elements.size() - 1);
				 element.click();
			 }
			 catch(Exception e1)
			 {
				 checkSelected_origin_destination(driver,preferance,origin);
			 }
			 checkSelected_origin_destination(driver,preferance,origin);
		 }		 
	 }	
}