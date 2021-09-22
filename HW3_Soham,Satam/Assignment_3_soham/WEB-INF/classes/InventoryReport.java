import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.mongodb.AggregationOutput;


@WebServlet("/InventoryReport")
public class InventoryReport extends HttpServlet {

    static DBCollection myReviews;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        ArrayList<InventoryReportClass> inventoryReportList = new ArrayList<InventoryReportClass>();

        try
        {
            inventoryReportList=MySqlDataStoreUtilities.getInventoryReport();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        ArrayList<InventoryReportClass> discountProductsList = new ArrayList<InventoryReportClass>();

        try
        {
            discountProductsList=MySqlDataStoreUtilities.getDiscountProducts();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        ArrayList<InventoryReportClass> rebateProductsList = new ArrayList<InventoryReportClass>();

        try
        {
            rebateProductsList=MySqlDataStoreUtilities.getRebateProducts();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }




        //here write the code to display the sales table
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Inventory Report</a></h2>"
                + "<div class='entry'>");

        pw.print("<h3><button id='btnGetChartData'>View Inventory Chart</h3>");
        pw.println("<div id='chart_div'></div>");

        pw.print("<table  class='gridtable'>");
        pw.print("<th>Product Name</th>");
        pw.print("<th>Price</th>");
        pw.print("<th>Available</th>");

        for (InventoryReportClass ir : inventoryReportList)
        {
            pw.print("<tr>");						
            pw.print("<td>"+ir.getProductName()+"</td>"+
            "<td>"+ir.getProductPrice()+"</td>"+
            "<td>"+ir.getItemsAvailable()+"</td>");
            pw.print("</tr>");
            // System.out.println(ir.getProductName()+" "+ir.getProductPrice()+" "+ir.getItemsAvailable());
            // System.out.println("\n");
        }
        pw.print("</table>");
        pw.println("</div>");  
        

        pw.print("<br><br>");

        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products on Sale</a></h2>"
                + "<div class='entry'>");
        pw.print("<table  class='gridtable'>");
        pw.print("<th>Product Name</th>");
        pw.print("<th>Price</th>");
        pw.print("<th>Discount</th>");

        for (InventoryReportClass ir : discountProductsList)
        {
            pw.print("<tr>");						
            pw.print("<td>"+ir.getProductName()+"</td>"+
            "<td>"+ir.getProductPrice()+"</td>"+
            "<td>"+ir.getDiscountRebate()+"</td>");
            pw.print("</tr>");
            // System.out.println(ir.getProductName()+" "+ir.getProductPrice()+" "+ir.getDiscountRebate());
            // System.out.println("\n");
        }
        pw.print("</table>");
        pw.println("</div>"); 

        pw.print("<br><br>");

        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products with Rebate</a></h2>"
                + "<div class='entry'>");
        pw.print("<table  class='gridtable'>");
        pw.print("<th>Product Name</th>");
        pw.print("<th>Price</th>");
        pw.print("<th>Rebate</th>");

        for (InventoryReportClass ir : rebateProductsList)
        {
            pw.print("<tr>");						
            pw.print("<td>"+ir.getProductName()+"</td>"+
            "<td>"+ir.getProductPrice()+"</td>"+
            "<td>"+ir.getDiscountRebate()+"</td>");
            pw.print("</tr>");
            // System.out.println(ir.getProductName()+" "+ir.getProductPrice()+" "+ir.getDiscountRebate());
            // System.out.println("\n");
        }
        pw.print("</table>");
        pw.println("</div>");
        
        pw.println("</div></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='InventoryReport.js'></script>");
        
        
        utility.printHtml("Footer.html");

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ArrayList<InventoryReportClass> inventoryReportList = MySqlDataStoreUtilities.getInventoryReport();
            
            String inventoryReportJson = new Gson().toJson(inventoryReportList);
            System.out.println(inventoryReportJson);


            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(inventoryReportJson);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
