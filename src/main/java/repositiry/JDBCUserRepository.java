package repositiry;
import entity.User;
import java.sql.*;
import java.util.Optional;

public class JDBCUserRepository implements UserRepository {
    @Override
    public void createUser(User user) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getUser(String name, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ? and password = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getString(1), resultSet.getString(2)));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int getUsersCount(String name) {
        int userCount = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from users where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCount;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String password = resultSet.getString("password");
                user = new User(id, userName, password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
