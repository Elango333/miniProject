package com.bookingFunctionality;

import java.util.Scanner;

public class BookTicketSubclass extends BookTickets implements BookingTicketsInterface{
  BookingDAO bookingFunc = new BookingDAO();
  @Override
  public void bookTickets() {
    Scanner bookTicketsSnr = new Scanner(System.in);
    System.out.println("\n╔════════════════════════════════════════╗\n" +
    					 "║                                        ║\n" +
    					 "║ To view all Concerts       - Press (1) ║\n" +
    					 "║                                        ║\n" +
    					 "║ To filter the Concerts     - Press (2) ║\n" +
    					 "║                                        ║\n" +
    					 "║ Back to Features page      - Press (3) ║\n" +
    					 "║                                        ║\n" +
    					 "╚════════════════════════════════════════╝\n");

    int options = bookTicketsSnr.nextInt();
    switch (options) {
    case 1:
      bookingFunc.viewAllConcerts();
      break;

    case 2:
      filter();
      break;

    case 3:
      FeaturesPage featuresPage = new FeaturesPage();
      featuresPage.showFeatures();
      break;

    default:
      System.out.println("Invalid option. Please try again.");
      bookTickets();
    }
    bookTicketsSnr.close();
  }

  @Override
  public void askBookingDetails() {
    Scanner askBookingDetailsSnr = new Scanner(System.in);
    System.out.println("Enter the Concert ID");
    int concertID = askBookingDetailsSnr.nextInt();
    System.out.println("Enter the number of tickets");
    int ticketCount = askBookingDetailsSnr.nextInt();
    bookingFunc.getCustomerID(concertID, ticketCount);
    askBookingDetailsSnr.close();
  }

  @Override
  public void filter() {
    Scanner filterSnr = new Scanner(System.in);
    System.out.println("\n" +
      "╔════════════════════════════════════════╗\n" +
      "║                                        ║\n" +
      "║ Filter by Concert name     - Press (1) ║\n" +
      "║                                        ║\n" +
      "║ Filter by Singer           - Press (2) ║\n" +
      "║                                        ║\n" +
      "║ Filter by City             - Press (3) ║\n" +
      "║                                        ║\n" +
      "║ Filter by Date             - Press (4) ║\n" +
      "║                                        ║\n" +
      "║ Back to booking page       - Press (5) ║\n" +
      "║                                        ║\n" +
      "╚════════════════════════════════════════╝\n");

    int option = filterSnr.nextInt();
    switch (option) {
    case 1:
      bookingFunc.filterByConcertName();
      break;
    case 2:
      bookingFunc.filterBySinger();
      break;
    case 3:
      bookingFunc.filterByCity();
      break;
    case 4:
      bookingFunc.filterByDate();
      break;
    case 5:
      bookTickets();
      break;
    default:
      System.out.println("Invalid option. Please try again.");
      filter();
    }
    filterSnr.close();
  }

}