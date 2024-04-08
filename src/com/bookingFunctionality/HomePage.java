package com.bookingFunctionality;

import java.util.Scanner;
import com.admin.AdminFeaturesPage;

public class HomePage {
  Scanner homePageSnr = new Scanner(System.in);
  public static void main(String[] args) {
    HomePage homepage = new HomePage();
    homepage.homePage();
  }
  public void homePage() {
    System.out.println(
      "\n" +
      "╔════════════════════════════╗\n" +
      "║                            ║\n" +
      "║          WELCOME!!!        ║\n" +
      "║                            ║\n" +
      "╠════════════════════════════╣\n" +
      "║                            ║\n" +
      "║ Admin login    - Press (1) ║\n" +
      "║                            ║\n" +
      "║ User login     - Press (2) ║\n" +
      "║                            ║\n" +
      "║ Exit           - Press (3) ║\n" +
      "║                            ║\n" +
      "╚════════════════════════════╝");

    int optionForLogin = homePageSnr.nextInt();

    //Admin login
    switch(optionForLogin) {
    case 1:
      System.out.println("Enter the admin name:");
      String adminName = homePageSnr.next();
      System.out.println("Enter the admin password:");
      String adminPassword = homePageSnr.next();
      if ((adminName.equals("admin@bs")) && (adminPassword.equals("BS@admin"))) {
    	  AdminFeaturesPage adminPage = new AdminFeaturesPage();
    	  adminPage.viewAdminFeatures();
      } else {
        System.out.println("\n╔═════════════════════════════════╗\n" +
        					 "║    Incorrect login details⚠️    ║\n" +
        					 "╚═════════════════════════════════╝\n");
        homePage();
      }
    break;
 
    case 2:
      //User login
      LoginPage loginPage = new LoginPage();
      loginPage.loginPage();
      break;
      
    case 3:
      System.out.println("Thank you..Bye Bye👋");
      System.exit(0);
      break;
    
    default:
      System.out.println("Invalid option. Please try again.");	
      homePage();
     }
    homePageSnr.close();
  }
}