package exercise.library;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {
    // ======================================================
    // assertCorrectIsbnFormat() TESTS
    // ======================================================

    @Test
    public void detectsNullIsbn() throws BookNotFoundException {
        // Run the code to test + checks
        try {
            Book.assertCorrectIsbnFormat(null);
            Assert.fail("Should have not reach here");
        } catch (IllegalArgumentException iae) {
            Assert.assertEquals("book isbn must begin with 'ISBN'", iae.getMessage());
        }
    }

    @Test
    public void detectsNotNullIsbnNotStartingWithIsbnPrefix() throws BookNotFoundException {
        // Run the code to test + checks
        try {
            Book.assertCorrectIsbnFormat("INVALID-TEXT");
            Assert.fail("Should have not reach here");
        } catch (IllegalArgumentException iae) {
            Assert.assertEquals("book isbn must begin with 'ISBN'", iae.getMessage());
        }
    }

    // ======================================================
    // book title TESTS
    // ======================================================

    @Test(expected = IllegalArgumentException.class)
    public void detectsNullTitle() throws BookNotFoundException {
        // Run the code to test + checks
        new Book("ISBN-001", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void detectsBlankTitle() throws BookNotFoundException {
        // Run the code to test + checks
        new Book("ISBN-001", "   ", null);
    }
}
