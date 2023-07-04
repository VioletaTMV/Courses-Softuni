package org.example.Bookshop.repositories;

import org.example.Bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

    @Query("SELECT a " +
            "FROM Author a " +
            "JOIN a.books books " +
            "GROUP BY a " +
            "ORDER BY count(books) DESC")
    Set<Author> findAllAuthorsSortedByTheirBooksCount();


    Set<Author> findByFirstNameEndingWith(String input);

    @Query(value = "SELECT a.firstName, a.lastName, sum(b.copies) " +
            "FROM Author AS a " +
            "JOIN a.books AS b " +
            "GROUP BY a.id " +
            "ORDER By sum(b.copies) DESC")
    List<List<Object>> getAuthorsWithCopiesCount();


    @Procedure(value = "usp_select_total_count_books_by_author", outputParameterName = "count_books")
    Integer getTotalCountOfBooksForAuthor(String firstName, String lastName);
}
