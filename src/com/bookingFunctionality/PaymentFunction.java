package com.bookingFunctionality;

import java.util.Scanner;

public class PaymentFunction implements PaymentInterface {

  @Override
  public void getPayment(BookingDTO bookingDetails) {
    Scanner paymentSnr = new Scanner(System.in);
    System.out.println("Enter the amount of Rs." + bookingDetails.getAmount() + " to complete your payment");
    int amount = paymentSnr.nextInt();
    if (amount == bookingDetails.getAmount()) {
      BookingDAO bookingFunc = new BookingDAO();
      int bookingID = bookingFunc.completePayment(bookingDetails);
      if (bookingID > 0) {
        System.out.println("\n╔═══════════════════════════╗\n" +
        					 "║     Payment successful!   ║\n" +
        					 "╚═══════════════════════════╝\n");

        System.out.println();
        System.out.println("═══════════════════════════\n" +
        				   "    Your Booking Id - " + bookingID + "    \n" +
        				   "═══════════════════════════\n");

        FeaturesPage featurePage = new FeaturesPage();
        featurePage.showFeatures();
      }
    } else {
      System.out.println("\n╔═══════════════════════════╗\n" +
    		  			   "║   Something went wrong!   ║\n" +
    		  			   "║         Try again         ║\n" +
    		  			   "╚═══════════════════════════╝\n");

      getPayment(bookingDetails);
    }
    paymentSnr.close();
  }

}