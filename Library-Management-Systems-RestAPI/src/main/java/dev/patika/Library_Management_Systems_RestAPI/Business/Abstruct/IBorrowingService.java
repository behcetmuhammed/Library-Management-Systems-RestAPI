package dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct;

import dev.patika.Library_Management_Systems_RestAPI.Entities.BookBorrowing;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import org.springframework.data.domain.Page;

public interface IBorrowingService {
    BookBorrowing save(BookBorrowing borrowing);

    BookBorrowing get(int id);

    Page<BookBorrowing> cursor(int page, int pageSize);

    BookBorrowing update(BookBorrowing borrowing);

    Boolean delete(int id);
}
