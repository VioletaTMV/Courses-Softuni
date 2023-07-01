package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;

import java.text.ParseException;
import java.util.Set;

public interface BookService {

    Set<Book> findAllBooksAfterYear(int year) throws ParseException;

    Set<Book> findAllBooksBeforeYear(int year) throws ParseException;

    void registerBook(Book book);

    Set<Book> findAllBooksByAuthorOrderedByReleaseDateAndTitle(Author author);

}
