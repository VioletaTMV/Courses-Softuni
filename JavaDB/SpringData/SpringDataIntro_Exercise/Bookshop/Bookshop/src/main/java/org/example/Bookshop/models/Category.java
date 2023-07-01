package org.example.Bookshop.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", length = 11)
    private int id;

    @Column(nullable = false)
    private String name;

 //   @ManyToMany
//    @JoinTable(name = "books_categories",
//    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
//    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"))
    @ManyToMany(mappedBy = "categories", targetEntity = Book.class)
    private Set<Book> books;


    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
