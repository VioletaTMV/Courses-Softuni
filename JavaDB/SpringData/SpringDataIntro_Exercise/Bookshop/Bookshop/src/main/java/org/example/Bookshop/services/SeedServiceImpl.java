package org.example.Bookshop.services;

import org.example.Bookshop.constants.FilePath;
import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.models.Category;
import org.example.Bookshop.models.enumerations.AgeRestriction;
import org.example.Bookshop.models.enumerations.EditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private AuthorService authorService;
    private CategoryService categoryService;
    private BookService bookService;

    @Autowired
    public SeedServiceImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void seedAuthors() {

        try {
            Files.readAllLines(Path.of(FilePath.PATH_AUTHORS_FILE))
                    .forEach(row -> {
                        String authorFirstName = null;
                        String authorLastName = null;
                        Author author = new Author();
                        String[] authorNames = row.split("\\s+");
                        if (authorNames.length > 1) {
                            authorFirstName = authorNames[0];
                            authorLastName = authorNames[1];
                            author.setFirstName(authorFirstName);
                            author.setLastName(authorLastName);
                        } else {
                            authorLastName = authorNames[0];
                            author.setLastName(authorLastName);
                        }
//                        authorService.registerAuthor(author);
                        authorService.registerAuthor(author);
                    });

        } catch (IOException e) {
            System.out.println("Authors file could not be read.");
        }

    }

    @Override
    public void seedCategories() {

        try {
            Files.readAllLines(Path.of(FilePath.PATH_CATEGORIES_FILE))
                    .forEach(row -> {
                        String categoryName = row.strip();
                        if (!categoryName.isEmpty()) {
                            Category category = new Category(categoryName);
                            categoryService.registerCategory(category);
                        }
                    });
        } catch (IOException e) {
            System.out.println("Categories file could not be read");
            e.printStackTrace();
        }
    }

    @Override
    public void seedBooks() {

            try {
                Files.readAllLines(Path.of(FilePath.PATH_BOOKS_FILE))
                        .forEach(row -> {

                            String[] bookData = row.split(" ");
                            //Enum edition type
                            EditionType editionType = EditionType.values()[Integer.parseInt(bookData[0])];
                            Date releaseDate = null;
                            int copies = Integer.parseInt(bookData[2]);
                            BigDecimal price = new BigDecimal(bookData[3]);
                            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookData[4])];
                            String title = Arrays.stream(bookData)
                                    .skip(5)
                                    .collect(Collectors.joining(" "));

                            try {
                                releaseDate = new SimpleDateFormat("d/M/yyy").parse(bookData[1]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Book book = new Book(title, releaseDate, editionType, price, copies, ageRestriction);

                            Author author = authorService.getRandomAuthor();
                            book.setAuthor(author);

                            Set<Category> categories = categoryService.getRandomCategories();
                            book.setCategories(categories);

                            bookService.registerBook(book);

                        });
            } catch (IOException e) {
                System.out.println("Could not read Book file");
                e.printStackTrace();
            }


    }
}
