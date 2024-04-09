package com.admin;

import java.util.Scanner;

public class UpdateConcertDetails implements UpdateDetails{
	AdminDAO adminDAO = new AdminDAO();
	@Override
	public void toAskUpdateField() {
			Scanner updateFeatureSnr = new Scanner(System.in);
			 System.out.println("\n╔═════════════════════════════════════════════════╗\n" +
					 			  "║                                                 ║\n" +
					 			  "║ To update Venue location            - Press (1) ║\n" +
					 			  "║                                                 ║\n" +
					 			  "║ To update date                      - Press (2) ║\n" +
					 			  "║                                                 ║\n" +
					 			  "║ To update ticket price              - Press (3) ║\n" +
					 			  "║                                                 ║\n" +
					 			  "║ Back to features page               - Press (4) ║\n" +
					 			  "║                                                 ║\n" +
					 			  "╚═════════════════════════════════════════════════╝\n");

			 int option = updateFeatureSnr.nextInt();
			 
			 switch(option) {
			 case 1:
				 updateVenue();
				 break;
			 case 2:
				 updateDate();
				 break;
			 case 3:
				 updateTicketPrice();
				 break;
			 case 4:
				 AdminFeaturesPage adminPage = new AdminFeaturesPage();
			     adminPage.viewAdminFeatures();
				 break;
		     default:
		    	 System.out.println("Invalid option. Please try again.");
		    	 toAskUpdateField();
			 }
			 updateFeatureSnr.close();
		}
	
	public void updateVenue() {
		Scanner updateVenueSnr = new Scanner(System.in);
		System.out.println("Enter the ConcertId that you want to update:");
		int concertID = updateVenueSnr.nextInt();
		System.out.println("Enter the new Venue address:");
		String newVenueAddress = updateVenueSnr.next();
		adminDAO.updateVenue(concertID, newVenueAddress);
		updateVenueSnr.close();
	}
	
	public void updateDate() {
		Scanner updateDateSnr = new Scanner(System.in);
		System.out.println("Enter the ConcertId that you want to update:");
		int concertID = updateDateSnr.nextInt();
		System.out.println("Enter the new Date (yyyy-MM-dd):");
		String newDate = updateDateSnr.next();
		adminDAO.updateDate(concertID, newDate);
		updateDateSnr.close();
	}
	
	public void updateTicketPrice() {
		Scanner updateTicketPriceSnr = new Scanner(System.in);
		System.out.println("Enter the ConcertId that you want to update:");
		int concertID = updateTicketPriceSnr.nextInt();
		System.out.println("Enter the new Ticket price:");
		int newTicketPrice = updateTicketPriceSnr.nextInt();
		adminDAO.updateTicketPrice(concertID, newTicketPrice);
		updateTicketPriceSnr.close();
	}
}
