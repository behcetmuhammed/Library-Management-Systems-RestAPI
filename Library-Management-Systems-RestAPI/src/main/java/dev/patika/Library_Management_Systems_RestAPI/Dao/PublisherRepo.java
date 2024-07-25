package dev.patika.Library_Management_Systems_RestAPI.Dao;

import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}
