package com.admin;

import java.util.Scanner;
import com.concert.ConcertDTO;

public class AddConcert {
	ConcertDTO concertDTO = new ConcertDTO();
	public void askConcertDetails() {
		Scanner askConcertDetailsSnr = new Scanner(System.in);
		askConcertDetailsSnr.useDelimiter("\\n");
		System.out.println("Enter the Concert name:");
		concertDTO.setConcertName(askConcertDetailsSnr.nextLine());
		System.out.println("Enter the Singer name:");
		concertDTO.setSingerName(askConcertDetailsSnr.nextLine());
		System.out.println("Enter the Venue address:");
		concertDTO.setVenueLocation(askConcertDetailsSnr.nextLine());;
		System.out.println("Enter the City name:");
		concertDTO.setCityName(askConcertDetailsSnr.nextLine());
		System.out.println("Enter the Date:");
		concertDTO.setDate(askConcertDetailsSnr.nextLine());
		System.out.println("Enter the Ticket price:");
		concertDTO.setTicketPrice(askConcertDetailsSnr.nextInt());
		System.out.println("Enter the Total tickets:");
		concertDTO.setTotalTickets(askConcertDetailsSnr.nextInt());
		System.out.println("Enter the Available tickets:");
		concertDTO.setAvailableTickets(askConcertDetailsSnr.nextInt());
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.addConcert(concertDTO);
		askConcertDetailsSnr.close();
	}
}
