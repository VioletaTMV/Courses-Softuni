package org.example.Bookshop.repositories;

import org.example.Bookshop.models.Author;
import org.example.Bookshop.models.Book;
import org.example.Bookshop.models.enumerations.AgeRestriction;
import org.example.Bookshop.models.enumerations.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.Copies;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

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

}
