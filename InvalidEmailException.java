package com.onboarding;
public class InvalidEmailException extends Exception {
    private final String invalidEmail;
    public InvalidEmailException(String email, String reason) {
        super(buildMessage(email, reason));
        this.invalidEmail = email;
    }
    public String getInvalidEmail() {
        return invalidEmail;
    }
    private static String buildMessage(String email, String reason) {
        String display = (email == null) ? "<null>" : email;
        return "Invalid email address " + display + ": " + reason;
    }
}
