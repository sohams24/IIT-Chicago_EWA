import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhoneList")

public class PhoneList extends HttpServlet {

	/* Phone Page Displays all the Phones and their Information in Best Deal */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is Apple, samsung, OnePlus, motorola, or nokia */

		HashMap<String, Phone> hm = new HashMap<String, Phone>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.phones);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("apple"))
		   {
			 for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Apple"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Apple";
		   }
		   else if(CategoryName.equals("samsung"))
		    {
			for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Samsung";
			}
			else if(CategoryName.equals("motorola"))
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Motorola"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Motorola";
			}
            else if(CategoryName.equals("oneplus"))
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("OnePlus"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "OnePlus";
			}
            else if(CategoryName.equals("nokia"))
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Nokia"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Nokia";

			}
			
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the Phone and Phone information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Phones</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Phone> entry : hm.entrySet())
		{
			Phone phone = entry.getValue();
			System.out.println("Inside PhoneList.java");
			System.out.println("Hashmap: "+hm);
			for(Phone p : hm.values()){
				System.out.println(p.getAccessories());
			}
			System.out.println("ID: "+phone.getId());
			System.out.println("Name: "+phone.getName());
			System.out.println("Image: "+phone.getImage());
			System.out.println("Price: "+phone.getPrice());
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+phone.getName()+"</h3>");
			pw.print("<strong>$"+phone.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/phones/"+phone.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<label for='quantity'>Quantity: </label>"+
					"<input type='number' name='quantity' value='1' min='1'>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+phone.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
