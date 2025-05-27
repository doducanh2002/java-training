package org.aibles.feature.booking.entity;

public class TripContact {

  private String id;
  private String primaryEmail;
  private String secondaryEmail;
  private String phoneNumber;
  private Ticket ticket;

  public TripContact() {
  }

  public TripContact(String id, String primaryEmail, String secondaryEmail,
      String phoneNumber, Ticket ticket) {
    this.id = id;
    this.primaryEmail = primaryEmail;
    this.secondaryEmail = secondaryEmail;
    this.phoneNumber = phoneNumber;
    this.ticket = ticket;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPrimaryEmail() {
    return primaryEmail;
  }

  public void setPrimaryEmail(String primaryEmail) {
    this.primaryEmail = primaryEmail;
  }

  public String getSecondaryEmail() {
    return secondaryEmail;
  }

  public void setSecondaryEmail(String secondaryEmail) {
    this.secondaryEmail = secondaryEmail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }
}
