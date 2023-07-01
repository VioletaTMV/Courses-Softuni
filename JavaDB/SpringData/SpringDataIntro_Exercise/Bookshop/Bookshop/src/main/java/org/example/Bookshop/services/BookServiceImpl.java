package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @Override
    public Set<Book> findAllBooksAfterYear(int year) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date beginningOfYear = simpleDateFormat.parse(String.valueOf(year));

        Optional<Set<Book>> booksAfterYear = bookRepository.findByReleaseDateAfter(beginningOfYear);

        if (booksAfterYear.isEmpty()){
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

        if (booksBeforeYear.isEmpty()){
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
            System.out.println("Book " + book.getTitle() + " already exists.") ;
        }

    }

    @Override
    public Set<Book> findAllBooksByAuthorOrderedByReleaseDateAndTitle(Author author) {

        Optional<Set<Book>> booksByAuthor = bookRepository.findByGivenAuthorOrderedByReleaseDateAndTitle(author);

        if (booksByAuthor.isEmpty()){
            System.out.println("No books by this author exists.");
            return null;
        }

        return booksByAuthor.get();


    }
}
