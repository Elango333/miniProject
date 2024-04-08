package com.bookingFunctionality;

import java.util.Scanner;

public abstract class BookTickets {

  public abstract void bookTickets();
  public abstract void askBookingDetails();

  public void askToBookTickets() {
    Scanner askToBookTicketSnr = new Scanner(System.in);
   System.out.println("\n╔════════════════════════════════════════╗\n" +
    					"║                                        ║\n" +
    					"║ To Book the tickets        - Press (1) ║\n" +
    					"║                                        ║\n" +
    					"║ Back to Booking page       - Press (2) ║\n" +
    					"║                                        ║\n" +
    					"╚════════════════════════════════════════╝\n");

    int option = askToBookTicketSnr.nextInt();
    switch (option) {
    case 1:
      askBookingDetails();
      break;
    case 2:
     bookTickets();
      break;
    default:
      System.out.println("Invalid option. Please try again.");
      askToBookTickets();
    }
    askToBookTicketSnr.close();
  }
}