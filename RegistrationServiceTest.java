package com.onboarding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class RegistrationServiceTest {
    private RegistrationService service;
    @BeforeEach
    void setUp() {
        service = new RegistrationService(true);
    }
    @Nested
    class SuccessfulRegistrationTests {
        @Test
        void testValidEmailAtMinimumAge() throws InvalidEmailException {
            assertTrue(service.registerUser("alice@@example.com", 18));
        }
        @Test
        void testValidEmailAboveMinimumAge() throws InvalidEmailException {
            assertTrue(service.registerUser("bob@company.org", 35));
        }
        @Test
        void testEmailWithPlusAlias() throws InvalidEmailException {
            assertTrue(service.registerUser("user+tag@mail.co", 25));
        }
        @Test
        void testEmailWithSubdomain() throws InvalidEmailException {
            assertTrue(service.registerUser("dev@staging.platform.io", 22));
        }
        @Test
        void testEmailWithNumericLocalPart() throws InvalidEmailException {
            assertTrue(service.registerUser("123@numbers.net", 30));
        }
    }
    @Nested
    class InvalidEmailExceptionTests {
        @Test
        void testNullEmailThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser(null, 25));
        }
        @Test
        void testEmptyEmailThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("", 25));
        }
        @Test
        void testBlankEmailThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("   ", 25));
        }
        @Test
        void testEmailMissingAtThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("invalidemail.com", 25));
        }
        @Test
        void testEmailMissingDomainThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user@", 25));
        }
        @Test
        void testEmailMissingTldThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user@domain", 25));
        }
        @Test
        void testEmailWithSpacesThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user @domain.com", 25));
        }
        @Test
        void testEmailWithDoubleAtThrows() {
            assertThrows(InvalidEmailException.class,
                () -> service.registerUser("user@@domain.com", 25));
        }
    }
    @Nested
    class UnderageExceptionTests {
        @Test
        void testAge17Throws() {
            UnderageException ex = assertThrows(
                UnderageException.class,
                () -> service.registerUser("young@example.com", 17)
            );
            assertEquals(17, ex.getProvidedAge());
        }
        @Test
        void testAge0Throws() {
            assertThrows(UnderageException.class,
                () -> service.registerUser("newborn@example.com", 0));
        }
        @Test
        void testNegativeAgeThrows() {
            assertThrows(UnderageException.class,
                () -> service.registerUser("neg@example.com", -5));
        }
        @Test
        void testUnderageIsRuntimeException() {
            UnderageException ex = new UnderageException(15, 18);
            assertTrue(ex instanceof RuntimeException);
        }
        @Test
        void testMessageContainsBothAges() {
            UnderageException ex = assertThrows(
                UnderageException.class,
                () -> service.registerUser("teen@example.com", 16)
            );
            assertTrue(ex.getMessage().contains("16"));
            assertTrue(ex.getMessage().contains("18"));
        }
    }
    @Nested
    class ExceptionHierarchyTests {
        @Test
        void testInvalidEmailIsChecked() {
            InvalidEmailException ex = new InvalidEmailException("bad", "test");
            assertTrue(ex instanceof Exception);
        }
        @Test
        void testUnderageIsUnchecked() {
            UnderageException ex = new UnderageException(10, 18);
            assertTrue(ex instanceof RuntimeException);
        }
    }
}
