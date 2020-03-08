package it.contrader.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Test;


public class TestDAO implements DAO<Test>{
    private final String QUERY_ALL = "SELECT * FROM test";
    private final String QUERY_CREATE = "INSERT INTO test (nome, data_i, data_m  , type_t) VALUES (?,?,?,?)";
    private final String QUERY_READ = "SELECT * FROM test WHERE id=?";
    private final String QUERY_UPDATE = "UPDATE test SET nome=? ,data_i=?, data_m=?, type_t=? WHERE id=?";
    private final String QUERY_DELETE = "DELETE FROM test WHERE id=?";

    public TestDAO() {}


    public List<Test> getAll() {
        List<Test> testList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Test test;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String data_i = resultSet.getString("data_i");
                String data_m = resultSet.getString("data_m");
                String type_t = resultSet.getString("type_t");
                test = new Test(nome, data_i, data_m , type_t);
                test.setId(id);
                testList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }



    public boolean insert(Test testToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(3, testToInsert.getData_m());
            preparedStatement.setString(2, testToInsert.getData_i());
            preparedStatement.setString(1, testToInsert.getNome());
            preparedStatement.setString(4, testToInsert.getType_t());

            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
    public Test read(int testId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String data_m, data_i, nome ,type_t;
            nome = resultSet.getString("nome");
            data_i = resultSet.getString("data_i");
            data_m = resultSet.getString("data_m");
            type_t = resultSet.getString("type_t");

            Test test = new Test(nome, data_i, data_m , type_t);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            return null;
        }

    }

    public boolean update(Test testToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();


        if (testToUpdate.getId() == 0)
            return false;

        Test testRead = read(testToUpdate.getId());
        if (!testRead.equals(testToUpdate)) {
            try {

                if (testToUpdate.getData_m() == null || testToUpdate.getData_m().equals("")) {
                    testToUpdate.setData_m(testRead.getData_m());}
                if (testToUpdate.getType_t()== null || testToUpdate.getType_t().equals("")) {
                    testToUpdate.setType_t(testRead.getType_t()); }


                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(3,  testToUpdate.getData_m());
                preparedStatement.setString(2,  testToUpdate.getData_i());
                preparedStatement.setString(1,  testToUpdate.getNome());
                preparedStatement.setString(4, testToUpdate.getType_t());
                preparedStatement.setInt(5, testToUpdate.getId());
                int a = preparedStatement.executeUpdate();
                if (a > 0)
                    return true;
                else
                    return false;

            } catch (SQLException e) {
                return false;
            }
        }

        return false;

    }

    public boolean delete(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            int n = preparedStatement.executeUpdate();
            if (n != 0)
                return true;

        } catch (SQLException e) {
        }
        return false;
    }
}
	
		
		
		
		
		
	
	
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

