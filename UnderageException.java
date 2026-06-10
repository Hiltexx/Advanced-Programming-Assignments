package com.onboarding;
public class UnderageException extends RuntimeException {
    private final int providedAge;
    private final int minimumAge;
    public UnderageException(int providedAge, int minimumAge) {
        super("Applicant age " + providedAge + " does not meet the minimum age requirement of " + minimumAge + ". Registration denied.");
        this.providedAge = providedAge;
        this.minimumAge = minimumAge;
    }
    public int getProvidedAge() { return providedAge; }
    public int getMinimumAge() { return minimumAge; }
}
