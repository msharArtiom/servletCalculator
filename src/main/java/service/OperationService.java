package service;

import entity.Operation;
import entity.User;
import repositiry.JDBCOperationRepository;
import repositiry.OperationRepository;

import java.sql.SQLException;
import java.util.List;

public class OperationService {

    private final OperationRepository repository = new JDBCOperationRepository();

    public Operation calculate(Operation operation) throws SQLException {
        switch (operation.getType()) {
            case "sum" -> {
                operation.setResult(operation.getNum1() + operation.getNum2());
                repository.save(operation);
            }
            case "sub" -> {
                operation.setResult(operation.getNum1() - operation.getNum2());
                repository.save(operation);
            }
            case "mul" -> {
                operation.setResult(operation.getNum1() * operation.getNum2());
                repository.save(operation);
            }
            case "div" -> {
                operation.setResult(operation.getNum1() / operation.getNum2());
                repository.save(operation);
            }

        }
        return operation;
    }

    public List<Operation> getHistory(User user) {
        return repository.findAll(user);

    }
}
