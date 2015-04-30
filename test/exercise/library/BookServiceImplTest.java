package exercise.library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookServiceImplTest {
    private final static String EXISTING_VALID_BOOK_ISBN = Book.ISBN_PREFIX + "001";
    private final static String EXISTING_VALID_BOOK_TITLE = "Harry Potter and the Deathly Hallows";
    private final static String EXISTING_VALID_BOOK_DESCRIPTION = "Sorcery and Magic.";

    private final static String INVALID_BOOK_ISBN = "INVALID-TEXT";
    
    private final static String NOT_FOUND_VALID_BOOK_ISBN = "ISBN-777";

    private BookServiceImpl bookServiceImpl;

    @Before
    public void setUp() {
        bookServiceImpl = new BookServiceImpl(new BookRepositoryMock());
    }

    // ======================================================
    // retrieveBook() TESTS
    // ======================================================

    @Test
    public void canRetrieveExistingBook() throws BookNotFoundException {
        // Run the code to test + checks (more tests for summary are inside BookTest.java)
        Assert.assertEquals(BookServiceImplTest.EXISTING_VALID_BOOK_ISBN, bookServiceImpl.retrieveBook(BookServiceImplTest.EXISTING_VALID_BOOK_ISBN).getIsbn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void detectsIsbnNotStartingWithIsbnPrefixWhileRetrievingBook() throws BookNotFoundException {
        // Run the code to test (the exception message is tested better inside BookTest.java)
        bookServiceImpl.retrieveBook("INVALID-TEXT");
    }

    @Test(expected = BookNotFoundException.class)
    public void detectsNotFoundBookWhileRetrievingBook() throws BookNotFoundException {
        // Run the code to test
        bookServiceImpl.retrieveBook("ISBN-777");
    }

    // ======================================================
    // getBookSummary() TESTS (more tests for summary are inside BookTest.java)
    // ======================================================

    @Test
    public void canGenerateWholeSummaryForExistingBook() throws BookNotFoundException {
        // Run the code to test + checks
        Assert.assertEquals("[" + BookServiceImplTest.EXISTING_VALID_BOOK_ISBN + "] " 
                + BookServiceImplTest.EXISTING_VALID_BOOK_TITLE 
                + " - " + BookServiceImplTest.EXISTING_VALID_BOOK_DESCRIPTION, 
            bookServiceImpl.getBookSummary(BookServiceImplTest.EXISTING_VALID_BOOK_ISBN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void detectsIsbnNotStartingWithIsbnPrefixWhileGettingBookSummary() throws BookNotFoundException {
        // Run the code to test (the exception message is tested better inside BookTest.java) + checks
        bookServiceImpl.getBookSummary(BookServiceImplTest.INVALID_BOOK_ISBN);
    }

    @Test(expected = BookNotFoundException.class)
    public void detectsNotFoundBookWhileGettingBookSummary() throws BookNotFoundException {
        // Run the code to test + checks
        bookServiceImpl.getBookSummary(BookServiceImplTest.NOT_FOUND_VALID_BOOK_ISBN);
    }

    // ======================================================
    // buildBookDescriptionForSummary() TESTS
    // ======================================================

    @Test
    public void shortDescriptionDoesNotGenerateEllipsesInSummary() {
        // Run the code to test + checks
        Assert.assertEquals(" - " + BookServiceImplTest.EXISTING_VALID_BOOK_DESCRIPTION, 
                bookServiceImpl.buildBookDescriptionForSummary(BookServiceImplTest.EXISTING_VALID_BOOK_DESCRIPTION));
    }

    @Test
    public void longDescriptionGeneratesEllipsesInSummary() {
        final String description = "Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy.";

        // Run the code to test + checks
        Assert.assertEquals(" - " + "Jernau Morat Gurgeh. The Player of Games. Master of every...",
                bookServiceImpl.buildBookDescriptionForSummary(description));
    }

    @Test
    public void veryLongDescriptionGeneratesEllipsesInSummary() {
        final String description = "A brilliant interweaving of Richard Feynman's colourful life and a detailed and accessible account of his theories and experiments.";

        // Run the code to test + checks
        Assert.assertEquals(" - " + "A brilliant interweaving of Richard Feynman's colourful life and a...",
                bookServiceImpl.buildBookDescriptionForSummary(description));
    }

    @Test
    public void nullDescriptionDoesNotAddToSummary() {
        final String description = null;

        // Run the code to test + checks
        Assert.assertEquals("", bookServiceImpl.buildBookDescriptionForSummary(description));
    }

    @Test
    public void blankDescriptionDoesNotAddToSummary() {
        final String description = "       ";

        // Run the code to test + checks
        Assert.assertEquals("", bookServiceImpl.buildBookDescriptionForSummary(description));
    }

    
    /*
    // ======================================================
    // buildInnerWhiteSpacesPreservingBookDescriptionForSummary() TESTS
    // ======================================================

    @Test
    public void canGenerateSummaryDescriptionWithoutEllipsesWhenDescriptionHasVariousWhitespaces() {
        final String description = "    Very    good      book       to    read    on   the               train.  ";

        // Run the code to test + checks
        Assert.assertEquals(" - " + "Very    good      book       to    read    on   the               train.",
                bookServiceImpl.buildInnerWhiteSpacesPreservingBookDescriptionForSummary(description));
    }

    @Test
    public void canGenerateSummaryDescriptionWithEllipsesWhenDescriptionHasWithVariousWhitespaces() {
        final String description = "    Very    good      book       to    read    on   the               train    when    comming                from     work.  ";

        // Run the code to test + checks
        Assert.assertEquals(" - " + "Very    good      book       to    read    on   the               train    when    comming...",
                bookServiceImpl.buildInnerWhiteSpacesPreservingBookDescriptionForSummary(description));
    }
     */

    private static class BookRepositoryMock implements BookRepository {
        @Override
        public Book retrieveBook(String isbn) throws BookNotFoundException {
            if (BookServiceImplTest.EXISTING_VALID_BOOK_ISBN.equals(isbn)) {
                return new Book(isbn, BookServiceImplTest.EXISTING_VALID_BOOK_TITLE, BookServiceImplTest.EXISTING_VALID_BOOK_DESCRIPTION);
            }

            if (BookServiceImplTest.INVALID_BOOK_ISBN.equals(isbn)) {
                throw new IllegalArgumentException("does not matter in this test");
            }

            throw new BookNotFoundException("oes not matter in this test");
        }
    }
}
