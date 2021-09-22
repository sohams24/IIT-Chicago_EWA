import java.io.IOException;
import java.io.*;
import java.time.*;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private String productName;
	private double orderPrice;
	private double discount;
	private double finalPrice;
	private int quantity;
	private double totalCost;
	private double rebate;
	private double finalCost;
	private String creditCardNo;
	private boolean pickup;
	private double shippingCost=0;
	private LocalDate orderDate;
	private LocalDate receiveDate; 
	private String street=null;
	private String city=null;
	private String state=null;
	private String zip=null;
	private String storeId=null;
	private String storeStreet=null;
	private String storeCity=null;
	private String storeState=null;
	private String storeZip=null;
	
	public OrderPayment(int orderId,String userName,String orderName,
			double orderPrice,double discount,int quantity,double rebate,
			String creditCardNo,boolean pickup,LocalDate orderDate,LocalDate receiveDate){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.discount=discount;
		this.quantity=quantity;
		this.rebate=rebate;
		this.creditCardNo=creditCardNo;
		this.pickup=pickup;
		this.orderDate=orderDate;
		this.receiveDate=receiveDate;
		updateFinalPrice();
		updateTotalCost();
		updateFinalCost();
		// this.shippingCost=shippingCost;
		// this.street=street;
		// this.city=city;
		// this.state=state;
		// this.zip=zip;
		// this.storeId=storeId;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}



	public double getDiscount(){
		return discount;
	}
	public void setDiscount(){
		this.discount=discount;
		updateFinalPrice();
	}


	public double getFinalPrice(){
		return finalPrice;
	}
	private void updateFinalPrice(){
		finalPrice = orderPrice - discount;
	}
	


	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
		updateTotalCost();
	}

	public double getTotalCost(){
		return totalCost;
	}
	private void updateTotalCost(){
		totalCost = finalPrice*quantity - rebate;
	}


	public double getRebate(){
		return rebate;
	}
	public void setRebate(){
		this.rebate=rebate;
		updateTotalCost();
	}


	public double getFinalCost(){
		return finalCost;
	}
	private void updateFinalCost(){
		finalCost = totalCost - rebate;
	}

	

	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}



	public boolean getPickup() {
		return pickup;
	}
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}



	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost*quantity;
	}

	

	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}



	public LocalDate getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}



	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}



	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	public String getStoreStreet() {
		return storeStreet;
	}
	public void setStoreStreet(String storeStreet) {
		this.storeStreet = storeStreet;
	}



	public String getStoreCity() {
		return storeCity;
	}
	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}


	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}


	public String getStoreZip() {
		return storeZip;
	}
	public void setStoreZip(String storeZip) {
		this.storeZip = storeZip;
	}
}
