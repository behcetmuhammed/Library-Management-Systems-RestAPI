package dev.patika.Library_Management_Systems_RestAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors") //Yazarlar
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", unique = true, nullable = false)
    private int authorId;

    @Column(name = "author_name", length = 100, nullable = false)
    private String authorName;

    @Column(name = "author_birth_date")
    private LocalDate authorBirthDate ;

    @Column(name = "author_country")
    private String authorCountry ;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> bookList;
}
