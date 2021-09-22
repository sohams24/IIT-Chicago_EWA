import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderItem")

/* 
	OrderItem class contains class variables name,price,image,retailer.

	OrderItem  class has a constructor with Arguments name,price,image,retailer.
	  
	OrderItem  class contains getters and setters for name,price,image,retailer.
*/

public class OrderItem extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private double discount;
	private double rebate;
	private int quantity;
	private String image;
	private String retailer;
	private String delPick;
	private String type;
	
	public OrderItem(String id, String name, double price, double discount, double rebate, int quantity, String image, String retailer,String type){
		this.id = id;
		this.name=name;
		this.price=price;
		this.discount = discount;
		this.rebate = rebate;
		this.quantity = quantity;
		this.image=image;
		this.retailer = retailer;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getRebate() {
		return rebate;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void updateQuantity(int quantity){
		this.quantity += quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setDelPick(String delPick){
		this.delPick = delPick;
	}

	public String getDelPick(){
		return delPick;
	}
}
