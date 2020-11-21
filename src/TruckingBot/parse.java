package TruckingBot;
/*  TruckBot Assignment
    Author: Iliyan Dimitrov
    Date:   Jan 7, 2020
    Class:  CS163
    Email:  iliyan@s.colostate.edu */
public class parse
{
	public static void main(String[] args)
	{
		String string_data= "    10:24 01/07 PO F 52 Loveland, CO 1,162 Houston, TX —\r\n" + 
				"Hermann Transportation Services\r\n" + 
				"poweronly@hermanntds.com 53 5,000 $250.00 85 39";
		System.out.println(string_data + "\n");
		
	}
	boolean parseData(String string_data)
	{
		try
		{
		String cost,distance,pickup_date,load_type,origin,destination,company_name,contact_info,load_weight;

		if(string_data.contains("@"))
		{
			cost = string_data.substring(string_data.indexOf("$") + 1);
			cost = cost.substring(0,cost.indexOf(".") + 3);
			cost.replace(",", "");
			distance = string_data.substring(string_data.indexOf(",") + 5);
			distance = distance.substring(0,distance.indexOf(" "));
			distance = distance.replace(",", "");
			cost = cost.replace(",", "");
			
			pickup_date = string_data.substring(string_data.indexOf("/") - 2,string_data.indexOf("/") + 3);
			string_data = string_data.substring(string_data.indexOf("/") + 4);
			load_type = string_data.substring(0,2);

			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") +  1));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") +  1) + 1);
			origin = string_data.substring(0, string_data.indexOf(" ", string_data.indexOf(" ") + 1));
			string_data = string_data.substring(string_data.indexOf(" ", string_data.indexOf(" ") + 1)  + 1);
			
			destination = string_data.substring(string_data.indexOf(" ") + 1,string_data.indexOf("—") - 1);
			string_data = string_data.substring(string_data.indexOf("—") + 1);
			int newline = 0;
			for(int i = 0; i< string_data.length(); i++)
				if(string_data.charAt(i) == '\n')
					newline = i;
			
			company_name = string_data.substring(1,newline - 1);

			 
			string_data = string_data.substring(newline +1);
			contact_info = string_data.substring(0,string_data.indexOf(" ",string_data.indexOf(".") + 3));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") + 1) + 1);
		
			
			load_weight = string_data.substring(0,string_data.indexOf(" "));

		}
		
		else if(charRepeat(string_data,'(',1))
		{
			cost = string_data.substring(string_data.indexOf("$") + 1);
			cost = cost.substring(0,cost.indexOf(".") + 3);
			cost.replace(",", "");
			distance = string_data.substring(string_data.indexOf(",") + 5);
			distance = distance.substring(0,distance.indexOf(" "));
			distance = distance.replace(",", "");
			cost = cost.replace(",", "");
			
			pickup_date = string_data.substring(string_data.indexOf("/") - 2,string_data.indexOf("/") + 3);
			string_data = string_data.substring(string_data.indexOf("/") + 4);
			load_type = string_data.substring(0,2);

			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") +  1));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") +  1) + 1);
			origin = string_data.substring(0, string_data.indexOf(" ", string_data.indexOf(" ") + 1));
			string_data = string_data.substring(string_data.indexOf(" ", string_data.indexOf(" ") + 1)  + 1);
			
			destination = string_data.substring(string_data.indexOf(" ") + 1,string_data.indexOf("—") - 1);
			string_data = string_data.substring(string_data.indexOf("—") + 1);
			company_name = string_data.substring(1,string_data.indexOf("(") - 1);
			string_data = string_data.substring(string_data.indexOf("(") );
			contact_info = string_data.substring(0,string_data.indexOf(" ",string_data.indexOf(" ") + 1));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") + 1) + 1);
		
			
			load_weight = string_data.substring(string_data.indexOf(" "),string_data.indexOf(" ",string_data.indexOf(" ") + 1));
			
			
			
		}
		else
		{
			cost = string_data.substring(string_data.indexOf("$") + 1);
			cost = cost.substring(0,cost.indexOf(".") + 3);
			cost.replace(",", "");
			distance = string_data.substring(string_data.indexOf(",") + 5);
			distance = distance.substring(0,distance.indexOf(" "));
			distance = distance.replace(",", "");
			cost = cost.replace(",", "");
			
			pickup_date = string_data.substring(string_data.indexOf("/") - 2,string_data.indexOf("/") + 3);
			string_data = string_data.substring(string_data.indexOf("/") + 4);
			load_type = string_data.substring(0,2);

			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") +  1));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") +  1) + 1);
			origin = string_data.substring(0, string_data.indexOf(" ", string_data.indexOf(" ") + 1));
			string_data = string_data.substring(string_data.indexOf(" ", string_data.indexOf(" ") + 1)  + 1);
			
			destination = string_data.substring(string_data.indexOf(" ") + 1,string_data.indexOf("—") - 1);
			string_data = string_data.substring(string_data.indexOf("—") + 1);
			company_name = string_data.substring(1,string_data.indexOf("(") - 1);
			string_data = string_data.substring(string_data.indexOf("(") - 2 );
			contact_info = string_data.substring(2,string_data.indexOf(" ",string_data.indexOf(" ") + 1));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") + 1) + 1);
			contact_info += " "  + string_data.substring(0,string_data.indexOf(")") + 1);
			string_data = string_data.substring(string_data.indexOf(")"));
			string_data = string_data.substring(string_data.indexOf(" ",string_data.indexOf(" ") + 1));
			
			load_weight = string_data.substring(string_data.indexOf(" "),string_data.indexOf(" ",string_data.indexOf(" ") + 1));
			
			

		}
		load_weight = load_weight.replace(",", "");

		
		
		this.pickup_date = pickup_date;
		this.price = Double.valueOf(cost);
		this.distance = Double.valueOf(distance);
		this.load_type = load_type;
		this.origin = origin;
		this.destination = destination;
		this.company_name = company_name;
		this.contact_info = contact_info;
		this.load_weight = Double.valueOf(load_weight);
		this.PPM = Double.valueOf(cost) / Double.valueOf(distance);
		return true;
		
		}
		catch(Exception e)
		{
			System.out.println("Couldn't Parse Data");
			System.out.println(string_data);
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean charRepeat(String s, char c, int n)
	{
		char[] values = s.toCharArray();
		int repeat = 0;
		for(int i = 0; i < values.length; i++)
		{
			if(values[i] == c)
			{
				repeat++;
			}
		}
		if(repeat == n)
			return true;
		return false;
		
	}	
	public String toString()
	{
		String content = "Pick up date: "  + this.pickup_date + "\n";
		content += "Price: "  + this.price + "\n"; 
		content += "Distance: "  + this.distance + "\n"; 
		content += "Load Type:  "  + this.load_type + "\n"; 
		content += "Origin: "  + this.origin + "\n"; 
		content += "Destination: "  + this.destination + "\n"; 
		content += "Company Name: "  + this.company_name + "\n"; 
		content += "Contact Information: "  + this.contact_info + "\n"; 
		content += "Load Weight: "  + this.load_weight + "\n"; 
		content += "Price Per Mile: "  + this.PPM + "\n\n"; 
		return content;
	}
}
