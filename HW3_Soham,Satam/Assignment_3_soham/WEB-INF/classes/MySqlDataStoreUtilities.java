import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.io.*;

public class MySqlDataStoreUtilities {
    static Connection conn = null;
    static String message;

    public static String getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsql", "root", "3306");
            System.out.println("Trying SQL connection.");
            message = "Successfull";
            return message;
        } catch (SQLException e) {
            System.out.println("SQL connection unsuccessful.");
            message = "unsuccessful";
            return message;

        } catch (Exception e) {
            message = e.getMessage();
            return message;
        }
    }


    public static void Insertproducts() {

        System.out.println("This method loads products from productsCatalog into productDetails table.");

        try {

            getConnection();

            String truncatetableacc = "delete from productaccessories;";
            PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
            pstt.executeUpdate();

            String truncatetableprod = "delete from  productdetails;";
            PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
            psttprod.executeUpdate();

            String insertProductQurey = "INSERT INTO  Productdetails(productId,productName,ProductType,productPrice,productImage,productManufacturer,productCondition,productDiscount,productRebate)" +
                "VALUES (?,?,?,?,?,?,?,?,?);";

            for (Map.Entry < String, Accessory > entry: SaxParserDataStore.accessories.entrySet()) {
                String type = "accessories";
                Accessory acc = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, acc.getId());
                pst.setString(2, acc.getName());
                pst.setString(3, type);
                pst.setDouble(4, acc.getPrice());
                pst.setString(5, acc.getImage());
                pst.setString(6, acc.getRetailer());
                pst.setString(7, acc.getCondition());
                pst.setDouble(8, acc.getDiscount());
                pst.setDouble(9, acc.getRebate());
                pst.executeUpdate();
            }


            for (Map.Entry < String, Phone > entry: SaxParserDataStore.phones.entrySet()) {
                Phone phone = entry.getValue();
                String type = "phones";

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, phone.getId());
                pst.setString(2, phone.getName());
                pst.setString(3, type);
                pst.setDouble(4, phone.getPrice());
                pst.setString(5, phone.getImage());
                pst.setString(6, phone.getRetailer());
                pst.setString(7, phone.getCondition());
                pst.setDouble(8, phone.getDiscount());
                pst.setDouble(9, phone.getRebate());
                pst.executeUpdate();
                try {
                    HashMap < String, String > acc = phone.getAccessories();
                    String insertAccessoryQurey = "INSERT INTO  productaccessories(productId,accessoryId)" +
                        "VALUES (?,?);";
                    for (Map.Entry < String, String > accentry: acc.entrySet()) {
                        PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
                        pstacc.setString(1, phone.getId());
                        pstacc.setString(2, accentry.getValue());
                        pstacc.executeUpdate();
                    }
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
            for (Map.Entry < String, Telivision > entry: SaxParserDataStore.tvs.entrySet()) {
                String type = "tvs";
                Telivision tv = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, tv.getId());
                pst.setString(2, tv.getName());
                pst.setString(3, type);
                pst.setDouble(4, tv.getPrice());
                pst.setString(5, tv.getImage());
                pst.setString(6, tv.getRetailer());
                pst.setString(7, tv.getCondition());
                pst.setDouble(8, tv.getDiscount());
                pst.setDouble(9, tv.getRebate());

                pst.executeUpdate();


            }

            for (Map.Entry < String, Laptop > entry: SaxParserDataStore.laptops.entrySet()) {
                String type = "laptops";
                Laptop laptop = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, laptop.getId());
                pst.setString(2, laptop.getName());
                pst.setString(3, type);
                pst.setDouble(4, laptop.getPrice());
                pst.setString(5, laptop.getImage());
                pst.setString(6, laptop.getRetailer());
                pst.setString(7, laptop.getCondition());
                pst.setDouble(8, laptop.getDiscount());
                pst.setDouble(9, laptop.getRebate());

                pst.executeUpdate();


            }

            for (Map.Entry < String, SoundSystem > entry: SaxParserDataStore.soundSystems.entrySet()) {
                String type = "soundSystems";
                SoundSystem soundSystem = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, soundSystem.getId());
                pst.setString(2, soundSystem.getName());
                pst.setString(3, type);
                pst.setDouble(4, soundSystem.getPrice());
                pst.setString(5, soundSystem.getImage());
                pst.setString(6, soundSystem.getRetailer());
                pst.setString(7, soundSystem.getCondition());
                pst.setDouble(8, soundSystem.getDiscount());
                pst.setDouble(9, soundSystem.getRebate());

                pst.executeUpdate();


            }

            for (Map.Entry < String, VoiceAssistant > entry: SaxParserDataStore.voiceAssistants.entrySet()) {
                String type = "voiceAssistants";
                VoiceAssistant voiceAssistant = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, voiceAssistant.getId());
                pst.setString(2, voiceAssistant.getName());
                pst.setString(3, type);
                pst.setDouble(4, voiceAssistant.getPrice());
                pst.setString(5, voiceAssistant.getImage());
                pst.setString(6, voiceAssistant.getRetailer());
                pst.setString(7, voiceAssistant.getCondition());
                pst.setDouble(8, voiceAssistant.getDiscount());
                pst.setDouble(9, voiceAssistant.getRebate());

                pst.executeUpdate();


            }

            for (Map.Entry < String, WearableTech > entry: SaxParserDataStore.wearableTechs.entrySet()) {
                String type = "wearableTechs";
                WearableTech wearableTech = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, wearableTech.getId());
                pst.setString(2, wearableTech.getName());
                pst.setString(3, type);
                pst.setDouble(4, wearableTech.getPrice());
                pst.setString(5, wearableTech.getImage());
                pst.setString(6, wearableTech.getRetailer());
                pst.setString(7, wearableTech.getCondition());
                pst.setDouble(8, wearableTech.getDiscount());
                pst.setDouble(9, wearableTech.getRebate());

                pst.executeUpdate();


            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }


    public static void deleteOrder(int orderId, String userName, String orderName) {
        try {
            getConnection();
            String deleteOrderQuery = "Delete from customerorders where OrderId=? and userName=? and productId=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap < String, Phone > getPhones() {
        HashMap < String, Phone > hm = new HashMap < String, Phone > ();
        try {
            getConnection();

            String selectPhone = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectPhone);
            pst.setString(1, "phones");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Phone phone = new Phone(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"),
                    rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                phone.setId(rs.getString("productId"));
                double rebate = Double.parseDouble(rs.getString("productRebate"));
                phone.setRebate(rebate);
                hm.put(rs.getString("productId"), phone);
                try {
                    String selectaccessory = "Select * from productAccessories where productId=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1, rs.getString("productId"));
                    ResultSet rsacc = pstacc.executeQuery();

                    HashMap < String, String > acchashmap = new HashMap < String, String > ();
                    while (rsacc.next()) {
                        if (rsacc.getString("accessoryId") != null) {
                            acchashmap.put(rsacc.getString("accessoryId"), rsacc.getString("accessoryId"));
                            phone.setAccessories(acchashmap);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {}
        return hm;
    }


    public static HashMap < String, Telivision > getTvs() {
        HashMap < String, Telivision > hm = new HashMap < String, Telivision > ();
        try {
            getConnection();

            String selectTv = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTv);
            pst.setString(1, "tvs");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Telivision tv = new Telivision(rs.getString("productName"), rs.getDouble("productPrice"),
                    rs.getString("productImage"), rs.getString("productManufacturer"),
                    rs.getString("productCondition"), rs.getDouble("productDiscount"));
                tv.setId(rs.getString("productId"));
                double rebate = Double.parseDouble(rs.getString("productrebate"));
                tv.setRebate(rebate);
                hm.put(rs.getString("productId"), tv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }


    public static HashMap < String, Laptop > getLaptops() {
        HashMap < String, Laptop > hm = new HashMap < String, Laptop > ();
        try {
            getConnection();

            String selectLaptop = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectLaptop);
            pst.setString(1, "laptops");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Laptop laptop = new Laptop(rs.getString("productName"), rs.getDouble("productPrice"),
                    rs.getString("productImage"), rs.getString("productManufacturer"),
                    rs.getString("productCondition"), rs.getDouble("productDiscount"));
                laptop.setId(rs.getString("productId"));
                double rebate = Double.parseDouble(rs.getString("productrebate"));
                laptop.setRebate(rebate);
                hm.put(rs.getString("productId"), laptop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }


    public static HashMap < String, SoundSystem > getSoundSystems() {
        HashMap < String, SoundSystem > hm = new HashMap < String, SoundSystem > ();
        try {
            getConnection();

            String selectss = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectss);
            pst.setString(1, "soundSystems");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SoundSystem soundSystem = new SoundSystem(rs.getString("productName"), rs.getDouble("productPrice"),
                    rs.getString("productImage"), rs.getString("productManufacturer"),
                    rs.getString("productCondition"), rs.getDouble("productDiscount"));
                soundSystem.setId(rs.getString("productId"));
                double rebate = Double.parseDouble(rs.getString("productrebate"));
                soundSystem.setRebate(rebate);
                hm.put(rs.getString("productId"), soundSystem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static HashMap < String, VoiceAssistant > getVoiceAssistants() {
        HashMap < String, VoiceAssistant > hm = new HashMap < String, VoiceAssistant > ();
        try {
            getConnection();

            String selectva = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectva);
            pst.setString(1, "voiceAssistants");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                VoiceAssistant va = new VoiceAssistant(rs.getString("productName"), rs.getDouble("productPrice"),
                    rs.getString("productImage"), rs.getString("productManufacturer"),
                    rs.getString("productCondition"), rs.getDouble("productDiscount"));
                va.setId(rs.getString("productId"));
                double rebate = Double.parseDouble(rs.getString("productRebate"));
                va.setRebate(rebate);
                hm.put(rs.getString("productId"), va);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static HashMap < String, WearableTech > getWearableTechs() {
        HashMap < String, WearableTech > hm = new HashMap < String, WearableTech > ();
        try {
            getConnection();

            String selectwt = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectwt);
            pst.setString(1, "wearableTechs");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                WearableTech wt = new WearableTech(rs.getString("productName"), rs.getDouble("productPrice"),
                    rs.getString("productImage"), rs.getString("productManufacturer"),
                    rs.getString("productCondition"), rs.getDouble("productDiscount"));
                wt.setId(rs.getString("productId"));
                double rebate = Double.parseDouble(rs.getString("productRebate"));
                wt.setRebate(rebate);
                hm.put(rs.getString("productId"), wt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }


	public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		getConnection();
		
		String selectAcc="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				acc.setId(rs.getString("productId"));
				double rebate = Double.parseDouble(rs.getString("productRebate"));
                acc.setRebate(rebate);
				hm.put(rs.getString("productId"), acc);
				

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,double productRebate,String prod)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Productdetails(ProductType,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			pst.setDouble(9,productRebate);
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}

public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount, double productRebate)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=?,productRebate=? where productId =?;" ;
			        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setDouble(7,productRebate);
			pst.setString(8,productId);
			pst.executeUpdate();
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}

public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where productId=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}

    // public static void insertOrder(int orderId,String userName,String orderName,
    // double orderPrice, double discount, double finalPrice, int quantity, double rebate, double totalCost, String userAddress,String creditCardNo, 
    // boolean pickup, String storeLocation, LocalDate orderDate)



    public static void insertOrder(OrderPayment orderpayment) {
        try {
            System.out.println("Trying to insert order into customerorders table.");
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,productId,orderPrice,discount,finalPrice," +
                "quantity,totalCost,rebate,finalCost,creditCardNo,pickup,shippingCost,orderDate,receiveDate,street,city,state,zip,order_storeId)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderpayment.getOrderId());
            System.out.println("Order id is: " + orderpayment.getOrderId());
            pst.setString(2, orderpayment.getUserName());
            System.out.println("username is: " + orderpayment.getUserName());
            pst.setString(3, orderpayment.getOrderName());
            System.out.println("ordername is: " + orderpayment.getOrderName());
            pst.setDouble(4, orderpayment.getOrderPrice());
            System.out.println("orderPrice is: " + orderpayment.getOrderPrice());
            pst.setDouble(5, orderpayment.getDiscount());
            System.out.println("discount is: " + orderpayment.getDiscount());
            pst.setDouble(6, orderpayment.getFinalPrice());
            System.out.println("Final price after discount: " + orderpayment.getFinalPrice());
            pst.setInt(7, orderpayment.getQuantity());
            System.out.println("Quantity no is: " + orderpayment.getQuantity());
            pst.setDouble(8, orderpayment.getTotalCost());
            System.out.println("Total cost: " + orderpayment.getTotalCost());
            pst.setDouble(9, orderpayment.getRebate());
            System.out.println("rebate is: " + orderpayment.getRebate());
            pst.setDouble(10, orderpayment.getFinalCost());
            System.out.println("Final cost after rebate: " + orderpayment.getFinalCost());
            pst.setString(11, orderpayment.getCreditCardNo());
            System.out.println("creditcard no is: " + orderpayment.getCreditCardNo());
            pst.setBoolean(12, orderpayment.getPickup());
            System.out.println("Pickup is: " + orderpayment.getPickup());
            pst.setDouble(13, orderpayment.getShippingCost());
            System.out.println("Shipping cost is: " + orderpayment.getShippingCost());
            Date orderDate = Date.valueOf(orderpayment.getOrderDate());
            pst.setDate(14, orderDate);
            System.out.println("OrderDate is: " + orderDate);
            Date receiveDate = Date.valueOf(orderpayment.getReceiveDate());
            pst.setDate(15, receiveDate);
            System.out.println("ReceiveDate no is: " + receiveDate);
            pst.setString(16, orderpayment.getStreet());
            System.out.println("Street: " + orderpayment.getStreet());
            pst.setString(17, orderpayment.getCity());
            System.out.println("City: " + orderpayment.getCity());
            pst.setString(18, orderpayment.getState());
            System.out.println("State: " + orderpayment.getState());
            pst.setString(19, orderpayment.getZip());
            System.out.println("Zip: " + orderpayment.getZip());
            pst.setString(20, orderpayment.getStoreId());
            System.out.println("Store for pickup is: " + orderpayment.getStoreId());
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Order insert unsuccessful");
        }
    }

    public static HashMap < Integer, ArrayList < OrderPayment >> selectOrder() {

        HashMap < Integer, ArrayList < OrderPayment >> orderPayments = new HashMap < Integer, ArrayList < OrderPayment >> ();

        try {
            getConnection();
            //select the table 
            String selectOrderQuery = "SELECT * FROM customerorders NATURAL JOIN productdetails LEFT OUTER JOIN stores ON order_storeId = storeId;";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList < OrderPayment > orderList = new ArrayList < OrderPayment > ();
            while (rs.next()) {
                System.out.println("Inside rs!");
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList < OrderPayment > arr = new ArrayList < OrderPayment > ();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList < OrderPayment > listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
                System.out.println("data is " + rs.getInt("OrderId") + orderPayments.get(rs.getInt("OrderId")));

                //add to orderpayment hashmap

                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                LocalDate receiveDate = rs.getDate("receiveDate").toLocalDate();

                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"), rs.getString("productId"), rs.getDouble("orderPrice"), rs.getDouble("discount"), rs.getInt("Quantity"), rs.getDouble("rebate"), rs.getString("creditCardNo"), rs.getBoolean("pickup"), orderDate, receiveDate);
                order.setProductName(rs.getString("productName"));
                System.out.println(rs.getString("storeStreet"));
                System.out.println(rs.getString("storeCity"));
                System.out.println(rs.getString("storeState"));
                System.out.println(rs.getString("storeZip"));
                if (order.getPickup()) {
                    order.setStoreId(rs.getString("storeId"));
                    order.setStoreStreet(rs.getString("storeStreet"));
                    order.setStoreCity(rs.getString("storeCity"));
                    order.setStoreState(rs.getString("storeState"));
                    order.setStoreZip(rs.getString("storeZip"));
                } else {
                    order.setShippingCost(10);
                    order.setStreet(rs.getString("street"));
                    order.setCity(rs.getString("city"));
                    order.setState(rs.getString("state"));
                    order.setZip(rs.getString("zip"));
                }

                listOrderPayment.add(order);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return orderPayments;
    }


    public static void insertUser(String username, String password, String usertype, String street,String city,String state,String zip) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,password,usertype,street,city,state,zip)" +
                "VALUES (?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, usertype);
            pst.setString(4, street);
            pst.setString(5, city);
            pst.setString(6, state);
            pst.setString(7, zip);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap < String, User > selectUser() {
        HashMap < String, User > hm = new HashMap < String, User > ();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from  Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"),rs.getString("street"),rs.getString("city"),rs.getString("state"),rs.getString("zip"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {}
        return hm;
    }



    public static ArrayList<SalesReportClass> getSalesReport(){

        ArrayList<SalesReportClass> salesReportList = new ArrayList<SalesReportClass>();

        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String salesReportquery = "SELECT productId, productName, productPrice, sum(quantity) AS itemsSold, sum(finalCost) AS totalSales FROM customerorders NATURAL JOIN productdetails GROUP BY productId;";
            ResultSet rs = stmt.executeQuery(salesReportquery);
            while (rs.next()) {
                SalesReportClass sr = new SalesReportClass(rs.getString("productId"), rs.getString("productName"), rs.getDouble("productPrice"),rs.getInt("itemsSold"),rs.getDouble("totalSales"));
                salesReportList.add(sr);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return salesReportList;

    }


    public static ArrayList<DateWiseSales> getDateWiseSales(){

        ArrayList<DateWiseSales> dateWiseSalesList = new ArrayList<DateWiseSales>();

        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String dateWiseSalesquery = "SELECT orderDate, sum(finalCost) AS totalSales FROM customerorders GROUP BY orderDate;";
            ResultSet rs = stmt.executeQuery(dateWiseSalesquery);
            while (rs.next()) {
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                DateWiseSales ds = new DateWiseSales(orderDate,rs.getDouble("totalSales"));
                dateWiseSalesList.add(ds);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dateWiseSalesList;
    }

    public static ArrayList<InventoryReportClass> getInventoryReport(){

        ArrayList<InventoryReportClass> inventoryReportList = new ArrayList<InventoryReportClass>();

        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String inventoryReportquery = "SELECT productName, productPrice, itemsAvailable FROM productDetails;";
            ResultSet rs = stmt.executeQuery(inventoryReportquery);
            while (rs.next()) {
                InventoryReportClass ir = new InventoryReportClass(rs.getString("productName"),rs.getDouble("productPrice"),rs.getInt("itemsAvailable"));
                inventoryReportList.add(ir);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return inventoryReportList;
    }


    public static ArrayList<InventoryReportClass> getDiscountProducts(){

        ArrayList<InventoryReportClass> inventoryReportList = new ArrayList<InventoryReportClass>();

        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String inventoryReportquery = "SELECT productName, productPrice, productDiscount FROM productDetails WHERE productDiscount>0;";
            ResultSet rs = stmt.executeQuery(inventoryReportquery);
            while (rs.next()) {
                InventoryReportClass ir = new InventoryReportClass(rs.getString("productName"),rs.getDouble("productPrice"),rs.getDouble("productDiscount"));
                inventoryReportList.add(ir);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return inventoryReportList;
    }

    public static ArrayList<InventoryReportClass> getRebateProducts(){

        ArrayList<InventoryReportClass> inventoryReportList = new ArrayList<InventoryReportClass>();

        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String inventoryReportquery = "SELECT productName, productPrice, productRebate FROM productDetails WHERE productRebate>0;";
            ResultSet rs = stmt.executeQuery(inventoryReportquery);
            while (rs.next()) {
                InventoryReportClass ir = new InventoryReportClass(rs.getString("productName"),rs.getDouble("productPrice"),rs.getDouble("productRebate"));
                inventoryReportList.add(ir);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return inventoryReportList;
    }
}