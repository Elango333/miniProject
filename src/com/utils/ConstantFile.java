package com.utils;

public class ConstantFile {
   public static int adminLogin = 1;
   public static int userLogin = 2;
   public static int registerUser = 1;
   public static int loginUser = 2;
   public static String select_query_for_CheckAccount = "select Password FROM Customer WHERE Name = ?";
   public static String insert_query_for_Register = "insert into Customer(Name, EmailId, PhoneNumber, Password) values(?, ?, ?, ?)";
   public static String select_query_for_getAllConcertDetails = "select Concert_Id, Concert_name, Singers, Venue_Location, City_Name, Date, Ticket_Price, Available_Tickets from Concert_Details";
   public static String select_query_for_getCustomerId = "select Customer_Id from Customer where Name = ?";
   public static String select_query_for_getTicketPrice = "select Ticket_Price from Concert_Details where Concert_Id = ?";
   public static String insert_query_for_bookingDetails = "insert into Customer_BookingDetails (Customer_Id, Concert_Id, No_Of_Tickets_Booked, Total_Amount) values(?, ?, ?, ?)";
   public static String select_query_for_AvailableTickets = "select Available_Tickets from Concert_Details where Concert_Id = ?";
   public static String update_query_for_ticketAvailability = "update Concert_Details set Available_Tickets = ? where Concert_Id = ?";
   public static String select_query_for_concertName = "select Concert_name from Concert_Details";
   public static String select_query_for_singers = "select Singers from Concert_Details";
   public static String select_query_for_city = "select City_Name from Concert_Details";
   public static String select_query_for_date = "select Date from Concert_Details order by Date";
   public static String select_query_for_concertDetailsByConcertName = "select Concert_Id, Concert_name, Singers, Venue_Location, City_Name, Date, Ticket_Price, Available_Tickets from Concert_Details where Concert_name = ?";
   public static String select_query_for_concertDetailsBySinger = "select Concert_Id, Concert_name, Singers, Venue_Location, City_Name, Date, Ticket_Price, Available_Tickets from Concert_Details where Singers = ?";
   public static String select_query_for_concertDetailsByCity = "select Concert_Id, Concert_name, Singers, Venue_Location, City_Name, Date, Ticket_Price, Available_Tickets from Concert_Details where City_Name = ?";
   public static String select_query_for_concertDetailsByDate = "select Concert_Id, Concert_name, Singers, Venue_Location, City_Name, Date, Ticket_Price, Available_Tickets from Concert_Details where Date = ?";
   public static String select_query_for_customerDetails = "select * from Customer where Name = ?";
   public static String select_query_for_bookedConcertDetails = "select Concert_Details.Concert_name, Concert_Details.Singers, Concert_Details.Venue_Location, Concert_Details.City_Name, Concert_Details.Date, Customer_BookingDetails.No_Of_Tickets_Booked, Customer_BookingDetails.Total_Amount, Customer_BookingDetails.Booking_Id FROM Customer JOIN Customer_BookingDetails ON Customer.Customer_Id = Customer_BookingDetails.Customer_Id JOIN Concert_Details ON Concert_Details.Concert_Id = Customer_BookingDetails.Concert_Id WHERE Name = ? order by Customer_BookingDetails.Booking_Id desc";
   public static String select_query_for_getAllCustomerDetails = "select Customer_Id, Name, EmailId, PhoneNumber from Customer";
   public static String update_query_for_venue = "update Concert_Details set Venue_Location = ? where Concert_Id = ?";
   public static String update_query_for_date = "update Concert_Details set Date = ? where Concert_Id = ?";
   public static String update_query_for_ticketPrice = "update Concert_Details set Ticket_Price = ? where Concert_Id = ?";
   public static String insert_query_for_newConcert = "insert into Concert_Details (Concert_name, Singers, Venue_Location, City_Name, Date, Ticket_Price, Total_Tickets, Available_Tickets) values(?, ?, ?, ?, ?, ?, ?, ?)";
   public static String delete_query_for_concert = "delete from Concert_Details where Concert_Id = ?";
   public static String select_query_for_ViewBookedConcertDetails = "select  Customer_BookingDetails.Booking_Id, Concert_Details.Concert_name, Concert_Details.Singers, Customer.Name, Customer_BookingDetails.No_Of_Tickets_Booked, Customer_BookingDetails.Total_Amount FROM Customer JOIN Customer_BookingDetails ON Customer.Customer_Id = Customer_BookingDetails.Customer_Id JOIN Concert_Details ON Concert_Details.Concert_Id = Customer_BookingDetails.Concert_Id";
}
