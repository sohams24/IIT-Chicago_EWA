import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/ViewOrdersSalesman")

public class ViewOrdersSalesman extends HttpServlet
{
    HttpSession session;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
        pw.print("<form name ='ViewOrder' action='ViewOrdersSalesman' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");

        //hashmap gets all the order details from file 
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		int size;

        // try
		// {
		// 	FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment_5_soham\\PaymentDetails.txt"));
		// 	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		// 	orderPayments = (HashMap)objectInputStream.readObject();
		// }
		// catch(Exception e)
		// {
		// }

        /*check if the order button is clicked 
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order 
		if order button is clicked user will be directed to this same servlet and user has given order number 
		then this page shows all the order details*/
	
		if(request.getParameter("Order")==null)
        {
            pw.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
            pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");

        }

        /*if order button is clicked that is user provided a order number to view order 
		order details will be fetched and displayed in  a table 
		Also user will get an button to cancel the order */


        if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
            if (request.getParameter("orderId") != null && request.getParameter("orderId") != "" )
			{
                int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file

                try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}

                size=0;
				/*get the order size and check if there exist an order with given order number 
				if there is no order present give a message no order stored with this id */

				// display the orders if there exist order with order id
				if(orderPayments.get(orderId)!=null)
				{	
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<th>OrderId:</th>");
					pw.print("<th>UserName:</th>");
					pw.print("<th>Order Name:</th>");
					pw.print("<th>Price</th>");
					pw.print("<th>Discount</th>");
					pw.print("<th>Final Price</th>");
					pw.print("<th>Quantity</th>");
					pw.print("<th>Total Cost</th>");
					pw.print("<th>Rebate</th>");
					pw.print("<th>Final Cost</th>");
					pw.print("<th>Creditcard</th>");
					pw.print("<th>Order Date</th>");
					pw.print("<th>Delivery/Pickup Date</th>");
					pw.print("<th>Delivery/Pickup</th>");
					pw.print("<th>Shipping Cost</th>");
					pw.print("<th>Shipping Address</th>");
					pw.print("<th>Store Address</th></tr>");
					
					for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						pw.print("<tr>");			
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
						pw.print("<td>"+oi.getOrderId()+".</td>"+
						"<td>"+oi.getUserName()+"</td>"+
                        "<td>"+oi.getProductName()+"</td>"+
						"<td>"+oi.getOrderPrice()+"</td>"+
						"<td>"+oi.getDiscount()+"</td>"+
						"<td>"+oi.getFinalPrice()+"</td>"+
						"<td>"+oi.getQuantity()+"</td>"+
						"<td>"+oi.getTotalCost()+"</td>"+
						"<td>"+oi.getRebate()+"</td>"+
						"<td>"+oi.getFinalCost()+"</td>"+
						"<td>"+oi.getCreditCardNo()+"</td>"+
						"<td>"+oi.getOrderDate()+"</td>"+
						"<td>"+oi.getReceiveDate()+"</td>");
						if(oi.getPickup()){
							pw.print("<td>Pickup</td>"+
									"<td>-</td>"+
									"<td>-</td>"+
									"<td>"+oi.getStoreId()+"\n"+oi.getStoreStreet()+"\n"+oi.getStoreCity()+"\n"+oi.getStoreState()+"\n"+oi.getStoreZip()+"</td>");
						}
						else{
							pw.print("<td>Delivery</td>"+
									"<td>"+oi.getShippingCost()+"</td>"+
									"<td>"+oi.getStreet()+"\n"+oi.getCity()+"\n"+oi.getState()+"\n"+oi.getZip()+"</td>"+
									"<td>-</td>");
						}
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");
					}
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>There are no orders with this id.</h4>");
				}


            }

            else
            {
                pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");
            }
        }

        //if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			if(request.getParameter("orderName") != null)
			{
				String orderName=request.getParameter("orderName");
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
                
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
				try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						if(oi.getOrderName().equals(orderName))
						{
                            String userName = oi.getUserName();
							MySqlDataStoreUtilities.deleteOrder(orderId,userName,orderName);
							ListOrderPayment.add(oi);
							pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");	
						}
					}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				if(orderPayments.get(orderId).size()==0)
					{
						orderPayments.remove(orderId);
					}
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");

    }
}