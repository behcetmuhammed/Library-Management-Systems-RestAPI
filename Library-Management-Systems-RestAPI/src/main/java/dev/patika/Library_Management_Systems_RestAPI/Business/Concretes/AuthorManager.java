package dev.patika.Library_Management_Systems_RestAPI.Business.Concretes;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IAuthorService;
import dev.patika.Library_Management_Systems_RestAPI.Core.exception.NotFoundException;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.Msg;
import dev.patika.Library_Management_Systems_RestAPI.Dao.AuthorRepo;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }


    ////////////////////////         İŞLEMLER         ////////////////////////
    //Kaydetme
    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    //Getirme
    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.authorRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public Author update(Author author) {
        this.get(author.getAuthorId());
        return this.authorRepo.save(author);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}
