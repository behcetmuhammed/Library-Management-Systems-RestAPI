package dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Category;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher save(Publisher publisher);

    Publisher get(int id);

    Page<Publisher> cursor(int page, int pageSize);

    Publisher update(Publisher publisher);

    Boolean delete(int id);
}
