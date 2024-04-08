package com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import com.customer.CustomerDTO;
import com.utils.ConstantFile;
import com.utils.DatabaseConnection;

public class LoginDAO {
  private DatabaseConnection connection = DatabaseConnection.getInstance();
  private Connection conn = connection.getConnection();

  // Encrypt the password
  public String hashPassword(String plainTextPassword) {
    return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
  }

  // Decrypt the password
  public boolean checkPassword(String plainTextPassword, String hashedPassword) {
    return BCrypt.checkpw(plainTextPassword, hashedPassword);
  }

  // Store User register details to DB
  boolean storeRegisterDetails(CustomerDTO customer) {
    boolean IsAccountCreated = false;
    try {
      PreparedStatement statement = conn.prepareStatement(ConstantFile.insert_query_for_Register);
      statement.setString(1, customer.getName());
      statement.setString(2, customer.getEmailId());
      statement.setString(3, customer.getMobileNumber());
      statement.setString(4, customer.getPassword());
      int rowsAffected = statement.executeUpdate();

      if (rowsAffected > 0) {
        IsAccountCreated = true;
      } else {
        IsAccountCreated = false;
      }

    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in storeRegisterDetails method");
    } catch (Exception e) {
      System.out.println("Error occurred in storeRegisterDetails method");
    }
    return IsAccountCreated;
  }

  //Check username & password ---> For login
  boolean IsIdExistsForLogin(String name, String password) {
    boolean IsAccountExists = false;
    try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_CheckAccount)) {
      stmnt.setString(1, name);
      ResultSet resultSet = stmnt.executeQuery();
      if (resultSet.next()) {
        String hashedPasswordFromDB = resultSet.getString("Password");
        if (checkPassword(password, hashedPasswordFromDB)) {
          IsAccountExists = true;
        } else {
          IsAccountExists = false;
        }
      } else {
        IsAccountExists = false;
      }
    } catch (SQLException e) {
      System.out.println("Error executing SQL query in IsIdExistsForLogin method");
    }
    return IsAccountExists;
  }

}