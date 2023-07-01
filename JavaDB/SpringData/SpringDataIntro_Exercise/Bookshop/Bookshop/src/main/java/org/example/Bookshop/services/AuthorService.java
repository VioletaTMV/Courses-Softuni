package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;

public interface AuthorService {

    void registerAuthor(Author author);

    Author getRandomAuthor();

    Set<Author> getAuthorsHavingBookReleasePriorYear(int year) throws ParseException;

    Map<Author, Integer> getAuthorsAndTheirBookCount();

    Author findAuthorByFirstAndLastname(String firstName, String lastName);
}
