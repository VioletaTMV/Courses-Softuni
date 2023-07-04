package org.example.Bookshop.services;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
    @Transactional
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

        if (authorByFirstNameAndLastName == null) {
            throw new IllegalArgumentException("No author with this name exists.");
        }

        return authorByFirstNameAndLastName;
    }

    @Override
    public void printAuthorsWhoseFirstNameEndsWith(String input) {

        Set<Author> authors = authorRepository.findByFirstNameEndingWith(input);

        if (authors.isEmpty()) {
            System.out.println("No authors with first name ending by " + input);
            return;
        }

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

    }

    @Override
    public void printAuthorsWithTheirBookCopiesNumber() {

        List<List<Object>> authorsWithCopiesCount = authorRepository.getAuthorsWithCopiesCount();

        authorsWithCopiesCount
                .forEach(listObj -> {
                    String authorFirstName = String.valueOf(listObj.get(0));
                    String authorLastName = String.valueOf(listObj.get(1));
                    Integer numberOfBookCopies = Integer.parseInt(String.valueOf(listObj.get(2)));
                    System.out.printf("%s %s - %d%n",
                            authorFirstName,
                            authorLastName,
                            numberOfBookCopies);
                });
    }

    @Override
    @Transactional
    public void printNumberOfBooksWrittenBy(String fullName) {

        String[] fullNameArr = fullName.split(" ");

        if (fullNameArr.length != 2){
            System.out.println("No author " + fullName + " exist.");
            return;
        }
        String firstName = fullNameArr[0];
        String lastName = fullNameArr[1];

       Integer numberOfBooksForAuthor = authorRepository.getTotalCountOfBooksForAuthor(firstName, lastName);

       if (numberOfBooksForAuthor == null){
           System.out.println("We have no books written by author " + fullName);
           return;
       }

        System.out.printf("%s %s has written %d books%n", firstName, lastName, numberOfBooksForAuthor);

    }


}
