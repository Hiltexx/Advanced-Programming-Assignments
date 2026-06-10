package com.onboarding;
import java.util.regex.Pattern;
public class RegistrationService {
    public static final int MINIMUM_AGE = 18;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    private final boolean serviceEnabled;
    public RegistrationService(boolean serviceEnabled) {
        this.serviceEnabled = serviceEnabled;
    }
    public RegistrationService() {
        this(true);
    }
    public boolean registerUser(String email, int age) throws InvalidEmailException {
        assert serviceEnabled : "RegistrationService is disabled.";
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException(email, "value must not be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new InvalidEmailException(email, "does not match required format");
        }
        if (age < MINIMUM_AGE) {
            throw new UnderageException(age, MINIMUM_AGE);
        }
        return true;
    }
    public boolean isServiceEnabled() {
        return serviceEnabled;
    }
}
