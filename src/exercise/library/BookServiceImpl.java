package exercise.library;

import java.util.Arrays;
import java.util.regex.Pattern;

public class BookServiceImpl implements BookService {
    private static final Pattern DESCRIPTION_SPLIT_PATTERN = Pattern.compile("\\s+");
    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book retrieveBook(final String isbn) throws BookNotFoundException {
        Book.assertCorrectIsbnFormat(isbn);
        return bookRepository.retrieveBook(isbn);
    }

    @Override
    public String getBookSummary(final String isbn) throws BookNotFoundException {
        final Book book = retrieveBook(isbn);
        
        return buildBookSummary(book);
    }
    
    String buildBookSummary(final Book book) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(book.getIsbn());
        stringBuilder.append("] ");
        stringBuilder.append(book.getTitle());
        /*
         * Use buildInnerWhiteSpacesPreservingBookDescriptionForSummary() instead
         * of buildBookDescriptionForSummary() for real world results
         */
        stringBuilder.append(buildBookDescriptionForSummary(book.getDescription()));

        return stringBuilder.toString();
    }

    String buildBookDescriptionForSummary(final String bookDescription) {
        final StringBuilder stringBuilder = new StringBuilder();

        // Make sure that we really have a not blank description
        String bookDescriptionCleansed = null;
        if (bookDescription != null) {
            bookDescriptionCleansed = bookDescription.trim();
            if (bookDescriptionCleansed.length() == 0) {
                bookDescriptionCleansed = null;
            }
        }

        if (bookDescriptionCleansed != null) {
            /*
             * This kind of splitting loses the info of how many and what
             * whitespace characters are between each successive 2 words.
             * However, in order to satisfy the acceptance criteria of this
             * exercise, this seem enough. In a real code situation however
             * preserving the inner whitespaces is probably important, so that
             * is why I created below a
             * buildInnerWhiteSpacesPreservingDescriptionForSummary() function
             * that does that.
             */
            final String[] descriptionWords = BookServiceImpl.DESCRIPTION_SPLIT_PATTERN.split(bookDescriptionCleansed);
            final String summaryDescription = String.join(" ", Arrays.copyOfRange(descriptionWords, 0, Math.min(10, descriptionWords.length)));
            stringBuilder.append(" - ");
            stringBuilder.append(summaryDescription);

            // If there are parts still not used, then we need the ellipses
            if (descriptionWords.length > 10) {
                stringBuilder.append("...");
            }
        }

        return stringBuilder.toString();
    }

    /*
    private static final Pattern DESCRIPTION_SPLIT_RETAINING_DELIMS_PATTERN = Pattern.compile("(?=\\s+)");

    String buildInnerWhiteSpacesPreservingBookDescriptionForSummary(final String bookDescription) {
        final StringBuilder stringBuilder = new StringBuilder();

        // Make sure that we really have a not blank description
        String bookDescriptionCleansed = null;
        if (bookDescription != null) {
            bookDescriptionCleansed = bookDescription.trim();
            if (bookDescriptionCleansed.length() == 0) {
                bookDescriptionCleansed = null;
            }
        }

        if (bookDescriptionCleansed != null) {
            stringBuilder.append(" - ");

            // This lookahead expression unfortunately does generate blank items
            // for subsequent spaces,
            // reason for which I needed to really count the words
            int descriptionWordsCount = 0;

            final String[] descriptionToUseParts = DESCRIPTION_SPLIT_RETAINING_DELIMS_PATTERN.split(bookDescriptionCleansed);
            int partIndex = 0;
            for (; partIndex < descriptionToUseParts.length && descriptionWordsCount < 10; partIndex++) {
                final String part = descriptionToUseParts[partIndex];
                stringBuilder.append(part);
                // If this part is a word and not just whitespace, then
                // increment the words count
                if (part.trim().length() > 0) {
                    descriptionWordsCount++;
                }
            }
            
            // If there are parts still not used, then we need the ellipses
            if (partIndex < descriptionToUseParts.length) {
                stringBuilder.append("...");
            }
        }

        return stringBuilder.toString();
    }
     */
}
