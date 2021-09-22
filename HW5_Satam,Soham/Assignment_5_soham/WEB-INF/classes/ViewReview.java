import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        review(request, response);
    }

    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to view Review");
                response.sendRedirect("Login");
                return;
            }
            
            HashMap < String, ArrayList < Review >> hm = MongoDBDataStoreUtilities.selectReview();
            String productName = request.getParameter("name");
            String userName = ""; 
            String productType = request.getParameter("type");;
            String productMaker = request.getParameter("maker");;
            String reviewRating = "";
            String reviewDate = "";
            String reviewText = "";
            String price = "";
            String storeId = "";
            String storeZip = "";
            String storeCity = "";
            String storeState = "";
            String productOnSale = "";
            String rebate = "";
            String userAge = "";
            String userGender = "";
            String userOccupation = "";


            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Review</a>");
            pw.print("</h2><div class='entry'>");

            //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if (hm == null) {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            } else {
                if (!hm.containsKey(productName)) {
                    pw.println("<h2>There are no reviews for this product.</h2>");
                } else {
                    pw.print("<table class='gridtable'>");
                    pw.print("<tr>");
                    pw.print("<th> Product Name: </th>");
                    pw.print("<td>" + productName + "</td>");
                    pw.print("</tr>");

                    pw.print("<tr>");
                    pw.print("<th> Type: </th>");
                    pw.print("<td>" + productType + "</td>");
                    pw.print("</tr>");

                    pw.print("<tr>");
                    pw.print("<th> Manufacturer: </th>");
                    pw.print("<td>" + productMaker + "</td>");
                    pw.print("</tr>");
                    pw.println("</table>");

                    pw.print("<br><br>");

                    for (Review r: hm.get(productName)) {
                        pw.print("<table class='gridtable'>");
                        
                        pw.print("<tr>");
                        pw.println("<th> Username: </th>");
                        userName = r.getUserName();
                        pw.print("<td>" + userName + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.println("<th> Review Rating: </th>");
                        reviewRating = r.getReviewRating().toString();
                        pw.print("<td>" + reviewRating + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Review Date: </th>");
                        reviewDate = r.getReviewDate().toString();
                        pw.print("<td>" + reviewDate + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Review Text: </th>");
                        reviewText = r.getReviewText();
                        pw.print("<td>" + reviewText + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Store Id: </th>");
						storeId = r.getStoreId();
                        pw.print("<td>" + storeId + "</td>");
                        pw.print("</tr>");
                        
                        pw.print("<tr>");
                        pw.print("<th> Store Zip: </th>");
						storeZip = r.getStoreZip();
                        pw.print("<td>" + storeZip + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Store City: </th>");
						storeCity = r.getStoreCity();
                        pw.print("<td>" + storeCity + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Store State: </th>");
						storeState = r.getStoreState();
                        pw.print("<td>" + storeState + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> price: </th>");
                        price = r.getPrice();
                        pw.print("<td>" + price + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Product on sale? </th>");
						productOnSale = r.getProductOnSale();
                        pw.print("<td>" + productOnSale + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> Manufacturer Rebate? </th>");
						rebate = r.getRebate();
                        pw.print("<td>" + rebate + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> User Age: </th>");
						userAge = r.getUserAge();
                        pw.print("<td>" + userAge + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> User Gender: </th>");
						userGender = r.getUserGender();
                        pw.print("<td>" + userGender + "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");
                        pw.print("<th> User Occupation: </th>");
						userOccupation = r.getUserOccupation();
                        pw.print("<td>" + userOccupation + "</td>");
                        pw.print("</tr>");
                        pw.println("</table>");

                        pw.print("<br><br>");
                    }
                }
            }
            pw.print("</div></div></div>");
            utility.printHtml("Footer.html");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

    }
}