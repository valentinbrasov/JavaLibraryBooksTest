package exercise.library;

public class BookNotFoundException extends Exception
{
    private static final long serialVersionUID = 4237524941638091108L;

    public BookNotFoundException(final String message) {
        super(message);
    }
}
