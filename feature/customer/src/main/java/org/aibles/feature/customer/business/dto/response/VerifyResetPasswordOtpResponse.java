package org.aibles.feature.customer.business.dto.response;

public class VerifyResetPasswordOtpResponse {

    private String email;
    private String resetPasswordKey;


    public VerifyResetPasswordOtpResponse(String email, String resetPasswordKey) {
        this.email = email;
        this.resetPasswordKey = resetPasswordKey;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    @Override
    public String toString() {
        return "VerifyResetPasswordOtpResponse{" +
                "email='" + email + '\'' +
                ", resetPasswordKey='" + resetPasswordKey + '\'' +
                '}';
    }
}
