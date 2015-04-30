package exercise.library;

public interface BookService
{
    /**
     * Returns an existing book.
     * @param isbn Identifies the book to be returned.
     * @return the book
     * @throws BookNotFoundException If the book does not exist
     * @throws IllegalArgumentException If the book's ISBN does not start with 'ISBN-'
     */
    public Book retrieveBook(final String isbn) throws BookNotFoundException;
    
    /**
     * Returns the summary of an existing book, summary created by concatenating the ISBN, the title, and the first ten words of the review followed by '...'
     * @param isbn Identifies the book for which the summary is returned.
     * @return summary of the book
     * @throws BookNotFoundException If the book does not exist
     * @throws IllegalArgumentException If the book's ISBN does not start with 'ISBN-'
     */
    public String getBookSummary(final String isbn) throws BookNotFoundException;
}
