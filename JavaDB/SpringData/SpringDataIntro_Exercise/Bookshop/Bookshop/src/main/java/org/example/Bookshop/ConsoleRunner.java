package org.example.Bookshop;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.services.AuthorService;
import org.example.Bookshop.services.BookService;
import org.example.Bookshop.services.CategoryService;
import org.example.Bookshop.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;
    private SeedService seedService;

    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService, SeedService seedService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.seedService = seedService;
    }


    @Override
    public void run(String... args) throws Exception {

        // 1.
        seedService.seedDatabase();

         // 2.
        Set<Book> booksAfter = bookService.findAllBooksAfterYear(2000);
        booksAfter.forEach(b -> System.out.println(b.getTitle()));
        System.out.println();

        // 3.
        Set<Author> authorsWithBookReleasedBeforeYear = authorService.getAuthorsHavingBookReleasePriorYear(1990);
        authorsWithBookReleasedBeforeYear.forEach(a ->
                System.out.println(a.getFirstName() + " " + a.getLastName()));
        System.out.println();

        // 4.
        Map<Author, Integer> authorsAndTheirBooksCount = authorService.getAuthorsAndTheirBookCount();
        authorsAndTheirBooksCount.forEach((author, bookNumber) -> System.out.printf("%s %s %d%n", author.getFirstName(), author.getLastName(), bookNumber));

        // 5.
        Author foundAuthor = authorService.findAuthorByFirstAndLastname("George", "Powell");
        Set<Book> booksByAuthorOrderedReleaseDateAndTitle = bookService.findAllBooksByAuthorOrderedByReleaseDateAndTitle(foundAuthor);
        booksByAuthorOrderedReleaseDateAndTitle.forEach(b -> System.out.printf("%s, %s, %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));



    }


}
