import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/DeliveryOptions")

public class DeliveryOptions extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

        Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		System.out.print("name" + name + "type" + type + "maker" + maker + "accesee" + access);
        utility.printHtml("Header.html");
        
		utility.printHtml("LeftNavigationBar.html");
        // pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h2 class='title'>Please select delivery or store pickup:</h2>");
        pw.print("<form method='post' action='Cart'>" +
					"<input type='radio' id='del' name='delpick' value='Delivery'>"+
                    "<label for='del'>Delivery</label>"+
                    "<input type='radio' id='pick' name='delpick' value='Pickup'>"+
                    "<label for='pick'>Pickup</label><br>"+
                    "<input type='hidden' name='name' value='"+name+"'>"+
                    "<input type='hidden' name='type' value='"+type+"'>"+
                    "<input type='hidden' name='maker' value='"+maker+"'>"+
                    "<input type='hidden' name='access' value='"+access+"'>"+
                    "<input type='submit' value='Proceed'>"+
                    "</form>");
        pw.print("</div");
        pw.print("</div></br></br></br></br></br></br></br>");
       

        utility.printHtml("Footer.html");
	}
}