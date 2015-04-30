package exercise.library;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {
    private static final String ISBN_DEATHLY_HALLOWS = Book.ISBN_PREFIX + "001";
    private static final String ISBN_PLAYER_OF_GAMES = Book.ISBN_PREFIX + "002";
    private static final String ISBN_GENIUS = Book.ISBN_PREFIX + "003";

    /**
     * The keys in the map are ISBNs of books
     */
    private static final Map<String, Book> books;

    static {
        books = new HashMap<String, Book>();
        books.put(ISBN_DEATHLY_HALLOWS, new Book(
                ISBN_DEATHLY_HALLOWS,
                "Harry Potter and the Deathly Hallows",
                "Sorcery and Magic."));
        books.put(ISBN_PLAYER_OF_GAMES, new Book(
                ISBN_PLAYER_OF_GAMES,
                "The Player of Games",
                "Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy."));
        books.put(ISBN_GENIUS, new Book(
                ISBN_GENIUS,
                "Genius: Richard Feynman and Modern Physics",
                "A brilliant interweaving of Richard Feynman's colourful life and a detailed and accessible account of his theories and experiments."));
    }

    public Book retrieveBook(final String isbn) throws BookNotFoundException {
        final Book book = books.get(isbn);
        
        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " does not exist");
        }
        
        return book;
    }
}
