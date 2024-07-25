package dev.patika.Library_Management_Systems_RestAPI.Business.Concretes;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IBorrowingService;
import dev.patika.Library_Management_Systems_RestAPI.Core.exception.NotFoundException;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.Msg;
import dev.patika.Library_Management_Systems_RestAPI.Dao.BorrowingRepo;
import dev.patika.Library_Management_Systems_RestAPI.Entities.BookBorrowing;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowingManager implements IBorrowingService {
    private final BorrowingRepo borrowingRepo;

    public BorrowingManager(BorrowingRepo borrowingRepo) {
        this.borrowingRepo = borrowingRepo;
    }



    ////////////////////////         İŞLEMLER         ////////////////////////
    //Kaydetme
    @Override
    public BookBorrowing save(BookBorrowing borrowing) {
        return this.borrowingRepo.save(borrowing);    }

    //Getirme
    @Override
    public BookBorrowing get(int id) {
        return this.borrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.borrowingRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public BookBorrowing update(BookBorrowing borrowing) {
        this.get(borrowing.getBorrowerId());
        return this.borrowingRepo.save(borrowing);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        BookBorrowing borrowing = this.get(id);
        this.borrowingRepo.delete(borrowing);
        return true;
    }
}
