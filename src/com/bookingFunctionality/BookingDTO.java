package com.bookingFunctionality;

public class BookingDTO {
  private int customerId;
  private int concertId;
  private int numOfTickets;
  private int amount;

  public int getCustomerId() {
    return customerId;
  }
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
  public int getConcertId() {
    return concertId;
  }
  public void setConcertId(int concertId) {
    this.concertId = concertId;
  }
  public int getNumOfTickets() {
    return numOfTickets;
  }
  public void setNumOfTickets(int numOfTickets) {
    this.numOfTickets = numOfTickets;
  }
  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }

}