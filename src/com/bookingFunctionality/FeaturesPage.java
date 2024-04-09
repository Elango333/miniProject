package com.bookingFunctionality;

import java.util.Scanner;

import com.customer.CustomerDAO;
import com.login.Login;

public class FeaturesPage {

  public void showFeatures() {
    Scanner featureSnr = new Scanner(System.in);
    System.out.println("\n╔═════════════════════════════════════════════════╗\n" +
    					 "║                                                 ║\n" +
    					 "║ To view your profile                - Press (1) ║\n" +
    					 "║                                                 ║\n" +
    					 "║ To book tickets                     - Press (2) ║\n" +
    				     "║                                                 ║\n" +
    					 "║ To view your booked concert details - Press (3) ║\n" +
    					 "║                                                 ║\n" +
    					 "║ To logout                           - Press (4) ║\n" +
    					 "║                                                 ║\n" +
    					 "╚═════════════════════════════════════════════════╝\n");

    int options = featureSnr.nextInt();

    switch (options) {
    case 1:
    	CustomerDAO customerFunc = new CustomerDAO();
		customerFunc.GetCustomerDetails(Login.customerName);
      break;

    case 2:
      BookTicketFunctions bookticket = new BookTicketFunctions();
      bookticket.bookTickets();
      break;

    case 3:
    	BookingDAO bookingFunc = new BookingDAO();
		bookingFunc.viewConcertDetails();
      break;

    case 4:
      HomePage homepage = new HomePage();
      homepage.homePage();
      break;

    default:
      System.out.println("Invalid option. Please try again.");
      showFeatures();
    }

    featureSnr.close();
  }
}