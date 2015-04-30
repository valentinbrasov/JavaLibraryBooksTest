package exercise.library;

public class Book {
    public static final String ISBN_PREFIX = "ISBN-";

    private final String isbn;
    private final String title;
    private final String description;

    public Book(final String isbn, final String title, final String description) {
        Book.assertCorrectIsbnFormat(isbn);
        this.isbn = isbn;

        if (title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        this.title = title;

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public static void assertCorrectIsbnFormat(final String isbn) {
        if (isbn == null || !isbn.startsWith(Book.ISBN_PREFIX)) {
            throw new IllegalArgumentException("book isbn must begin with 'ISBN'");
        }
        /*
         * TODO: for the purposes of this test I thought that it may be
         * important to stick to the given acceptance criteria. However in
         * practice I would ensure more things in my implementation, even if
         * they are not specified in the acceptance criteria, like the fact that
         * the ISBN length should be bigger than the length of the prefix,
         * perhaps even using a regular expression for checking the pattern of
         * the ISBN
         */
    }
}
