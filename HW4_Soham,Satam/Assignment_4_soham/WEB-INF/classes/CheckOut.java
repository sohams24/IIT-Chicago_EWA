import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    Utilities Utility = new Utilities(request, pw);

		Order order = new Order();
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request,pw);
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 

			//get the order product details	on clicking submit the form will be passed to submitorder page	
			int i = 1;
			String userName = session.getAttribute("username").toString();
			String orderTotal = request.getParameter("orderTotal");
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<form name ='CheckOut' action='Payment' method='post'>");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table  class='gridtable'><tr><td>Customer: </td><td>");
			pw.print(userName);
			pw.print("</td></tr></table>");
			pw.print("<table  class='gridtable'>");
			pw.print("<tr>");
			pw.print("<th>No.</th>");
			pw.print("<th>Item.</th>");
			pw.print("<th>Unit Price</th>");
			pw.print("<th>Discount</th>");
			pw.print("<th>Final Price</th>");
			pw.print("<th>Quantity</th>");
			pw.print("<th>Total Cost</th>");
			pw.print("<th>Rebate</th>");
			pw.print("<th>Final Cost</th>");
			pw.print("</tr>");
			// for each order iterate and display the order name price
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				// pw.print("<tr><td> Product Purchased:</td><td>");
				// pw.print(oi.getName()+"</td></tr><tr><td>");
				// pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				// pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				// pw.print("Product Price:</td><td>"+ oi.getPrice());
				// pw.print("</td></tr>");


				pw.print("<tr>"+
						"<td>"+i+".</td>"+
						"<td>"+oi.getName()+"</td>"+
						"<td> "+oi.getPrice()+"</td>"+
						"<td> "+oi.getDiscount()+"</td>"+
						"<td> "+(oi.getPrice() - oi.getDiscount())+"</td>"+
						"<td> "+oi.getQuantity()+"</td>"+
						"<td> "+((oi.getPrice()-oi.getDiscount())*oi.getQuantity())+"</td>"+
						"<td> "+oi.getRebate()+"</td>"+
						"<td> "+(((oi.getPrice()-oi.getDiscount())*oi.getQuantity())-oi.getRebate())+"</td>"+
						"</tr>");
				i++;
			}
			pw.print("<tr><th></th>");
			pw.print("<th>Total Order Cost</th><th></th><th></th><th></th><th></th><th></th><th></th><th>"+orderTotal);
			pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
			pw.print("</th></tr></table>");	

			pw.print("<br><br>");

			pw.print("<table><tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td>");
			pw.print("Customer name: </td>");
			pw.print("<td><input type='text' name='name'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td>");
			pw.print("Customer Age: </td>");
			pw.print("<td><input type='text' name='age'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td>");
			pw.print("Customer Occupation: </td>");
			pw.print("<td><input type='text' name='occupation'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr></tr><tr></tr>");
			pw.print("<tr><td>");
			pw.print("Street: </td>");
			pw.print("<td><input type='text' name='street'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr></tr><tr></tr>");
			pw.print("<tr><td>");
			pw.print("City: </td>");
			pw.print("<td><input type='text' name='city'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr></tr><tr></tr>");
			pw.print("<tr><td>");
			pw.print("State: </td>");
			pw.print("<td><input type='text' name='state'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr></tr><tr></tr>");
			pw.print("<tr><td>");
			pw.print("Zipcode: </td>");
			pw.print("<td><input type='text' name='zip'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr></tr><tr></tr>");
			pw.print("<tr><td>");
			pw.print("Credit/accountNo</td>");
			pw.print("<td><input type='text' name='creditCardNo'>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr><td></td></tr>");

			pw.print("<tr><td>");
			pw.print("Home Delivery/Instore pickup: </td>");
			pw.print("<td><input type='radio' id='del' name='delpick' value='del'>"+
					"<label for='del'>Home Delivery</label>"+
					"<input type='radio' id='pick' name='delpick' value='pick'>"+
					"<label for='pick'>Pickup</label>");
			pw.print("</td></tr>");

			pw.print("<tr><td></td></tr>");
			pw.print("<tr></tr></table>");

			pw.print("<table><tr><td>");
			pw.print("<p>For instore pickup, please select any one of the following store locations:</p> </td>");
			pw.print("<td><select name='storelocation' id='storelocation'>"+
						"<option value='Arlington Heights 60005'>Arlington Heights 60005</option>"+
						"<option value='Elk Groov Village 60007'>Elk Groov Village 60007</option>"+
						"<option value='Des Plaines 60016'>Des Plaines 60016</option>"+
						"<option value='Glenview 60026'>Glenview 60026</option>"+
						"<option value='Lake Zurich 60047'>Lake Zurich 60047</option>"+
						"<option value='Geneva 60134'>Geneva 60134</option>"+
						"<option value='Frankfort 60423'>Frankfort 60423</option>"+
						"<option value='Millbrook 60536'>Millbrook 60536</option>"+
						"<option value='Naperville 60564'>Naperville 60564</option>"+
						"<option value='Chicago 60606'>Chicago 60606</option>"+
						"<option value='Chicago 60616'>Chicago 60616</option>"+
					"</select>");
			pw.print("</td></tr>");
			pw.print("<tr></tr>");
			pw.print("<tr><td colspan='2'>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("</td></tr></table></form>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
		}
		catch(Exception e)
		{
			System.out.println("Inside checkout.java\n"+"Message: "+e.getMessage());
		}  			
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
