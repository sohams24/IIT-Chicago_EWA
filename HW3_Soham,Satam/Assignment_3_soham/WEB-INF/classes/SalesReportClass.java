import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SalesReportClass")

public class SalesReportClass extends HttpServlet{
	private String productId;
	private String productName;
	private double productPrice;
	private int itemsSold;
	private double totalSales;
	
	public SalesReportClass(String productId, String productName, double productPrice, int itemsSold, double totalSales){
		this.productId = productId;
		this.productName=productName;
		this.productPrice=productPrice;
		this.itemsSold = itemsSold;
		this.totalSales = totalSales;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public int getItemsSold() {
		return itemsSold;
	}

	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}


    public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

}
