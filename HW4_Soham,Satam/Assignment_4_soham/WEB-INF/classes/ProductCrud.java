import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");

        String msg = "good";
        String producttype = "", productId = "", productName = "", productImage = "", productManufacturer = "", productCondition = "", prod = "";
        double productPrice = 0.0, productDiscount = 0.0, productRebate = 0.0;
        HashMap < String, Phone > allphones = new HashMap < String, Phone > ();
        HashMap < String, Telivision > alltvs = new HashMap < String, Telivision > ();
        HashMap < String, Laptop > alllaptops = new HashMap < String, Laptop > ();
        HashMap < String, SoundSystem > allsoundSystems = new HashMap < String, SoundSystem > ();
        HashMap < String, VoiceAssistant > allvoiceAssistants = new HashMap < String, VoiceAssistant > ();
        HashMap < String, WearableTech > allwearableTechs = new HashMap < String, WearableTech > ();
        HashMap < String, Accessory > allaccessories = new HashMap < String, Accessory > ();
        if (action.equals("add") || action.equals("update")) {
            producttype = request.getParameter("producttype");
            productId = request.getParameter("productId");
            productName = request.getParameter("productName");
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            productImage = request.getParameter("productImage");
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
            productRebate = Double.parseDouble(request.getParameter("productRebate"));

        } else {
            productId = request.getParameter("productId");
        }
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if (action.equals("add")) {

            if (producttype.equals("phones")) {
                allphones = MySqlDataStoreUtilities.getPhones();
                if (allphones.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("tvs")) {
                alltvs = MySqlDataStoreUtilities.getTvs();
                if (alltvs.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("laptops")) {
                alllaptops = MySqlDataStoreUtilities.getLaptops();
                if (alllaptops.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("soundSystems")) {
                allsoundSystems = MySqlDataStoreUtilities.getSoundSystems();
                if (allsoundSystems.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("voiceAssistants")) {
                allvoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if (allvoiceAssistants.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("wearableTechs")) {
                allwearableTechs = MySqlDataStoreUtilities.getWearableTechs();
                if (allwearableTechs.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("accessories")) {
                if (!request.getParameter("product").isEmpty()) {
                    prod = request.getParameter("product");
                    allphones = MySqlDataStoreUtilities.getPhones();
                    if (allphones.containsKey(prod)) {
                        allaccessories = MySqlDataStoreUtilities.getAccessories();
                        if (allaccessories.containsKey(productId)) {
                            msg = "Product already available";
                        }
                    } else {
                        msg = "The product related to accessories is not available";
                    }


                } else {
                    msg = "Please add the prodcut name";
                }

            }
            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.addproducts(producttype, productId, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productRebate, prod);
                } catch (Exception e) {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }
        } else if (action.equals("update")) {

            if (producttype.equals("phones")) {
                allphones = MySqlDataStoreUtilities.getPhones();
                if (!allphones.containsKey(productId)) {
                    msg = "Product not available";
                }

            } else if (producttype.equals("tvs")) {
                alltvs = MySqlDataStoreUtilities.getTvs();
                if (!alltvs.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("laptops")) {
                alllaptops = MySqlDataStoreUtilities.getLaptops();
                if (!alllaptops.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("soundSystems")) {
                allsoundSystems = MySqlDataStoreUtilities.getSoundSystems();
                if (!allsoundSystems.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("voiceAssistants")) {
                allvoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if (!allvoiceAssistants.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("wearableTechs")) {
                allwearableTechs = MySqlDataStoreUtilities.getWearableTechs();
                if (!allwearableTechs.containsKey(productId)) {
                    msg = "Product not available";
                }
            } else if (producttype.equals("accessories")) {
                allaccessories = MySqlDataStoreUtilities.getAccessories();
                if (!allaccessories.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.updateproducts(producttype, productId, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, productRebate);
                } catch (Exception e) {
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            }

        } else if (action.equals("delete")) {

            msg = "bad";

            allphones = MySqlDataStoreUtilities.getPhones();
            if (allphones.containsKey(productId)) {
                msg = "good";
            }

            alltvs = MySqlDataStoreUtilities.getTvs();
            if (alltvs.containsKey(productId)) {
                msg = "good";
            }

            alllaptops = MySqlDataStoreUtilities.getLaptops();
            if (alllaptops.containsKey(productId)) {
                msg = "good";
            }

            allsoundSystems = MySqlDataStoreUtilities.getSoundSystems();
            if (allsoundSystems.containsKey(productId)) {
                msg = "good";
            }

            allwearableTechs = MySqlDataStoreUtilities.getWearableTechs();
            if (allwearableTechs.containsKey(productId)) {
                msg = "good";
            }

            allvoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
            if (allvoiceAssistants.containsKey(productId)) {
                msg = "good";
            }

            allaccessories = MySqlDataStoreUtilities.getAccessories();
            if (allaccessories.containsKey(productId)) {
                msg = "good";
            }

            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.deleteproducts(productId);
                } catch (Exception e) {
                    msg = "Product cannot be deleted";
                }
                msg = "Product has been successfully deleted";
            } else {
                msg = "Product not available";
            }
        }
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h4 style='color:blue'>" + msg + "</h4>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }
}