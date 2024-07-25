package dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Borrowing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingResponse {

    //Kullanıcıya (İstemciye) gösterecek bilgiler
    private String borrowerName;
    private LocalDate borrowerDate;
    private LocalDate  borrowerReturnDate;
    private int borrowerBook;
}
