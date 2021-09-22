import java.io.IOException;
import java.io.*;
import java.time.*;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class Transaction implements Serializable{

    private String loginId;
    private String customerName;
    private int customerAge;
    private String customerOcc;
    private String creditCardNo;
    private int orderId;
    private LocalDate orderDate;
    private LocalDate exptDelDate;
    private LocalDate actDelDate;
    private String productId;
    private String productName;
    private String category;
    private String manufacturer;
    private int reviewRating;
    private String delTrackId;
    private String delType;
    private String delZipCode;
    private boolean transactionStatus;
    private boolean orderReturned;
    private boolean orderDelOnTime;
	
	public Transaction(String custName, int custAge, String custOcc, String creditCardNo,
                        LocalDate orderDate, LocalDate exptDelDate,
						String delType,String delZipCode, boolean transactionStatus){

            // this.loginId = loginId;
            this.customerName = custName;
            this.customerAge = custAge;
            this.customerOcc = custOcc;
            this.creditCardNo = creditCardNo;
            // this.orderId = orderId;
            this.orderDate = orderDate;
            this.exptDelDate = exptDelDate;
            // this.actDelDate= actDelDate;
            // this.productId = productId;
            // this.productName = productName;
            // this.category = category;
            // this.manufacturer = manufacturer;
            // this.reviewRating = reviewRating;
            // this.delTrackId = delTrackId;
            this.delType = delType;
            this.delZipCode = delZipCode;
            this.transactionStatus = transactionStatus;
            // this.orderReturned = orderReturned;
            // this.orderDelOnTime = orderDelOnTime;
	}

    public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

    public String getCustName(){
		return customerName;
	}
	public void setCustName(String custName) {
		this.customerName = custName;
	}



    public int getCustAge(){
		return customerAge;
	}
	public void setCustAge(int custAge) {
		this.customerAge = custAge;
	}




    public String getCustOcc(){
		return customerOcc;
	}
	public void setCustOcc(String custOcc) {
		this.customerOcc = custOcc;
	}




    public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}



    public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}




    public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}



	public LocalDate getExptDelDate() {
		return exptDelDate;
	}
	public void setExptDelDate(LocalDate exptDelDate) {
		this.exptDelDate = exptDelDate;
	}



    public LocalDate getActDelDate() {
		return actDelDate;
	}
	public void setActDelDate(LocalDate actDelDate) {
		this.actDelDate = actDelDate;
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



    public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}



    public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}




    public String getDelTrackId() {
		return delTrackId;
	}
	public void setDelTrackId(String delTrackId) {
		this.delTrackId = delTrackId;
	}




	public int getReviewRating(){
		return reviewRating;
	}
	public void setReviewRating(int reviewRating){
		this.reviewRating = reviewRating;
	}



    public String getDelType() {
		return delType;
	}
	public void setDelType(String pickup) {
		this.delType = delType;
	}



    public String getDelZipCode() {
		return delZipCode;
	}
	public void setDelZip(String delZipCode) {
		this.delZipCode = delZipCode;
	}



    public boolean getTranStatus() {
		return transactionStatus;
	}
	public void setTranStatus(boolean transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


    public boolean getOrderReturned() {
		return orderReturned;
	}
	public void setOrderReturned(boolean orderReturned) {
		this.orderReturned = orderReturned;
	}



    public boolean getOrderDelOnTime() {
		return orderDelOnTime;
	}
	public void setOrderDelOnTime(boolean orderDelOnTime) {
		this.orderDelOnTime = orderDelOnTime;
	}
	
}
