package com.Client.auth;



import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class Client {

	 
	private String ID;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	
	private boolean isPremium;
	private int ptsFid;
	 
	Connection conn = null;
	  private ResultSet resultSet = null;
	
	
	public Client() {
	}
	
	public Client(String iD, String nom, String prenom, String email, String password, boolean isPrem) {
		super();
		ID = iD;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.isPremium = isPrem;
	}
	
	
	public int getPtsFid() {
		return ptsFid;
	}


	public void setPtsFid(int ptsFid) {
		this.ptsFid = ptsFid;
	}


	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isPrem() {
		return isPremium;
	}
	public void setPrem(boolean isPrem) {
		this.isPremium = isPrem;
	}
	
	
	public void getConnected (String url, String user, String password) throws SQLException {
		
		conn = DriverManager.getConnection(url, user, password);
        
        
        if (conn == null)
        {
        	throw new SQLException("connection not open");
        }
        else
        	System.out.println("Connected");
	}
	
	
	
	
	
	public boolean authenticate (String id, String pwd, Connection cnx) throws SQLException, NullPointerException {
		   
		if (id == null || pwd==null) 
		{
	         throw new NullPointerException("Parameters must be not null");
		}
		
		else {
			
		
		   String pass = null;
		   //statement = cnx.createStatement();
		   //resultSet = statement.executeQuery("select password from gl.client where ID ="+id);
		   
		   PreparedStatement st = cnx.prepareStatement("SELECT password FROM client WHERE ID = ?");

			      st.setString(1, id);
			      resultSet = st.executeQuery();
		   
		   int count=0;
		   while (resultSet.next())
		      {
		         pass = resultSet.getString("password");
		         System.out.println(" password in database : "+ pass);
		         count++;
		      }
		   
		   if (count==0)
		   { System.out.println(" No such client in the database");
		      return false;
		   }
		   
		   else if (count == 1)
		   {
			   if (pass.compareTo(pwd) == 0)
			     {System.out.println(" Welcome Client : you are authenticate");	
			         return true;
			      }
			   else
				   {System.out.println(" wrong Password");	
				     return false;
				   }
		   }
		   else 
		   {System.out.println(" Error : two instances of same client");
	       return false;}
	}
	
	}
}
