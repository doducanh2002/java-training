package org.aibles.feature.booking.entity;

import java.time.LocalDate;
import java.util.List;
import org.aibles.feature.booking.business.dto.request.PassengerInfoPurchaseUseCaseRequest;

public class PassengerInfo {

  private String id;
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String gender;
  private String country;
  private Ticket ticket;
  private List<SpecialAssistance> specialAssistances;

  public PassengerInfo() {
  }

  public PassengerInfo(String id, String firstName, String middleName, String lastName,
      LocalDate dateOfBirth, String gender, String country) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.country = country;
  }

  public PassengerInfo(PassengerInfoPurchaseUseCaseRequest purchaseUseCase, Ticket ticket, List<SpecialAssistance> specialAssistances) {
    this.firstName = purchaseUseCase.getFirstName();
    this.middleName = purchaseUseCase.getMiddleName();
    this.lastName = purchaseUseCase.getLastName();
    this.gender = purchaseUseCase.getGender();
    this.country = purchaseUseCase.getCountry();
    this.dateOfBirth = LocalDate.parse(purchaseUseCase.getDob());
    this.ticket = ticket;
    this.specialAssistances = specialAssistances;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  public List<SpecialAssistance> getSpecialAssistances() {
    return specialAssistances;
  }

  public void setSpecialAssistances(
      List<SpecialAssistance> specialAssistances) {
    this.specialAssistances = specialAssistances;
  }

  public static PassengerInfo from(PassengerInfoPurchaseUseCaseRequest purchaseUseCase, Ticket ticket, List<SpecialAssistance> specialAssistances) {
    return new PassengerInfo(purchaseUseCase, ticket, specialAssistances);
  }
}
