package dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);

    Book get(int id);

    Page<Book> cursor(int page, int pageSize);

    Book update(Book book);

    Boolean delete(int id);
}
