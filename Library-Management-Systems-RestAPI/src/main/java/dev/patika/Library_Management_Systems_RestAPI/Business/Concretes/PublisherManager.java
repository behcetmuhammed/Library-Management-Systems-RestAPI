package dev.patika.Library_Management_Systems_RestAPI.Business.Concretes;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IPublisherService;
import dev.patika.Library_Management_Systems_RestAPI.Core.exception.NotFoundException;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.Msg;
import dev.patika.Library_Management_Systems_RestAPI.Dao.PublisherRepo;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Category;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }



    ////////////////////////         İŞLEMLER         ////////////////////////
    //Kaydetme
    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    //Getirme
    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.publisherRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getPublisherId());
        return this.publisherRepo.save(publisher);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }
}
