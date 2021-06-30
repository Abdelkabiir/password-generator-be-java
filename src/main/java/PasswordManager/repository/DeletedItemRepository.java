package PasswordManager.repository;

import PasswordManager.model.DeletedItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeletedItemRepository extends MongoRepository<DeletedItem, String> {}
