package dev.patika.Library_Management_Systems_RestAPI.Dao;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    //boolean existsByCategoryId(int categoryId); //Kategoriyi ID'ye Göre var
}
