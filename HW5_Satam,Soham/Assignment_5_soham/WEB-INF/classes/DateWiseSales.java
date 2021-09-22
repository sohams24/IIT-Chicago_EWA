import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@WebServlet("/DateWiseSales")

public class DateWiseSales extends HttpServlet{
	private LocalDate date;
	private double totalSales;
	
	public DateWiseSales(LocalDate date, double totalSales){
		this.date = date;
		this.totalSales = totalSales;
	}

    public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

    public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

}
