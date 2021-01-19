package com.Client.auth;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Unit test for simple App.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class AppTest 
{
	 Client clt = new Client();
	 Connection cnx =clt.conn;
	
	


	@DisplayName("Connection Test")
	@BeforeEach
	public void setUp() throws Exception {

		clt.getConnected("jdbc:mysql://localhost:3306/gl?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}
	
	@Test
	public void CNXnotOpenException() throws Exception {
       assertThrows(SQLException.class, () -> {clt.getConnected("jdbc:mysql://localhost:3306/gl", "rootroot", "abcd1234");});
   }
	

	@AfterAll
	public void tearDown() throws Exception {
		clt = null;
	}
	

	@Test
	public void authenticateSuccess() throws Exception {
		clt.getConnected("jdbc:mysql://localhost:3306/gl", "root", "");
       assertTrue(clt.authenticate("1","1234",clt.conn), "Test OK");
   }
	
	@Test
	public void authenticateWithBadPass() throws Exception {
		clt.getConnected("jdbc:mysql://localhost:3306/gl", "root", "");
       assertFalse(clt.authenticate("clt1","5423",clt.conn), "Test Bad Password");
   }
	
	
	@Test
	public void authenticateWithUnknownClt() throws Exception {
		clt.getConnected("jdbc:mysql://localhost:3306/gl", "root", "");
       assertFalse(clt.authenticate("dodo","5423",clt.conn), "Unkown Client");
   }

	
	@Test
	public void authenticateWithNullParam() throws Exception {
		clt.getConnected("jdbc:mysql://localhost:3306/gl", "root", "");
       assertThrows(NullPointerException.class, () -> {clt.authenticate(null,null,clt.conn);});
   }
		
	
	@Test
	public void NullParam_Exception() {
       Exception exception = assertThrows(NullPointerException.class, () -> clt.authenticate(null, null, cnx));
       fail(exception.getMessage());
	}
}
