import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Salesman")

public class Salesman extends HttpServlet {

	/* Salesman Page Displays all the Salesman options */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		
		/* Header, Left Navigation Bar are Printed.

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");

        pw.print("<a href='Registration'><h2>Create a new Customer</h2></a>");
		pw.print("<a href='ViewOrdersSalesman'><h2>Delete a customer order</h2></a>");
		
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
