package com.bookingFunctionality;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.login.Login;
import com.utils.ConstantFile;
import com.utils.DatabaseConnection;

public class BookingDAO {
  private DatabaseConnection connection = DatabaseConnection.getInstance();
  private Connection conn = connection.getConnection();

  public void viewAllConcerts() {
    try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_getAllConcertDetails)) {
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Concert Details ------");
      System.out.println();
      while (resultSet.next()) {
        System.out.println("------ ------ ------\n" +
          "Concert_ID               : " + resultSet.getInt(1) + "\n" +
          "Concert name             : " + resultSet.getString(2) + "\n" +
          "Singer                   : " + resultSet.getString(3) + "\n" +
          "Venue                    : " + resultSet.getString(4) + "\n" +
          "City                     : " + resultSet.getString(5) + "\n" +
          "Date                     : " + resultSet.getDate(6) + "\n" +
          "Ticket Price             : " + resultSet.getInt(7) + "\n" +
          "Available Tickets        : " + resultSet.getInt(8) + "\n" +
                            "------ ------ ------\n");
      }
      BookTicketFunctions bookingTicket = new BookTicketFunctions();
      bookingTicket.askToBookTickets();
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in viewAllConcerts method");
    }
  }

  public void getCustomerID(int concertId, int ticketCount) {
    BookingDTO bookingDTO = new BookingDTO();
    try {
      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_getCustomerId);
      stmnt.setString(1, Login.customerName);
      ResultSet resultSet = stmnt.executeQuery();
      if (resultSet.next()) {
        bookingDTO.setConcertId(concertId);
        bookingDTO.setNumOfTickets(ticketCount);
        bookingDTO.setCustomerId(resultSet.getInt(1));
      }
      PreparedStatement stmntForAmount = conn.prepareStatement(ConstantFile.select_query_for_getTicketPrice);
      stmntForAmount.setInt(1, concertId);
      ResultSet resultSetForAmount = stmntForAmount.executeQuery();
      if (resultSetForAmount.next()) {
        bookingDTO.setAmount(ticketCount * (resultSetForAmount.getInt(1)));
      }
      PaymentFunction payment = new PaymentFunction();
      payment.getPayment(bookingDTO);
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in getCustomerID method");
    }
  }

  public int completePayment(BookingDTO bookingDetails) {
    int bookingId = 0;
    int availableTickets = 0;
    try {
      PreparedStatement stmntForGetAvailableTicket = conn.prepareStatement(ConstantFile.select_query_for_AvailableTickets);
      stmntForGetAvailableTicket.setInt(1, bookingDetails.getConcertId());
      ResultSet resultSet = stmntForGetAvailableTicket.executeQuery();
      if (resultSet.next()) {
        availableTickets = resultSet.getInt(1);
      }

      PreparedStatement stmntForUpdatingTickets = conn.prepareStatement(ConstantFile.update_query_for_ticketAvailability);
      stmntForUpdatingTickets.setInt(1, (availableTickets - bookingDetails.getNumOfTickets()));
      stmntForUpdatingTickets.setInt(2, bookingDetails.getConcertId());
      int rowsAffected = stmntForUpdatingTickets.executeUpdate();
      if (rowsAffected > 0) {
        PreparedStatement statement = conn.prepareStatement(ConstantFile.insert_query_for_bookingDetails, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, bookingDetails.getCustomerId());
        statement.setInt(2, bookingDetails.getConcertId());
        statement.setInt(3, bookingDetails.getNumOfTickets());
        statement.setInt(4, bookingDetails.getAmount());
        statement.executeUpdate();
        ResultSet resultSetForStoringDetails = statement.getGeneratedKeys();
        if (resultSetForStoringDetails.next()) {
          bookingId = resultSetForStoringDetails.getInt(1);
        }
      }
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in completePayment method");
    }
    return bookingId;
  }

  public void filterByConcertName() {
    BookTicketFunctions bookingTicket = new BookTicketFunctions();
    Scanner filterByConcertNameSnr = new Scanner(System.in);
    filterByConcertNameSnr.useDelimiter("\\n");

    try {
      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_concertName);
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Concert Names ------");
      System.out.println();
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
      System.out.println();
      System.out.println("------ ------ ------");
      System.out.println();
      System.out.println("Enter the Concert name");
      String concertName = filterByConcertNameSnr.next();

      try (PreparedStatement statement = conn.prepareStatement(ConstantFile.select_query_for_concertDetailsByConcertName)) {
        statement.setString(1, concertName);
        ResultSet resultSetForConcertName = statement.executeQuery();
        System.out.println();
        System.out.println("------ Concert Details ------");
        System.out.println();
        if (resultSetForConcertName.next()) {
          do {
            System.out.println(
              "------ ------ ------\n" +
              "Concert_ID               : " + resultSetForConcertName.getInt(1) + "\n" +
              "Concert name             : " + resultSetForConcertName.getString(2) + "\n" +
              "Singer                   : " + resultSetForConcertName.getString(3) + "\n" +
              "Venue                    : " + resultSetForConcertName.getString(4) + "\n" +
              "City                     : " + resultSetForConcertName.getString(5) + "\n" +
              "Date                     : " + resultSetForConcertName.getDate(6) + "\n" +
              "Ticket Price             : " + resultSetForConcertName.getInt(7) + "\n" +
              "Available Tickets        : " + resultSetForConcertName.getInt(8) + "\n" +
              "------ ------ ------\n");

          } while (resultSetForConcertName.next());


          bookingTicket.askToBookTickets();
        } else {
           System.out.println("\n╔════════════════════════════════════════╗\n" +
        		  				"║           No details found!            ║\n" +
        		  				"╚════════════════════════════════════════╝\n");
           bookingTicket.filter();
        }
      }
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in filterByConcert method");
    }
    filterByConcertNameSnr.close();
  }

  public void filterBySinger() {
    BookTicketFunctions bookingTicket = new BookTicketFunctions();
    Scanner filterBySingerSnr = new Scanner(System.in);
    filterBySingerSnr.useDelimiter("\\n");

    try {
      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_singers);
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Singers ------");
      System.out.println();
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
      System.out.println();
      System.out.println("------ ------ ------");
      System.out.println();
      System.out.println("Enter the Singer name");
      String singerName = filterBySingerSnr.next();

      try (PreparedStatement statement = conn.prepareStatement(ConstantFile.select_query_for_concertDetailsBySinger)) {
        statement.setString(1, singerName);
        ResultSet resultSetForSingerName = statement.executeQuery();
        System.out.println();
        System.out.println("------ Concert Details ------");
        System.out.println();
        if (resultSetForSingerName.next()) {
          do {
            System.out.println(
              "------ ------ ------\n" +
              "Concert_ID               : " + resultSetForSingerName.getInt(1) + "\n" +
              "Concert name             : " + resultSetForSingerName.getString(2) + "\n" +
              "Singer                   : " + resultSetForSingerName.getString(3) + "\n" +
              "Venue                    : " + resultSetForSingerName.getString(4) + "\n" +
              "City                     : " + resultSetForSingerName.getString(5) + "\n" +
              "Date                     : " + resultSetForSingerName.getDate(6) + "\n" +
              "Ticket Price             : " + resultSetForSingerName.getInt(7) + "\n" +
              "Available Tickets        : " + resultSetForSingerName.getInt(8) + "\n" +
              "------ ------ ------\n");

          } while (resultSetForSingerName.next());

          bookingTicket.askToBookTickets();
        } else {
           System.out.println("\n╔════════════════════════════════════════╗\n" +
        		  				"║           No details found!            ║\n" +
        		  				"╚════════════════════════════════════════╝\n");
           bookingTicket.filter();
        }
      }
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in filterBySingers method");
    }
    filterBySingerSnr.close();
  }

  public void filterByCity() {
    BookTicketFunctions bookingTicket = new BookTicketFunctions();
    Scanner filterByCitySnr = new Scanner(System.in);
    filterByCitySnr.useDelimiter("\\n");
    try {
      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_city);
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Cities ------");
      System.out.println();
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
      System.out.println();
      System.out.println("------ ------ ------");
      System.out.println();
      System.out.println("Enter the City name");
      String cityName = filterByCitySnr.next();

      try (PreparedStatement statement = conn.prepareStatement(ConstantFile.select_query_for_concertDetailsByCity)) {
        statement.setString(1, cityName);
        ResultSet resultSetForCityName = statement.executeQuery();
        System.out.println();
        System.out.println("------ Concert Details ------");
        System.out.println();
        if (resultSetForCityName.next()) {
          do {
            System.out.println(
              "------ ------ ------\n" +
              "Concert_ID               : " + resultSetForCityName.getInt(1) + "\n" +
              "Concert name             : " + resultSetForCityName.getString(2) + "\n" +
              "Singer                   : " + resultSetForCityName.getString(3) + "\n" +
              "Venue                    : " + resultSetForCityName.getString(4) + "\n" +
              "City                     : " + resultSetForCityName.getString(5) + "\n" +
              "Date                     : " + resultSetForCityName.getDate(6) + "\n" +
              "Ticket Price             : " + resultSetForCityName.getInt(7) + "\n" +
              "Available Tickets        : " + resultSetForCityName.getInt(8) + "\n" +
              "------ ------ ------\n");

          } while (resultSetForCityName.next());

          bookingTicket.askToBookTickets();
        } else {
           System.out.println("\n╔════════════════════════════════════════╗\n" +
        		  				"║           No details found!            ║\n" +
        		  				"╚════════════════════════════════════════╝\n");
           bookingTicket.filter();
        }
      }
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in filterByCity method");
    }
    filterByCitySnr.close();
  }

  public void filterByDate() {
      BookTicketFunctions bookingTicket = new BookTicketFunctions();
    Scanner filterByDateSnr = new Scanner(System.in);
    filterByDateSnr.useDelimiter("\\n");
    try {
      PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_date);
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Date ------");
      System.out.println();
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
      System.out.println();
      System.out.println("------ ------ ------");
      System.out.println();
      System.out.println("Enter the date");
      String dateString = filterByDateSnr.next();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
      try (PreparedStatement statement = conn.prepareStatement(ConstantFile.select_query_for_concertDetailsByDate)) {
        Date date = dateFormat.parse(dateString);
        statement.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet resultSetForDate = statement.executeQuery();
        System.out.println();
        System.out.println("------ Concert Details ------");
        System.out.println();
        if (resultSetForDate.next()) {
          do {
            System.out.println(
              "------ ------ ------\n" +
              "Concert_ID               : " + resultSetForDate.getInt(1) + "\n" +
              "Concert name             : " + resultSetForDate.getString(2) + "\n" +
              "Singer                   : " + resultSetForDate.getString(3) + "\n" +
              "Venue                    : " + resultSetForDate.getString(4) + "\n" +
              "City                     : " + resultSetForDate.getString(5) + "\n" +
              "Date                     : " + resultSetForDate.getDate(6) + "\n" +
              "Ticket Price             : " + resultSetForDate.getInt(7) + "\n" +
              "Available Tickets        : " + resultSetForDate.getInt(8) + "\n" +
              "------ ------ ------\n");

          } while (resultSetForDate.next());

          bookingTicket.askToBookTickets();
        } else {
           System.out.println("\n╔════════════════════════════════════════╗\n" +
        		  				"║           No details found!            ║\n" +
        		  				"╚════════════════════════════════════════╝\n");
           bookingTicket.filter();
        }
      }
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in filterByDate method");
    } catch (ParseException e) {
      System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
    }
    filterByDateSnr.close();
  }

  public void viewConcertDetails() {
    try (PreparedStatement stmnt = conn.prepareStatement(ConstantFile.select_query_for_bookedConcertDetails)) {
      stmnt.setString(1, Login.customerName);
      ResultSet resultSet = stmnt.executeQuery();
      System.out.println("------ Booked Concert Details ------");
      System.out.println();
      while (resultSet.next()) {
        System.out.println("------ ------ ------\n" +
          "Concert name          : " + resultSet.getString(1) + "\n" +
          "Singer                : " + resultSet.getString(2) + "\n" +
          "Venue                 : " + resultSet.getString(3) + "\n" +
          "City                  : " + resultSet.getString(4) + "\n" +
          "Date                  : " + resultSet.getDate(5) + "\n" +
          "Ticket count          : " + resultSet.getInt(6) + "\n" +
          "Amount                : " + resultSet.getInt(7) + "\n" +
          "BookingId             : " + resultSet.getInt(8) + "\n" +
          "------ ------ ------\n");
      }
      FeaturesPage features = new FeaturesPage();
      features.showFeatures();
    } catch (SQLException e) {
      System.out.println("SQL Exception occurred in viewConcertDetails method");
    }
  }
  
}