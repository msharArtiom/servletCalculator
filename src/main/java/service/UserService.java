package service;

import entity.User;
import repositiry.JDBCUserRepository;
import repositiry.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository repository = new JDBCUserRepository();

    public User createUser(String name, String password) {
        User user = new User(name, password);
        repository.createUser(user);
        return user;
    }

    public Optional<User> getUser(String username, String password) {
        return repository.getUser(username, password);
    }

    public boolean canCreate(String name) {
        return repository.getUsersCount(name) == 0;
    }

    public User getUser(String name) {
        return repository.getUserByName(name);
    }
}
