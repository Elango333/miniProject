package com.login;

import java.util.Scanner;
import com.bookingFunctionality.FeaturesPage;
import com.bookingFunctionality.LoginPage;

public class Login {
  public static String customerName;
  public void login() {
    Scanner loginSnr = new Scanner(System.in);
    System.out.println("Enter the name to login:");
    String name = loginSnr.next();
    customerName = name;
    System.out.println("Enter the Password:");
    String password = loginSnr.next();
    LoginDAO loginFunctions = new LoginDAO();
    boolean IsIdExists = loginFunctions.IsIdExistsForLogin(name, password);
    LoginPage loginpage = new LoginPage();
    if (IsIdExists) {
      System.out.println("\n╔═══════════════════════════╗\n" +
    		  			   "║  Logged in successfully!  ║\n" +
    		  			   "╚═══════════════════════════╝\n");

      FeaturesPage featurePage = new FeaturesPage();
      featurePage.showFeatures();
    } else {
      System.out.println("\n╔══════════════════════════╗\n" +
    		  			   "║ User does not exist!     ║\n" +
    		  			   "║ Please Register!         ║\n" +
    		  			   "╚══════════════════════════╝\n");

      loginpage.loginPage();
    }
    loginSnr.close();
  }
}