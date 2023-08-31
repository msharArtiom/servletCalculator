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
        int idByName = repository.getUsersCount(user.getName());
        if (idByName != 0) {
            user.setId(idByName);
            return user;
        }

        throw new IllegalArgumentException("User not created");
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
