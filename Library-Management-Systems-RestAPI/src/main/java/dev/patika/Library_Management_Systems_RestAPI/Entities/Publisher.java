package dev.patika.Library_Management_Systems_RestAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "publishers") //Yayıncılar
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", unique = true, nullable = false)
    private int publisherId;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "publisher_establishment_year", length = 100, nullable = false)
    private int publisherEstablishmentYear;

    @Column(name = "publisher_address")
    private String publisherAddress;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> bookList;
}
