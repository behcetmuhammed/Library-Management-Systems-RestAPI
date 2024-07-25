package dev.patika.Library_Management_Systems_RestAPI.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "borrowers") //kitap kiralayanlar (Borçlular)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrower_id", unique = true, nullable = false)
    private int borrowerId;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "borrower_date", length = 100, nullable = false)
    private LocalDate borrowerDate;

    @Column(name = "borrower_return_date")
    private LocalDate  borrowerReturnDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_borrower_id", referencedColumnName = "book_id")
    private Book book;
}

