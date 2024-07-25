package dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Author;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Book;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Author save(Author author);

    Author get(int id);

    Page<Author> cursor(int page, int pageSize);

    Author update(Author author);

    Boolean delete(int id);
}
