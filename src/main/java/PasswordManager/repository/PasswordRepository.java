package PasswordManager.repository;

import PasswordManager.model.Password;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordRepository extends MongoRepository<Password, String> {}
