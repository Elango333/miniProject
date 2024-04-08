package com.bookingFunctionality;

import java.util.Scanner;
import com.customer.ViewProfileSubclass;

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
      ViewProfileSubclass viewprofile = new ViewProfileSubclass();
      viewprofile.viewProfile();
      break;

    case 2:
      BookTicketSubclass bookticket = new BookTicketSubclass();
      bookticket.bookTickets();
      break;

    case 3:
      ViewBookedConcertDetailsSubclass viewConcertdetails = new ViewBookedConcertDetailsSubclass();
      viewConcertdetails.viewConcertDetails();
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