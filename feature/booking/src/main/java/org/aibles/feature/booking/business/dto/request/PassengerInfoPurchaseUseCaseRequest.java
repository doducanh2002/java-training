package org.aibles.feature.booking.business.dto.request;

public class PassengerInfoPurchaseUseCaseRequest {

  private Integer num;
  private String firstName;
  private String middleName;
  private String lastName;
  private String gender;
  private String country;
  private String dob;

  public PassengerInfoPurchaseUseCaseRequest() {
  }

  public PassengerInfoPurchaseUseCaseRequest(Integer num, String firstName, String middleName,
      String lastName, String gender, String country, String dob) {
    this.num = num;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.country = country;
    this.dob = dob;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
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

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public static PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder builder() {
    return new PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder();
  }

  public static class PassengerInfoPurchaseUseCaseBuilder {

    private Integer num;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String country;
    private String dob;

    public PassengerInfoPurchaseUseCaseBuilder() {
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder num(
        Integer num) {
      this.num = num;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder firstName(
        String firstName) {
      this.firstName = firstName;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder middleName(
        String middleName) {
      this.middleName = middleName;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder lastName(
        String lastName) {
      this.lastName = lastName;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder gender(
        String gender) {
      this.gender = gender;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder country(
        String country) {
      this.country = country;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest.PassengerInfoPurchaseUseCaseBuilder dob(
        String dob) {
      this.dob = dob;
      return this;
    }

    public PassengerInfoPurchaseUseCaseRequest build() {
      return new PassengerInfoPurchaseUseCaseRequest(num, firstName, middleName, lastName, gender,
          country, dob);
    }
  }
}
