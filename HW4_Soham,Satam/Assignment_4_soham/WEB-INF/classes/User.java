import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private int id;
	private String name;
	private String password;
	private String usertype;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public User(String name, String password, String usertype,String street,String city,String state,String zip) {
		this.name=name;
		this.password=password;
		this.usertype=usertype;
		this.street=street;
		this.city=city;
		this.state=state;
		this.zip=zip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
}
