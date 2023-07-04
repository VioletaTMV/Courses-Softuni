package org.example.Bookshop;

import org.example.Bookshop.services.AuthorService;
import org.example.Bookshop.services.BookService;
import org.example.Bookshop.services.CategoryService;
import org.example.Bookshop.services.SeedService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

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
//        seedService.seedDatabase();

        // 2.
//        Set<Book> booksAfter = bookService.findAllBooksAfterYear(2000);
//        booksAfter.forEach(b -> System.out.println(b.getTitle()));
//        System.out.println();

        // 3.
//        Set<Author> authorsWithBookReleasedBeforeYear = authorService.getAuthorsHavingBookReleasePriorYear(1990);
//        authorsWithBookReleasedBeforeYear.forEach(a ->
//                System.out.println(a.getFirstName() + " " + a.getLastName()));
//        System.out.println();

        // 4.
//        Map<Author, Integer> authorsAndTheirBooksCount = authorService.getAuthorsAndTheirBookCount();
//        authorsAndTheirBooksCount.forEach((author, bookNumber) -> System.out.printf("%s %s %d%n", author.getFirstName(), author.getLastName(), bookNumber));

        // 5.
//        Author foundAuthor = authorService.findAuthorByFirstAndLastname("George", "Powell");
//        Set<Book> booksByAuthorOrderedReleaseDateAndTitle = bookService.findAllBooksByAuthorOrderedByReleaseDateAndTitle(foundAuthor);
//        booksByAuthorOrderedReleaseDateAndTitle.forEach(b -> System.out.printf("%s, %s, %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));

        // Spring data Advanced Querying Exercise below

        Scanner scanner = new Scanner(System.in);

        // 1.

//        System.out.print("Enter age restriction: ");
//        String input = scanner.nextLine();
//        AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
//
//        Set<Book> booksByAgeRestriction = bookService.findBooksByAgeRestriction(ageRestriction);
//        booksByAgeRestriction.forEach(b -> System.out.println(b.getTitle()));

        // 2.
//
//        bookService.printGoldenBooksWithLessThan5000Copies(EditionType.GOLD, 5000);

        // 3.
//        bookService.printBooksWithPriceLowerThanAndHigherThan(5, 40);

        // 4.
//        System.out.print("Enter year: ");
//        int year = Integer.parseInt(scanner.nextLine());
//        bookService.printBooksNotReleasedIn(year);

        // 5.
//        System.out.print("Enter date in format dd-MM-yyy: ");
//        String dateString = scanner.nextLine();
//        bookService.printBooksReleasedBefore(dateString);

        // 6.
//        System.out.print("Enter first name ending letters: ");
//        String input = scanner.nextLine();
//        authorService.printAuthorsWhoseFirstNameEndsWith(input);

        // 7.
//        System.out.print("Enter string: ");
//        String str = scanner.nextLine();
//        bookService.printBooksContaining(str);

        // 8.
//        System.out.print("Enter string: ");
//        String input = scanner.nextLine();
//        bookService.printBooksByAuthorStartingWith(input);

        // 9.
//        System.out.print("Enter length of title: ");
//        int length = Integer.parseInt(scanner.nextLine());
//        bookService.printNumberOfBooksWithLongerTitleThan(length);

        // 10.
//        authorService.printAuthorsWithTheirBookCopiesNumber();

        // 11.
//        System.out.print("Enter title: ");
//        String title = scanner.nextLine();
//        bookService.printReducedInfoAboutBookByTitle(title);

        // 12.
//        System.out.print("Enter date in the format dd MMM yyy: ");
//        String dateString = scanner.nextLine();
//        System.out.print("Enter amount: ");
//        int amount = Integer.parseInt(scanner.nextLine());
//
//        bookService.printBookCopiesAdded(dateString, amount);

        // 13.
//        System.out.print("Enter number: ");
//        int num = Integer.parseInt(scanner.nextLine());
//        bookService.deleteBooksWithCopiesLessThan(num);

        // 14.
        System.out.print("Enter first and last name: ");
        String fullName = scanner.nextLine();
        authorService.printNumberOfBooksWrittenBy(fullName);


    }


}
