package com.admin;

import java.util.Scanner;
import com.bookingFunctionality.HomePage;

public class AdminFeaturesPage {

	public void viewAdminFeatures() {
		Scanner adminFeatureSnr = new Scanner(System.in);
		 System.out.println("\n╔═════════════════════════════════════════════════╗\n" +
				 			  "║                                                 ║\n" +
				 			  "║ To view Customers Details           - Press (1) ║\n" +
				 			  "║                                                 ║\n" +
				 			  "║ To view Concert Details             - Press (2) ║\n" +
				 		      "║                                                 ║\n" +
				 			  "║ To view Booked Concert Details      - Press (3) ║\n" +
				 			  "║                                                 ║\n" +
				 			  "║ To update Concert Details           - Press (4) ║\n" +
				 			  "║                                                 ║\n" +
				 			  "║ To add a new Concert                - Press (5) ║\n" +
				 			  "║                                                 ║\n" +
				 			  "║ To delete the Concert               - Press (6) ║\n" +
				 			  "║                                                 ║\n" +
				 			  "║ Back to home page                   - Press (7) ║\n" +
				 			  "║                                                 ║\n" +
				 			  "╚═════════════════════════════════════════════════╝\n");
		 
		 int option = adminFeatureSnr.nextInt();
		 AdminDAO adminFunc = new AdminDAO();
		 switch(option) {
		 case 1:
			 adminFunc.ShowAllCustomerDetails();
			 break;
		 case 2:
			 adminFunc.viewAllConcertsForAdmin();
			 break;
		 case 3:
			 adminFunc.viewBookedConcertDetails();
			 break;
		 case 4:
			 UpdateConcertDetailsSubclass updateConcert = new UpdateConcertDetailsSubclass();
			 updateConcert.toAskUpdateField();
			 break;
		 case 5:
			 AddConcert addconcert = new AddConcert();
			 addconcert.askConcertDetails();
			 break;
		 case 6:
			 adminFunc.deleteConcert();
			 break;
		 case 7:
			 HomePage homepage = new HomePage();
			 homepage.homePage();
			 break;
	     default:
	    	 System.out.println("Invalid option. Please try again.");
	    	 viewAdminFeatures();
		 }
		 adminFeatureSnr.close();
	}
}
