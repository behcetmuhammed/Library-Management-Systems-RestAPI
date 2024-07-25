package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Borrowing;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingSaveRequest {

    @NotNull
    @Size(min = 1, max = 255, message = "Müşteri (Ödünç alan) ismi 1 ile 255 karakter arasında olmalıdır")
    private String borrowerName;

    @Past(message = "Müşteri (Ödünç alan) kiralama tarihi geçmiş bir tarih olmalıdır")
    private LocalDate borrowerDate;

    @Future(message = "Müşteri (Ödünç alan) geri verme tarihi ileri bir tarih olmalıdır")
    private LocalDate  borrowerReturnDate;

    private int borrowerBook; //buraya bak sorun var???
}
