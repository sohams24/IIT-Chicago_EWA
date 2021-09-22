import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String usertype = session.getAttribute("usertype").toString();

				System.out.println("UserType is : "+usertype+"\n");
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);

				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello, "+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";

				if(usertype.equalsIgnoreCase("retailer")){
					result = result + "<li><a href='StoreManager'><span class='glyphicon'>Manager options</span></a></li>";
				}

				if(usertype.equalsIgnoreCase("manager")){
					result = result + "<li><a href='Salesman'><span class='glyphicon'>Salesman options</span></a></li>";
				}
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment_1_soham\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>(); 
		// Order order = new Order();
		if(OrdersHashMap.orders.containsKey(username()))
			orderItems= OrdersHashMap.orders.get(username());
		return orderItems;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment_1_soham\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			// int size=0;
			// for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
			// 		 size=size + 1;
					 
			// }
			int size=orderPayments.size();
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		// Order order = OrdersHashMap.orders.get(username());
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("tvs")){
			Telivision tv;
			tv = SaxParserDataStore.tvs.get(name);
			OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(), tv.getImage(), tv.getRetailer());
			// orderitem.setDelPick(delpick);
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}
		if(type.equals("soundSystems")){
			SoundSystem soundSystem;
			soundSystem = SaxParserDataStore.soundSystems.get(name);
			OrderItem orderitem = new OrderItem(soundSystem.getName(), soundSystem.getPrice(), soundSystem.getImage(), soundSystem.getRetailer());
			// orderitem.setDelPick(delpick);
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			Phone phone;
			phone = SaxParserDataStore.phones.get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer());
			// orderitem.setDelPick(delpick);
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptop laptop;
			laptop = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			// orderitem.setDelPick(delpick);
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}
		if(type.equals("voiceAssistants")){
			VoiceAssistant voiceAssistant;
			voiceAssistant = SaxParserDataStore.voiceAssistants.get(name);
			OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(), voiceAssistant.getImage(), voiceAssistant.getRetailer());
			// orderitem.setDelPick(delpick);
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}
		if(type.equals("wearableTechs")){
			WearableTech wearableTech;
			wearableTech = SaxParserDataStore.wearableTechs.get(name);
			OrderItem orderitem = new OrderItem(wearableTech.getName(), wearableTech.getPrice(), wearableTech.getImage(), wearableTech.getRetailer());
			// orderitem.setDelPick(delpick);
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}


		// if(type.equals("tablets")){
		// 	Tablet tablet = null;
		// 	tablet = SaxParserDataStore.tablets.get(name);
		// 	OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
		// 	orderItems.add(orderitem);
		// }
		if(type.equals("accessories")){	
			Accessory accessory = SaxParserDataStore.accessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
			// order.addOrderItem(orderitem);
			orderItems.add(orderitem);
		}
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo, boolean pickup, String storeLocation, LocalDate orderDate ){
		
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment_1_soham\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo, pickup, storeLocation, orderDate);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment_1_soham\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	
	/* getTelivisions Functions returns the Hashmap with all tvs in the store.*/

	public HashMap<String, Telivision> getTelivisions(){
			HashMap<String, Telivision> hm = new HashMap<String, Telivision>();
			hm.putAll(SaxParserDataStore.tvs);
			return hm;
	}
	
	/* getSoundSystems Functions returns the  Hashmap with all soundSystems in the store.*/

	public HashMap<String, SoundSystem> getSoundSystems(){
			HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
			hm.putAll(SaxParserDataStore.soundSystems);
			return hm;
	}
	
	/* getPhones Functions returns the Hashmap with all phones in the store.*/

	public HashMap<String, Phone> getPhones(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}

	public HashMap<String, Laptop> getLaptops(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}

	public HashMap<String, VoiceAssistant> getVoiceAssistants(){
			HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
			hm.putAll(SaxParserDataStore.voiceAssistants);
			return hm;
	}
	public HashMap<String, WearableTech> getWearableTechs(){
			HashMap<String, WearableTech> hm = new HashMap<String, WearableTech>();
			hm.putAll(SaxParserDataStore.wearableTechs);
			return hm;
	}
	
	/* getProductsTelivisions Functions returns the Arraylist of tvs in the store.*/

	public ArrayList<String> getProductsTelivisions(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Telivision> entry : getTelivisions().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductsSoundSystems(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SoundSystem> entry : getSoundSystems().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProductsPhones Functions returns the Arraylist of phones in the store.*/

	public ArrayList<String> getProductsPhones(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsLaptops(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsVoiceAsistants(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistants().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsWearableTechs(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, WearableTech> entry : getWearableTechs().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

}
