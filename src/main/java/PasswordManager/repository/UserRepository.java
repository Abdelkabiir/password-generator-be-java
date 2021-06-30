package PasswordManager.repository;

import PasswordManager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findUserByUsername(String username);
}
