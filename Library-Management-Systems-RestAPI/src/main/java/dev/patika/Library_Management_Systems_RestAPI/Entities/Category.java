package dev.patika.Library_Management_Systems_RestAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories") //Kategoriler
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private int categoryId;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription ;


    @ManyToMany (mappedBy = "categoryList", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Book> bookList;
}
