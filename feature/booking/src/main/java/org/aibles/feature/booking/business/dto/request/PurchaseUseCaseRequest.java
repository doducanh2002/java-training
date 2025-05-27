package org.aibles.feature.booking.business.dto.request;

import java.util.List;

public class PurchaseUseCaseRequest {

  private String ticketId;
  private Long totalPrice;
  private String tripType;
  private List<TripPurchaseUseCaseRequest> trips;
  private List<PassengerInfoPurchaseUseCaseRequest> passengerInfos;
  private TripContactPurchaseUseCaseRequest tripContact;
  private List<String> specialAssistances;
  private String promotion;

  public PurchaseUseCaseRequest() {
  }

  public PurchaseUseCaseRequest(String ticketId, Long totalPrice, String tripType,
      List<TripPurchaseUseCaseRequest> trips,
      List<PassengerInfoPurchaseUseCaseRequest> passengerInfos,
      TripContactPurchaseUseCaseRequest tripContact, List<String> specialAssistances,
      String promotion) {
    this.ticketId = ticketId;
    this.totalPrice = totalPrice;
    this.tripType = tripType;
    this.trips = trips;
    this.passengerInfos = passengerInfos;
    this.tripContact = tripContact;
    this.specialAssistances = specialAssistances;
    this.promotion = promotion;
  }

  public String getTicketId() {
    return ticketId;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  public Long getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Long totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getTripType() {
    return tripType;
  }

  public void setTripType(String tripType) {
    this.tripType = tripType;
  }

  public List<TripPurchaseUseCaseRequest> getTrips() {
    return trips;
  }

  public void setTrips(List<TripPurchaseUseCaseRequest> trips) {
    this.trips = trips;
  }

  public List<PassengerInfoPurchaseUseCaseRequest> getPassengerInfos() {
    return passengerInfos;
  }

  public void setPassengerInfos(List<PassengerInfoPurchaseUseCaseRequest> passengerInfos) {
    this.passengerInfos = passengerInfos;
  }

  public TripContactPurchaseUseCaseRequest getTripContact() {
    return tripContact;
  }

  public void setTripContact(TripContactPurchaseUseCaseRequest tripContact) {
    this.tripContact = tripContact;
  }

  public List<String> getSpecialAssistances() {
    return specialAssistances;
  }

  public void setSpecialAssistances(List<String> specialAssistances) {
    this.specialAssistances = specialAssistances;
  }

  public String getPromotion() {
    return promotion;
  }

  public void setPromotion(String promotion) {
    this.promotion = promotion;
  }

  public static PurchaseUseCaseRequest.PurchaseUseCaseBuilder builder() {
    return new PurchaseUseCaseRequest.PurchaseUseCaseBuilder();
  }

  public static class PurchaseUseCaseBuilder {

    private String ticketId;
    private Long totalPrice;
    private String tripType;
    private List<TripPurchaseUseCaseRequest> trips;
    private List<PassengerInfoPurchaseUseCaseRequest> passengerInfos;
    private TripContactPurchaseUseCaseRequest tripContact;
    private List<String> specialAssistances;
    private String promotion;

    public PurchaseUseCaseBuilder() {
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder ticketId(String ticketId) {
      this.ticketId = ticketId;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder totalPrice(Long totalPrice) {
      this.totalPrice = totalPrice;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder tripType(String tripType) {
      this.tripType = tripType;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder trips(
        List<TripPurchaseUseCaseRequest> trips) {
      this.trips = trips;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder passengerInfos(
        List<PassengerInfoPurchaseUseCaseRequest> passengerInfos) {
      this.passengerInfos = passengerInfos;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder tripContact(
        TripContactPurchaseUseCaseRequest tripContact) {
      this.tripContact = tripContact;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder specialAssistances(
        List<String> specialAssistances) {
      this.specialAssistances = specialAssistances;
      return this;
    }

    public PurchaseUseCaseRequest.PurchaseUseCaseBuilder promotion(
        String promotion) {
      this.promotion = promotion;
      return this;
    }

    public PurchaseUseCaseRequest build() {
      return new PurchaseUseCaseRequest(ticketId, totalPrice, tripType, trips, passengerInfos,
          tripContact, specialAssistances, promotion);
    }
  }
}
