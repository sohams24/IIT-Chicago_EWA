import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/Order")


public class Order extends HttpServlet{
    public ArrayList<OrderItem> orderItems;
	private String name;
	private String street;
	private String city;
	private String state;
	private String zipcode;
    private String creditCard;
    private boolean pickup = false;
    private String storeLocation = null;

	
	public Order(){
		// this.orderItems=orderItems;
        // this.name=name;
        // this.street=street;
        // this.city=city;
        // this.state=state;
        // this.zipcode=zipcode;
        // this.creditcard=creditcard;
        // this.pickup=pickup;
        // this.storeLocation=storeLocation;
	}

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }

    public void delOrderItem(OrderItem orderItem){
        orderItems.remove(orderItem);
    }

    public ArrayList<OrderItem> getOrderItems(){
        return orderItems;
    }

    public String getName(){
        return name;
    }
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getZipcode(){
        return zipcode;
    }
    public String getCreditCard(){
        return creditCard;
    }
    public boolean getpickup(){
        return pickup;
    }
    public String getStoreLocation(){
        return storeLocation;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setZipCode(String zipcode){
        this.zipcode = zipcode;
    }
    public void setCreditCard(String creditCard){
        this.creditCard = creditCard;
    }
    public void setPickup(boolean pickup){
        this.pickup = pickup;
    }
    public void setStoreLocation(String storeLocation){
        this.storeLocation = storeLocation;
    }
    
}
