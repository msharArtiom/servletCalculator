package repositiry;

import entity.User;

import java.util.Optional;

public interface UserRepository {

    void createUser(User user);

    Optional<User> getUser(String name, String password);

    int getUsersCount(String name);

    User getUserByName(String name);
}
