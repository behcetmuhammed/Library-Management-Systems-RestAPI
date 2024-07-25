package dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);

    Category get(int id);

    Page<Category> cursor(int page, int pageSize);

    Category update(Category category);

    Boolean delete(int id);
}
