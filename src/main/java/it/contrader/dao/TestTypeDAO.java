package it.contrader.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.TestType;

public class TestTypeDAO {

	private final String QUERY_ALL = "SELECT * FROM test_type";
	private final String QUERY_CREATE = "INSERT INTO test_type (id,type_t,descrizione) VALUES(?,?,?)";
	private final String QUERY_READ = "SELECT * FROM test_type WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE test_type SET id=?,type_t=?,descrizione=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM test_type WHERE id=?";
	
	public TestTypeDAO() {
		
	}
	
	public List<TestType> getAll(){
		List<TestType> testTypesList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			TestType testType;
			while(resultSet.next()) {
				int id =resultSet.getInt("id");
				String type_t = resultSet.getString("type_t");
				String descrizione = resultSet.getString("descrizione");
				testType= new TestType(type_t,descrizione);
				testType.setId(id);
				testTypesList.add(testType);
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return testTypesList;	
	}
	public boolean insert (TestType testTypeToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, testTypeToInsert.getType_t());
			preparedStatement.setString(2, testTypeToInsert.getDescrizione());
			preparedStatement.execute();
			return true;
		}catch (SQLException e) {
			return false;
		}
	}
	public TestType read(int testTypeId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, testTypeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String type_t, descrizione;
			
			type_t = resultSet.getString("type_t");
			descrizione = resultSet.getString("descrizione");
			TestType testType = new TestType(type_t,descrizione);
			testType.setId(resultSet.getInt("id"));
			
			return testType;
		}
		catch (SQLException e) {
			return null;
		}
	}
	public boolean update(TestType testTypeToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		
		if(testTypeToUpdate.getId()==0)
			return false;
		
		TestType testTypeRead = read(testTypeToUpdate.getId());
		if(!testTypeRead.equals(testTypeToUpdate)) {
			try {
				if(testTypeToUpdate.getType_t() == null || testTypeToUpdate.getType_t().contentEquals("")) {
					testTypeToUpdate.setType_t(testTypeRead.getType_t());
				}
				if(testTypeToUpdate.getDescrizione() == null || testTypeToUpdate.getDescrizione().contentEquals("")) {
					testTypeToUpdate.setDescrizione(testTypeRead.getDescrizione());
				}
			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
			preparedStatement.setString(1, testTypeToUpdate.getType_t());
			preparedStatement.setString(2, testTypeToUpdate.getDescrizione());
			int a = preparedStatement.executeUpdate();
			if(a>0)
				return true;
			else
				return false;
		} catch(SQLException e) {
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
			if(n!=0)
				return true;
			
		} catch (SQLException e ) {
		}
		return false;
	}
}
		