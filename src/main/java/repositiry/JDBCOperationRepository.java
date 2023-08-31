package repositiry;

import entity.Operation;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCOperationRepository implements OperationRepository{
    @Override
    public void save(Operation operation) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into calculator values  (?,?,?,?,?)");
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setDouble(2, operation.getNum2());
            preparedStatement.setString(3, operation.getType());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.setInt(5, operation.getUserId());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    @Override
    public List<Operation> findAll(User user) {
        List<Operation> operations = new ArrayList<>();
        String sql = "select * from calculator where user_id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double num1 = resultSet.getDouble(1);
                double num2 = resultSet.getDouble(2);
                String type = resultSet.getString(3);
                double result = resultSet.getDouble(4);
                Integer userId = resultSet.getInt(5);
                Operation operation = new Operation(num1, num2, type, result, userId);
                operations.add(operation);
            }
            return operations;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
}
