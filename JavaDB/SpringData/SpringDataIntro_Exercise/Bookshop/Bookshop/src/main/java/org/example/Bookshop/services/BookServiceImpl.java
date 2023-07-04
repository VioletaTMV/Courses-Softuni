package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.models.enumerations.AgeRestriction;
import org.example.Bookshop.models.enumerations.EditionType;
import org.example.Bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Set<Book> findAllBooksAfterYear(int year) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date beginningOfYear = simpleDateFormat.parse(String.valueOf(year));

        Optional<Set<Book>> booksAfterYear = bookRepository.findByReleaseDateAfter(beginningOfYear);

        if (booksAfterYear.isEmpty()) {
            return null;
        }

        Set<Book> books = booksAfterYear.get();

        return books;

    }

    @Override
    public Set<Book> findAllBooksBeforeYear(int year) throws ParseException {

        LocalDate localDate = LocalDate.of(year, 1, 1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Optional<Set<Book>> booksBeforeYear = bookRepository.findByReleaseDateBefore(date);

        if (booksBeforeYear.isEmpty()) {
            return null;
        }

        Set<Book> books = booksBeforeYear.get();

        return books;
    }


    @Override
    public void registerBook(Book book) {

        Optional<Book> foundBook = bookRepository.findBookByTitleAndAgeRestrictionAndCopiesAndEditionTypeAndPriceAndReleaseDate(
                book.getTitle(),
                book.getAgeRestriction(),
                book.getCopies(),
                book.getEditionType(),
                book.getPrice(),
                book.getReleaseDate()
        );

        if (foundBook.isEmpty()) {
            bookRepository.save(book);
        } else {
            System.out.println("Book " + book.getTitle() + " already exists.");
        }

    }

    @Override
    public Set<Book> findAllBooksByAuthorOrderedByReleaseDateAndTitle(Author author) {

        Optional<Set<Book>> booksByAuthor = bookRepository.findByGivenAuthorOrderedByReleaseDateAndTitle(author);

        if (booksByAuthor.isEmpty()) {
            System.out.println("No books by this author exists.");
            return null;
        }

        return booksByAuthor.get();


    }
    //Below are exercices from Spring Data Advanced Querying

    @Override
    public Set<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction) {

        Optional<Set<Book>> booksByAgeRestriction = bookRepository.findTitleByAgeRestriction(ageRestriction);

        if (booksByAgeRestriction.isEmpty()) {
            System.out.println("No books with this Age Restriction exists.");
            return null;
        }

        return booksByAgeRestriction.get();
    }

    @Override
    public void printGoldenBooksWithLessThan5000Copies(EditionType editionType, int copies) {

        Set<Book> booksGoldLessThan5000Copies = bookRepository.findByEditionTypeAndCopiesLessThan(editionType, copies);

        if (booksGoldLessThan5000Copies.isEmpty()) {
            System.out.println("No books of type Gold with less than 5000 copies exists.");
            return;
        }

        booksGoldLessThan5000Copies.forEach(b -> System.out.println(b.getTitle()));

    }

    @Override
    public void printBooksWithPriceLowerThanAndHigherThan(int lower, int higher) {

        Set<Book> books = bookRepository.findByPriceLessThanOrPriceGreaterThan(lower, higher);

        if (books.size() == 0) {
            System.out.println("No books with price lower than or higher than.");
            return;
        }

        books.forEach(b -> System.out.println(b.getTitle() + " - $" + b.getPrice()));


    }

    @Override
    public void printBooksNotReleasedIn(int year) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy");
        Date yearOfDate = df.parse(String.valueOf(year));

        Set<Book> booksNotReleasedIn = bookRepository.findByReleaseDateNot(yearOfDate);

        if (booksNotReleasedIn.isEmpty()) {
            System.out.println("All books are released in " + year);
            return;
        }

        booksNotReleasedIn.forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printBooksReleasedBefore(String dateString) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(dateString);

        Optional<Set<Book>> booksBefore = bookRepository.findByReleaseDateBefore(date);

        Set<Book> bb = booksBefore.get();
        if (bb.isEmpty()) {
            System.out.println("No book released before " + dateString + " exists.");
            return;
        }
        bb.forEach(b -> System.out.printf("%s %s %s%n", b.getTitle(), b.getEditionType(), b.getPrice()));

    }

    @Override
    public void printBooksContaining(String str) {

        String strToUppercase = str.toUpperCase();

        Set<Book> books = bookRepository.findByTitleContainingIgnoreCase(strToUppercase);

        if (books.isEmpty()) {
            System.out.println("No books condaining " + str);
            return;
        }

        books.forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printBooksByAuthorStartingWith(String input) {

        String inputUppercase = input.toUpperCase();

        Set<Book> books = bookRepository.findByAuthorLastNameStartingWithIgnoreCase(inputUppercase);

        if (books.isEmpty()) {
            System.out.println("No books by author whose last name starts with " + input);
            return;
        }

        books.forEach(b -> System.out.printf("%s (%s %s)%n",
                b.getTitle(),
                b.getAuthor().getFirstName(),
                b.getAuthor().getLastName()));

    }

    @Override
    public void printNumberOfBooksWithLongerTitleThan(int length) {

        int bookCount = bookRepository.countByLengthTitleGreaterThan(length);

        System.out.println(bookCount);
    }

    @Override
    public void printReducedInfoAboutBookByTitle(String title) {

        List<List<Object>> reducedBookInfo = bookRepository.getReducedBookInfoByTitle(title);

        if (reducedBookInfo.isEmpty()) {
            System.out.println("No book of this title exists.");
            return;
        }

        reducedBookInfo.forEach(listOfObjects -> {
            String ttl = listOfObjects.get(0).toString();
            EditionType et = EditionType.valueOf(listOfObjects.get(1).toString());
            AgeRestriction ar = AgeRestriction.valueOf(listOfObjects.get(2).toString());
            BigDecimal price = new BigDecimal(listOfObjects.get(3).toString());

            System.out.printf("%s %s %s %s%n", ttl, et, ar, price);
        });


    }

    @Override
    public void printBookCopiesAdded(String dateString, int amount) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd MMM yyy");
        Date date = df.parse(dateString);

        int impactedBooks = bookRepository.increaseBookCopiesOfBooksReleasedAfter(date, amount);

        System.out.println(impactedBooks * amount);

    }

    @Override
    public void deleteBooksWithCopiesLessThan(int num) {

       int impactedNumberOfBooks = bookRepository.deleteByCopiesLessThan(num);

        System.out.println(impactedNumberOfBooks);

    }


}
