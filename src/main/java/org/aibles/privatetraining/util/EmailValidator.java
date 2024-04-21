package org.aibles.privatetraining.util;

import java.util.regex.Pattern;

public class EmailValidator {

    // Định dạng kiểm tra tính hợp lệ của địa chỉ email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    );

    // Phương thức kiểm tra tính hợp lệ của email
    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
