package org.example.Bookshop.repositories;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.models.enumerations.AgeRestriction;
import org.example.Bookshop.models.enumerations.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findBookByTitleAndAgeRestrictionAndCopiesAndEditionTypeAndPriceAndReleaseDate(
            String title,
            AgeRestriction ageRestriction,
            int copies,
            EditionType editionType,
            BigDecimal price,
            Date releaseDate
    );

    Optional<Set<Book>> findByReleaseDateAfter(Date date);

    Optional<Set<Book>> findByReleaseDateBefore(Date date);

    @Query("FROM Book b " +
            "WHERE b.author = :author " +
            "ORDER BY b.releaseDate DESC, b.title ASC")
    Optional<Set<Book>> findByGivenAuthorOrderedByReleaseDateAndTitle(Author author);

    // Below are exercises from Spring Data Advanced Querying
    Optional<Set<Book>> findTitleByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    Set<Book> findByPriceLessThanOrPriceGreaterThan(int lower, int higher);

    Set<Book> findByReleaseDateNot(Date yearOfDate);

    Set<Book> findByTitleContainingIgnoreCase(String str);

    Set<Book> findByAuthorLastNameStartingWithIgnoreCase(String inputUppercase);

    @Query(value = "SELECT count(b) " +
            "FROM Book b " +
            "WHERE Length(title) > :length")
    int countByLengthTitleGreaterThan(int length);

    @Query(value = "SELECT " +
            "b.title, " +
            "b.editionType, " +
            "b.ageRestriction, " +
            "b.price " +
            "FROM Book AS b " +
            "WHERE b.title = :title")
    List<List<Object>> getReducedBookInfoByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Book AS b " +
            "SET b.copies = b.copies + :amount " +
            "WHERE b.releaseDate > :date")
    int increaseBookCopiesOfBooksReleasedAfter(Date date, int amount);

    @Modifying
    @Transactional
    int deleteByCopiesLessThan(int num);

}
