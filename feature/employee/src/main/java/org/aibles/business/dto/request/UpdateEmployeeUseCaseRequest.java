package org.aibles.business.dto.request;


import java.time.LocalDate;
import java.util.List;

public class UpdateEmployeeUseCaseRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String avatar;
    private String address;
    private List<String> roles;

    public UpdateEmployeeUseCaseRequest(String firstName, String lastName, String middleName, String email, String phone, LocalDate dateOfBirth, String gender, String avatar, String address, List<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.avatar = avatar;
        this.address = address;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static UpdateEmployeeUseCaseRequestBuilder builder() {
        return new UpdateEmployeeUseCaseRequestBuilder();
    }

    public static class UpdateEmployeeUseCaseRequestBuilder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String email;
        private String phone;
        private LocalDate dateOfBirth;
        private String gender;
        private String avatar;
        private String address;
        private List<String> roles;

        public UpdateEmployeeUseCaseRequestBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder lastName(String lastName) {
            this.lastName = firstName;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UpdateEmployeeUseCaseRequestBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UpdateEmployeeUseCaseRequest build() {
            return new UpdateEmployeeUseCaseRequest(this.firstName,this.lastName, this.middleName, this.email, this.phone, this.dateOfBirth, this.gender, this.avatar, this.address, this.roles);
        }
    }
}
