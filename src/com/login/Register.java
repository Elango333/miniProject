package com.login;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.bookingFunctionality.LoginPage;
import com.customer.CustomerDTO;

public class Register {

  public void register() {
	  
	   System.out.println("\n" +
			      "╔════════════════════════════════════════╗\n" +
			      "║                                        ║\n" +
			      "║                  Note⚠️                ║\n" +
			      "║                                        ║\n" +
			      "║> Name must be above 3 characters.      ║\n" +
			      "║                                        ║\n" +
			      "║> Enter proper email Id.                ║\n" +
			      "║                                        ║\n" +
			      "║> Enter the valid mobile number.        ║\n" +
			      "║                                        ║\n" +
			      "║> Password must have one upper case,    ║\n" +
			      "║                                        ║\n" +
			      "║ one number and minimum 8 characters.   ║\n" +
			      "║                                        ║\n" +
			      "╚════════════════════════════════════════╝\n\n");
	   
	  
    Scanner registerSnr = new Scanner(System.in);
    System.out.println("Enter the name to register");
    String name = registerSnr.next();
    // Minimum 3 characters for name
    boolean minLengthConditionForName = name.length() >= 3;
    if(!minLengthConditionForName) {
    	  System.out.println("\n╔═════════════════════════════════╗\n" +
    			  			   "║       Failed to Register!       ║\n" +
				               "║⚠️Name must be above 3 characters║\n" +
					           "╚═════════════════════════════════╝\n");
    	  register();
    }
    System.out.println("Enter the emailID:");
    String emailId = registerSnr.next();
    // Email check
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(emailId);
    boolean mailCheck = matcher.matches();
    if(!mailCheck) {
    	  System.out.println("\n╔═════════════════════════════════╗\n" +
    			  			   "║       Failed to Register!       ║\n" +
    			  			   "║   ⚠️ Enter the valid email Id   ║\n" +
    			  			   "╚═════════════════════════════════╝\n");
    	  	register();
    }
    System.out.println("Enter the mobile number:");
    String mobileNumber = registerSnr.next();
    // MobileNumber check
    boolean mobileNumberLength = mobileNumber.length() >= 10;
    if(!mobileNumberLength) {
    	  System.out.println("\n╔══════════════════════════════════╗\n" +
    			  			   "║        Failed to Register!       ║\n" +
    			  			   "║ ⚠️ Enter the valid mobile number ║\n" +
    			  			   "╚══════════════════════════════════╝\n");
    	  register();
    }
    System.out.println("Enter the Password:");
    String password = registerSnr.next();
    // Minimum 8 characters for password
    boolean minLengthConditionForPassword = password.length() >= 8;
    // At least 1 uppercase letter
    boolean uppercaseCondition = Pattern.compile("[A-Z]").matcher(password).find();
    // At least 1 number
    boolean numberCondition = Pattern.compile("\\d").matcher(password).find();
    if((!minLengthConditionForPassword) || (!uppercaseCondition) || (!numberCondition)) {
    	  System.out.println("\n╔════════════════════════════════════╗\n" +
    			  			   "║       Failed to Register!          ║\n" +
    			  			   "║⚠️Password must have one upper case,║\n" +
    			  			   "║ one number and minimum 8 characters║\n" +
                               "╚════════════════════════════════════╝\n");
    	  register();
    }
    LoginDAO loginFunc = new LoginDAO();
    boolean userNameExists = loginFunc.IsIdExistsForLogin(name, password);
    LoginPage loginpage = new LoginPage();
    if (userNameExists) {
      System.out.println("\n╔═════════════════════════════════╗\n" +
    		  			   "║       User already exists!      ║\n" +
    		  			   "╚═════════════════════════════════╝\n");
      loginpage.loginPage();
    }

    if (minLengthConditionForName && minLengthConditionForPassword && uppercaseCondition && numberCondition && mobileNumberLength && mailCheck && !userNameExists) {
      CustomerDTO customer = new CustomerDTO();
      customer.setName(name);
      customer.setEmailId(emailId);
      customer.setMobileNumber(mobileNumber);
      customer.setPassword(loginFunc.hashPassword(password));

      boolean IsAccountCreated = loginFunc.storeRegisterDetails(customer);
      if (IsAccountCreated) {
        System.out.println("\n╔═════════════════════════════════╗\n" +
        					 "║     Successfully Registered!    ║\n" +
        					 "╚═════════════════════════════════╝\n");

        Login loginPage = new Login();
        loginPage.login();
      } else {
        System.out.println("\n╔═════════════════════════════════╗\n" +
        					 "║      ⚠️Failed to Register!      ║\n" +
        					 "╚═════════════════════════════════╝\n");

        loginpage.loginPage();
      }
    } else {
      System.out.println("\n╔═════════════════════════════════╗\n" +
    		  			   "║      ⚠️Failed to Register!      ║\n" +
    		  			   "╚═════════════════════════════════╝\n");

      loginpage.loginPage();
    }

    registerSnr.close();
  }

}