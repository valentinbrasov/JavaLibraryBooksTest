package exercise.library;

import org.junit.Assert;
import org.junit.Test;


public class BookRepositoryImplTest {
    private BookRepository bookRepository = new BookRepositoryImpl();
    
    // ======================================================
    // retrieveBook() TESTS
    // ======================================================

    @Test
    public void findsExistingBookByIsbn() throws BookNotFoundException {
        final String bookIsbn = "ISBN-001";
        // Run the code to test
        final Book book = bookRepository.retrieveBook(bookIsbn);
        
        // Checks
        Assert.assertEquals(bookIsbn, book.getIsbn());
        Assert.assertEquals("Harry Potter and the Deathly Hallows", book.getTitle());
        Assert.assertEquals("Sorcery and Magic.", book.getDescription());
    }
    
    @Test(expected = BookNotFoundException.class)
    public void detectsNotFoundBook() throws BookNotFoundException {
        // Run the code to test + checks
        bookRepository.retrieveBook("ISBN-777");
    }
}
