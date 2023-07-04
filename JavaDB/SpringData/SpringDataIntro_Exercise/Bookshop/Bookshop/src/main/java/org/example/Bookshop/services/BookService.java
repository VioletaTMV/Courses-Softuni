package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.models.enumerations.AgeRestriction;
import org.example.Bookshop.models.enumerations.EditionType;

import java.text.ParseException;
import java.util.Set;

public interface BookService {

    Set<Book> findAllBooksAfterYear(int year) throws ParseException;

    Set<Book> findAllBooksBeforeYear(int year) throws ParseException;

    void registerBook(Book book);

    Set<Book> findAllBooksByAuthorOrderedByReleaseDateAndTitle(Author author);

    //Below are exercices from Spring Data Advanced Querying

    Set<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    void printGoldenBooksWithLessThan5000Copies(EditionType gold, int copies);

    void printBooksWithPriceLowerThanAndHigherThan(int lower, int higher);

    void printBooksNotReleasedIn(int year) throws ParseException;

    void printBooksReleasedBefore(String dateString) throws ParseException;

    void printBooksContaining(String str);

    void printBooksByAuthorStartingWith(String input);

    void printNumberOfBooksWithLongerTitleThan(int length);

    void printReducedInfoAboutBookByTitle(String title);

    void printBookCopiesAdded(String dateString, int amount) throws ParseException;

    void deleteBooksWithCopiesLessThan(int num);
}
