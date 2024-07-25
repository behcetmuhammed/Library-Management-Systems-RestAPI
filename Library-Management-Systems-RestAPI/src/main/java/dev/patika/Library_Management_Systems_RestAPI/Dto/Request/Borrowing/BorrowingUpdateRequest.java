package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Borrowing;

import java.time.LocalDate;

public class BorrowingUpdateRequest {
    private String borrowerName;
    private LocalDate borrowerDate;
    private LocalDate  borrowerReturnDate;
    private int borrowerBook;
}
