import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.time.*;


@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		LocalDate orderDate = LocalDate.now();
		LocalDate receiveDate = orderDate.plusDays(14);
		System.out.println("Inside Payment.java");
		System.out.println("Today's date is: "+orderDate);
		System.out.println("2 weeks from now: "+receiveDate);


		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	


		String name = request.getParameter("name");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String delpick = request.getParameter("delpick");
		String storeId = request.getParameter("storelocation");

		String userAddress= name + '\n' + street + '\n' + city + '\n' + state + '\n'+ zip;
		String creditCardNo=request.getParameter("creditCardNo");

		// Order order = OrdersHashMap.orders.get(utility.username());
		// order.setName(name);
		// order.setStreet(street);
		// order.setCity(city);
		// order.setState(state);
		// order.setZipCode(zip);
		boolean pickup = false;
		if(delpick.equals("pick")){
			pickup = true;
		}

		// System.out.print("the user address is" +userAddress);
		// System.out.print(creditCardNo);
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,oi.getId(),oi.getPrice(),oi.getDiscount(),oi.getRebate(),oi.getQuantity(),street,city,state,zip,creditCardNo,pickup,storeId,orderDate, receiveDate);
			}

			//remove the order details from cart after processing
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");  
			pw.print("is stored ");
			pw.print("<br>Your Order No is "+(orderId));
			if(pickup){
				pw.print("<br>The store location for pickup is "+storeId);
				pw.print("<br>The items will be available for pickup on "+orderDate.plusDays(14));
			}
			else{
				pw.print("<br>The items will be delivered to your address by "+(orderDate.plusDays(14)));
			}
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
