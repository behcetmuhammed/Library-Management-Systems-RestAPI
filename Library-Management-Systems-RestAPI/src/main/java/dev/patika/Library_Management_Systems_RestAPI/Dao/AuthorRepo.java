package dev.patika.Library_Management_Systems_RestAPI.Dao;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
