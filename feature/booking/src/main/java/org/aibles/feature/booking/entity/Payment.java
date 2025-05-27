package org.aibles.feature.booking.entity;

public class Payment {

  private String id;
  private Long amount;
  private String paymentType;
  private String paymentStatus;
  private Ticket ticket;

  public Payment() {
  }

  public Payment(String id, Long amount, String paymentType, String paymentStatus) {
    this.id = id;
    this.amount = amount;
    this.paymentType = paymentType;
    this.paymentStatus = paymentStatus;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }
}
