package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.repositories.AuthorRepository;
import org.example.Bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }


    @Override
    public void registerAuthor(Author author) {

        Author authorFound = authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        if (authorFound != null) {
            System.out.println("Author " + author.getFirstName() + " " + author.getLastName() + " already exists.");
        } else {
            authorRepository.save(author);
        }
    }

    @Override
    public Author getRandomAuthor() {

        List<Author> allAuthors = authorRepository.findAll();
        Random random = new Random();

        Author randomAuthor = allAuthors.get(random.nextInt(allAuthors.size()));

        return randomAuthor;
    }

    @Override
    public Set<Author> getAuthorsHavingBookReleasePriorYear(int year) throws ParseException {

        Set<Book> booksByReleaseDateBefore = bookService.findAllBooksBeforeYear(year);

        Set<Author> authorsWithBookReleasedBefore = booksByReleaseDateBefore
                .stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());

        return authorsWithBookReleasedBefore;

    }

    @Override
    public Map<Author, Integer> getAuthorsAndTheirBookCount() {

        Map<Author, Integer> authorsCountBookMap = new LinkedHashMap<>();

        Set<Author> authorsSortedByBookCountDesc = authorRepository.findAllAuthorsSortedByTheirBooksCount();

        authorsSortedByBookCountDesc.forEach(a -> authorsCountBookMap.put(a, a.getBooks().size())
        );

        return authorsCountBookMap;
    }

    @Override
    public Author findAuthorByFirstAndLastname(String firstName, String lastName) {

        Author authorByFirstNameAndLastName = authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);

        if (authorByFirstNameAndLastName == null){
            throw new IllegalArgumentException("No author with this name exists.");
        }

        return authorByFirstNameAndLastName;
    }


}
