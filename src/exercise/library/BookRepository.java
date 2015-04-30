package exercise.library;

public interface BookRepository {
    /**
     * Returns an existing book.
     * @param isbn Identifies the book to be returned.
     * @return the book
     * @throws BookNotFoundException If the book does not exist
     */
    Book retrieveBook(final String isbn) throws BookNotFoundException;
}
