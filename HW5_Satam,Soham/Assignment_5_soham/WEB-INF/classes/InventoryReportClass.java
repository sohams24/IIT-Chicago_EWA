import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InventoryReportClass")

public class InventoryReportClass extends HttpServlet{
	private String productName;
	private double productPrice;
    private int itemsAvaialble;
    private double discountRebate;	
	
	public InventoryReportClass(String productName, double productPrice, int itemsAvaialble){
		this.productName=productName;
		this.productPrice=productPrice;
		this.itemsAvaialble = itemsAvaialble;
	}

    public InventoryReportClass(String productName, double productPrice, double productDiscount){
		this.productName=productName;
		this.productPrice=productPrice;
		this.discountRebate = productDiscount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

    public int getItemsAvailable() {
		return itemsAvaialble;
	}

	public void setItemsAvaialble(int itemsAvaialble) {
		this.itemsAvaialble = itemsAvaialble;
	}

    public double getDiscountRebate() {
		return discountRebate;
	}

	public void setDiscountRebate(double discountRebate) {
		this.discountRebate = discountRebate;
	}

}
