package org.example.Bookshop.models;

import jakarta.persistence.*;
import org.example.Bookshop.models.enumerations.AgeRestriction;
import org.example.Bookshop.models.enumerations.EditionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated
    @Column(name = "edition_type", length = 11, nullable = false)
    private EditionType editionType;

    @Column(nullable = false, scale = 2, precision = 19)
    private BigDecimal price;

    @Column(length = 11, nullable = false)
    private int copies;

    @Column(name = "release_date")
    private Date releaseDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", length = 11, nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", nullable = false)
    private Author author;

//    @ManyToMany(mappedBy = "books", targetEntity = Category.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;


    public Book(){}

    public Book(String title, Date releaseDate, EditionType editionType, BigDecimal price, int copies, AgeRestriction ageRestriction) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.ageRestriction = ageRestriction;
        this.releaseDate = releaseDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", editionType=" + editionType +
                ", price=" + price +
                ", copies=" + copies +
                ", releaseDate=" + releaseDate +
                ", ageRestriction=" + ageRestriction +
                ", author=" + author +
                ", categories=" + categories +
                '}';
    }
}
