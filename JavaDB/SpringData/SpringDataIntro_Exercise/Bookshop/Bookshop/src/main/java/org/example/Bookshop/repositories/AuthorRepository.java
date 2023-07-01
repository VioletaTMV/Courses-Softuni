package org.example.Bookshop.repositories;

import org.example.Bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long>{


    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

   @Query("SELECT a " +
           "FROM Author a " +
           "JOIN a.books books " +
           "GROUP BY a " +
           "ORDER BY count(books) DESC")
    Set<Author> findAllAuthorsSortedByTheirBooksCount();



}
