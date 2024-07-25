package dev.patika.Library_Management_Systems_RestAPI.Business.Concretes;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IBookService;
import dev.patika.Library_Management_Systems_RestAPI.Core.exception.NotFoundException;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.Msg;
import dev.patika.Library_Management_Systems_RestAPI.Dao.BookRepo;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {

    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    ////////////////////////         İŞLEMLER         ////////////////////////
    //Kaydetme
    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    //Getirme
    @Override
    public Book get(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public Book update(Book book) {
        this.get(book.getBookId());
        return this.bookRepo.save(book);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        Book book = this.get(id);
        this.bookRepo.delete(book);
        return true;
    }
}
