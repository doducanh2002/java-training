package org.aibles.feature.customer.business.dto.request;

public class ResetPasswordUseCaseRequest {

    private String email;

    private String resetPasswordKey;

    private String newPassword;

    private String confirmPassword;

    public ResetPasswordUseCaseRequest() {
    }

    public ResetPasswordUseCaseRequest(String email, String resetPasswordKey, String newPassword, String confirmPassword) {
        this.email = email;
        this.resetPasswordKey = resetPasswordKey;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public static ResetPasswordUseCaseRequestBuilder builder() {
        return new ResetPasswordUseCaseRequestBuilder();
    }

    public static class ResetPasswordUseCaseRequestBuilder {
        private String email;
        private String resetPasswordKey;
        private String newPassword;
        private String confirmPassword;

        public ResetPasswordUseCaseRequestBuilder() {}

        public ResetPasswordUseCaseRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ResetPasswordUseCaseRequestBuilder resetPasswordKey(String resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
            return this;
        }

        public ResetPasswordUseCaseRequestBuilder newPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public ResetPasswordUseCaseRequestBuilder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public ResetPasswordUseCaseRequest build() {
            return new ResetPasswordUseCaseRequest(email, resetPasswordKey, newPassword, confirmPassword);
        }
    }
}
