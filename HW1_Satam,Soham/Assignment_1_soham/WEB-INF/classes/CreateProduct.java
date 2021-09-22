import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateProduct")

public class CreateProduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
	    Utilities utility = new Utilities(request, pw);

	    /* StoreManager Page Displays all the StoreManager options */
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'></h2>");

        pw.print("<form method='post' action='CreateProduct'>"+
                "<label for='fname'>Product name:</label><br>"+
                "<input type='text' id='fname' name='name' value=''><br>"+
                "<label for='fname'>Product id:</label><br>"+
                "<input type='text' id='fname' name='id' value=''><br>"+
                "<label for='fname'>Price:</label><br>"+
                "<input type='text' id='fname' name='manufacturer' value=''><br>"+
                "<label for='fname'>Manufacturer:</label><br>"+
                "<input type='text' id='fname' name='Discount' value=''><br>"+
                "<label for='lname'>Discount:</label><br>"+
                "<input type='text' id='lname' name='lname' value=''><br><br>"+
                "<input type='submit' value='Create Product'>"+
                "</form> ");
        pw.print("</div></div> ");

        utility.printHtml("Footer.html");

	}

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
	    Utilities utility = new Utilities(request, pw);

	    /* StoreManager Page Displays all the StoreManager options */
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'></h2>");

        String name = request.getParameter("name");
        String id = request.getParameter("id");
        pw.print("<p>New product "+name+" created with id = "+id+"</p>");
        pw.print("</div></div> ");

        utility.printHtml("Footer.html");

	}
}
