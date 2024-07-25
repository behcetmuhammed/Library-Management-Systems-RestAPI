package dev.patika.Library_Management_Systems_RestAPI.Business.Concretes;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.ICategoryService;
import dev.patika.Library_Management_Systems_RestAPI.Core.exception.NotFoundException;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.Msg;
import dev.patika.Library_Management_Systems_RestAPI.Dao.BookRepo;
import dev.patika.Library_Management_Systems_RestAPI.Dao.CategoryRepo;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;

    private final BookRepo bookRepo;

    public CategoryManager(CategoryRepo categoryRepo, BookRepo bookRepo) {
        this.categoryRepo = categoryRepo;
        this.bookRepo = bookRepo;
    }



    ////////////////////////         İŞLEMLER         ////////////////////////
    //Kaydetme
    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    //Getirme
    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    //Sayfalama
    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    //Güncelleme
    @Override
    public Category update(Category category) {
        this.get(category.getCategoryId());
        return this.categoryRepo.save(category);
    }

    //Silme
    @Override
    public Boolean delete(int id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return true;

//        Category category = this.get(id);
//
//        // Kontrol et
//        if (bookRepo.existsByCategoryId(id)) {
//            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
//        }
//
//        // Kategoriyi sil
//        this.categoryRepo.delete(category);
//        return "Kategori başarıyla silindi.";
    }
}
