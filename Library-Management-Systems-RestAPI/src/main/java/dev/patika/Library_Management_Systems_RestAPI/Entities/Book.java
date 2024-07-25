package dev.patika.Library_Management_Systems_RestAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books") //Kitaplar
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    private int bookId;

    @Column(name = "book_name", length = 100, nullable = false)
    private String bookName;

    @Column(name = "book_publication_year")
    private int publicationYear;

    @Column(name = "book_stock")
    private int bookStock;

    //Book (x)<=>(1) Author
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    private Author author;

    //Book (x)<=>(1) Publisher
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

    //Book (x)<=>(1) Book Borrower
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookBorrowing> borrowings;

    //Book (x)<=>(x) Category
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "book2cate",
            joinColumns = {@JoinColumn(name = "book2cate_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book2cate_category_id")}
    )
    private List<Category> categoryList;

}
