package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.bookingFunctionality.BookTicketSubclass;
import com.concert.ConcertDTO;
import com.utils.ConstantFile;
import com.utils.DatabaseConnection;

public class AdminDAO {
	
	 private DatabaseConnection connection = DatabaseConnection.getInstance();
	 private Connection conn = connection.getConnection();
	 AdminFeaturesPage adminPage = new AdminFeaturesPage();
	 public void ShowAllCustomerDetails() {
		  try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_getAllCustomerDetails)) {
		      ResultSet resultSet = stmnt.executeQuery();
		      System.out.println("------ Customer details ------");
		      System.out.println();
		      while (resultSet.next()) {
		    	  System.out.println("------ ------ ------\n" +
		                   "CustomerID           : " + resultSet.getInt(1) + "\n" +
		                   "Name                 : " + resultSet.getString(2) + "\n" +
		                   "EmailId              : " + resultSet.getString(3) + "\n" +
		                   "MobileNumber         : " + resultSet.getString(4));
		      }
		      System.out.println("------ ------ ------");
		      System.out.println();
		     AdminFeaturesPage adminPage = new AdminFeaturesPage();
		     adminPage.viewAdminFeatures();
		    } catch (SQLException e) {
		      System.out.println("SQL Exception occurred in ShowAllCustomerDetails method");
		    }
		  }
	 public void updateVenue(int concertID, String newVenue) {
		 try {
	      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.update_query_for_venue);
	      stmnt.setString(1, newVenue);
	      stmnt.setInt(2, concertID);
	      int rowsAffected = stmnt.executeUpdate();
	      if (rowsAffected > 0) {
	    	  System.out.println("\n╔═══════════════════════════╗\n" +
		  			               "║   Successfully Updated!   ║\n" +
		  			               "╚═══════════════════════════╝\n");
	    	  adminPage.viewAdminFeatures();
	      }
	      else {
	    	  System.out.println("\n╔═══════════════════════════╗\n" +
			                       "║     Unable to  Update!    ║\n" +
			                       "╚═══════════════════════════╝\n");
	    	  adminPage.viewAdminFeatures();
	      }
		 }
		 catch (SQLException e) {
		      System.out.println("SQL Exception occurred in updateVenue method");
	     }
		
	 }
	 
	 public void updateDate(int concertID, String newDate) {
		 try {
		      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.update_query_for_date);
		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		      Date date = dateFormat.parse(newDate);
		      stmnt.setDate(1, new java.sql.Date(date.getTime()));
		      stmnt.setInt(2, concertID);
		      int rowsAffected = stmnt.executeUpdate();
		      if (rowsAffected > 0) {
		    	  System.out.println("\n╔═══════════════════════════╗\n" +
			  			               "║   Successfully Updated!   ║\n" +
			  			               "╚═══════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures();
		      }
		      else {
		    	  System.out.println("\n╔═══════════════════════════╗\n" +
				                       "║     Unable to  Update!    ║\n" +
				                       "╚═══════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures();
		      }
			 }
			 catch (SQLException e) {
			      System.out.println("SQL Exception occurred in updateDate method");
		     }
		 	 catch (ParseException e) {
		 		  System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
		     }
	 }
	 
	 public void updateTicketPrice(int concertID, int newTicketPrice) {
		 try {
		      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.update_query_for_ticketPrice);
		      stmnt.setInt(1, newTicketPrice);
		      stmnt.setInt(2, concertID);
		      int rowsAffected = stmnt.executeUpdate();
		      if (rowsAffected > 0) {
		    	  System.out.println("\n╔═══════════════════════════╗\n" +
			  			               "║   Successfully Updated!   ║\n" +
			  			               "╚═══════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures();
		      }
		      else {
		    	  System.out.println("\n╔═══════════════════════════╗\n" +
				                       "║     Unable to  Update!    ║\n" +
				                       "╚═══════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures();
		      }
			 }
			 catch (SQLException e) {
			      System.out.println("SQL Exception occurred in updateTicketPrice method");
		     }
	 }
	 
	 public void addConcert(ConcertDTO concertDTO) {
		 try {
		      PreparedStatement statement = conn.prepareStatement(ConstantFile.insert_query_for_newConcert);
		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		      Date date = dateFormat.parse(concertDTO.getDate());
		      statement.setString(1, concertDTO.getConcertName());
		      statement.setString(2, concertDTO.getSingerName());
		      statement.setString(3, concertDTO.getVenueLocation());
		      statement.setString(4, concertDTO.getCityName());
		      statement.setDate(5, new java.sql.Date(date.getTime()));
		      statement.setInt(6, concertDTO.getTicketPrice());
		      statement.setInt(7, concertDTO.getTotalTickets());
		      statement.setInt(8, concertDTO.getAvailableTickets());
		      int rowsAffected = statement.executeUpdate();
		      if (rowsAffected > 0) {
		    	  System.out.println("\n╔═════════════════════════════════════╗\n" +
 			                           "║   Successfully added new Concert!   ║\n" +
 			                           "╚═════════════════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures(); 
		      }
		      else {
		    	  System.out.println("\n╔════════════════════════════════╗\n" +
		    			  			   "║   Unable to add new Concert!   ║\n" +
		    			  			   "╚════════════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures(); 
		      }
		 }	
		 catch (SQLException e) {
		      System.out.println("SQL Exception occurred in addConcert method");
	     }
	 	 catch (ParseException e) {
	 		  System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
	     }
	 }
	 
	 public void deleteConcert() {
		 Scanner deleteConcertSnr = new Scanner(System.in);
		 System.out.println("Enter the concertID to delete:");
		 int concertID = deleteConcertSnr.nextInt();
		  try {
		      PreparedStatement statement = conn.prepareStatement(ConstantFile.delete_query_for_concert);
		      statement.setInt(1, concertID);
		      int rowsAffected = statement.executeUpdate();
		      if (rowsAffected > 0) {
		    	  System.out.println("\n╔═══════════════════════════════════════╗\n" +
		    			  			   "║   Successfully deleted the Concert!   ║\n" +
		    			  			   "╚═══════════════════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures();   
		      }
		      else {
		    	  System.out.println("\n╔═══════════════════════════════════╗\n" +
		    			  		       "║   Unable to delete the Concert!   ║\n" +
		    			  			   "╚═══════════════════════════════════╝\n");
		    	  adminPage.viewAdminFeatures(); 	
		      }
		  }
		  catch (SQLException e) {
			      System.out.println("SQL Exception occurred in deleteConcert method");
		  }
		  deleteConcertSnr.close();
	 }
	 
	 public void viewAllConcertsForAdmin() {
		    try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_getAllConcertDetails)) {
		      ResultSet resultSet = stmnt.executeQuery();
		      System.out.println("------ Concert Details ------");
		      System.out.println();
		      while (resultSet.next()) {
		        System.out.println("------ ------ ------\n" +
		          "Concert_ID               : " + resultSet.getInt(1) + "\n" +
		          "Concert name             : " + resultSet.getString(2) + "\n" +
		          "Singer                   : " + resultSet.getString(3) + "\n" +
		          "Venue                    : " + resultSet.getString(4) + "\n" +
		          "City                     : " + resultSet.getString(5) + "\n" +
		          "Date                     : " + resultSet.getDate(6) + "\n" +
		          "Ticket Price             : " + resultSet.getInt(7) + "\n" +
		          "Available Tickets        : " + resultSet.getInt(8) + "\n" +
		          "------ ------ ------\n");
		      }
		      AdminFeaturesPage adminPage = new AdminFeaturesPage();
			  adminPage.viewAdminFeatures();
		    } catch (SQLException e) {
		      System.out.println("SQL Exception occurred in viewAllConcerts method");
		    }
		  }
	 
	 public void viewBookedConcertDetails() {
		   try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_ViewBookedConcertDetails)) {
			      ResultSet resultSet = stmnt.executeQuery();
			      System.out.println("------Booked Concert Details ------");
			      System.out.println();
			      while (resultSet.next()) {
			        System.out.println("------ ------ ------\n" +
			          "Booking_ID               : " + resultSet.getInt(1) + "\n" +
			          "Concert name             : " + resultSet.getString(2) + "\n" +
			          "Singer name              : " + resultSet.getString(3) + "\n" +
			          "Customer name            : " + resultSet.getString(4) + "\n" +
			          "Ticket count             : " + resultSet.getInt(5) + "\n" +
			          "Amount                   : " + resultSet.getInt(6) + "\n" +
			          "------ ------ ------\n");
			      }
			      AdminFeaturesPage adminPage = new AdminFeaturesPage();
				  adminPage.viewAdminFeatures();
			    } catch (SQLException e) {
			      System.out.println("SQL Exception occurred in viewBookedConcertDetails method");
			    }
	 }
}
