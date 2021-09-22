import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String userName;
	private String productType;
	private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String price;
	private String storeId;
	private String storeZip;
	private String storeCity;
	private String storeState;
	private String productOnSale;
	private String rebate;
	private String userAge;
	private String userGender;
	private String userOccupation;
	
	public Review (String productName,String userName,String productType,
			String productMaker,String reviewRating,String reviewDate,
			String reviewText,String price,
			String storeId,String storeZip,String storeCity,
			String storeState,String productOnSale,String rebate,
			String userAge,String userGender,String userOccupation){
		this.productName=productName;
		this.productType=productType;
		this.productMaker=productMaker;
		this.price= price;

	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;

		this.storeId = storeId;
		this.storeZip = storeZip;
		this.storeCity = storeCity;
		this.storeState = storeState;

		this.productOnSale = productOnSale;
		this.rebate = rebate;
		this.userName=userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userOccupation = userOccupation;
	}

	public Review(String productName, String storeZip, String reviewRating, String reviewText) {
       this.productName = productName;
       this.storeZip = storeZip;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }

	public String getProductName() {
		return productName;
	}
	public String getUserName() {
		return userName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
    

	// public String getRetailerPin() {
	// 	return retailerpin;
	// }
	// public void setRetailerPin(String retailerpin) {
	// 	this.retailerpin = retailerpin;
	// }


	// public String getRetailerCity() {
	// 	return retailercity;
	// }
	// public void setRetailerCity(String retailercity) {
	// 	this.retailercity = retailercity;
	// }



	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}



	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreZip() {
		return storeZip;
	}
	public void setStoreZip(String storeZip) {
		this.storeZip = storeZip;
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

	public String getProductOnSale() {
		return productOnSale;
	}
	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}

	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserOccupation() {
		return userOccupation;
	}
	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}



}
