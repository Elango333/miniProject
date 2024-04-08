package com.bookingFunctionality;

public class ViewBookedConcertDetailsSubclass extends ViewBookedConcertDetails{

	@Override
	public void viewConcertDetails() {
		BookingDAO bookingFunc = new BookingDAO();
		bookingFunc.viewConcertDetails();
	}

}
