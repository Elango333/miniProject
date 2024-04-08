package com.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bookingFunctionality.FeaturesPage;
import com.utils.ConstantFile;
import com.utils.DatabaseConnection;

public class CustomerDAO {
  private DatabaseConnection connection = DatabaseConnection.getInstance();
  private Connection conn = connection.getConnection();

  public void GetCustomerDetails(String name) {
    try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_customerDetails)) {
      stmnt.setString(1, name);
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Customer details ------");
      System.out.println();
      while (resultSet.next()) {
    	  System.out.println("CustomerID           : " + resultSet.getInt(1) + "\n" +
    			  			 "Name                 : " + resultSet.getString(2) + "\n" +
    			  			 "EmailId              : " + resultSet.getString(3) + "\n" +
    			  			 "MobileNumber         : " + resultSet.getString(4));

      }
      System.out.println("------ ------ ------");
      System.out.println();
      FeaturesPage featuresPage = new FeaturesPage();
      featuresPage.showFeatures();
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in GetCustomerDetails method");
    }
  }
}