package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tracker.utils.Validator;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;


class TrackerTest {


    private final Predicate<String> isValidName = Validator::isValidName;
    private final Predicate<String> isValidMail = Validator::isValidEmail;

    @Test
    @DisplayName("Should return true when first name contains hyphens")
    void testFirstnameWithHyphens() {
        String name = "Jean-Claude";
        assertTrue(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return true when first name contains apostrophe")
    void testFirstnameWithApostrophe() {
        String name = "O'Neill";
        assertTrue(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return true when last name contains space")
    void testLastnameContainsSpace() {
        String name = "van Helsing";
        assertTrue(Validator.isValidLastname(name));
    }

    @Test
    @DisplayName("Should return false when first name and last name contain invalid characters")
    void testFirstnameWithInvalidCharacter() {
        String name = "Stanisław";
        assertFalse(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return false when first name is shorted")
    void testFirstnameShorted() {
        String name = "J.";
        assertFalse(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return false when last name is shorted")
    void testLastnameShorted() {
        String name = "D.";
        assertFalse(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return false when first name is given with only one character")
    void testFirstnameGivenWithOneCharacter() {
        String name = "D";
        assertFalse(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return false when first name and last name contain invalid characters")
    void testLastnameWithInvalidCharacter() {
        String name = "Oğuz";
        assertFalse(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return false when first name contain chinese characters")
    void testLastnameWithChineseCharacter() {
        String name = "陳";
        assertFalse(isValidName.test(name));
    }

    @Test
    @DisplayName("Should return true when email is valid")
    void testEmailWithFirstnameShortedValid() {
        String email = "jdoe@mail.net";
        assertTrue(isValidMail.test(email));
    }

    @Test
    @DisplayName("Should return true when email is valid")
    void testEmailWithFullNameValid() {
        String email = "jane.doe@yahoo.com";
        assertTrue(isValidMail.test(email));
    }

    @Test
    @DisplayName("Should return false when email is incorrect")
    void testEmailNotCorrectValid() {
        String email = "email";
        assertFalse(isValidMail.test(email));
    }
}