package org.aibles.feature.booking.business.dto.response;

public class CalculatePriceResponse {

  private String ticketId;

  private long price;

  public CalculatePriceResponse() {
  }

  public CalculatePriceResponse(String ticketId, long price) {
    this.ticketId = ticketId;
    this.price = price;
  }

  public String getTicketId() {
    return ticketId;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }
}
