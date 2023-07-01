package org.example.Bookshop.services;

public interface SeedService {

    void seedAuthors();

    void seedCategories();

    void seedBooks();

    default void seedDatabase(){
        seedAuthors();
        seedCategories();
        seedBooks();
    }


}
