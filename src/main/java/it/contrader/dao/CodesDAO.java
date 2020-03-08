package it.contrader.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Codes;


public class CodesDAO implements DAO<Codes>{
	private final String QUERY_ALL = "SELECT * FROM codes";
	private final String QUERY_CREATE = "INSERT INTO codes (nome, data_i, data_m , type_t) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM codes WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE codes SET nome=? ,data_i=?, nome=?, data_i=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM codes WHERE id=?";

	public CodesDAO() {}

	
	public List<Codes> getAll() {
		List<Codes> codesList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Codes codes;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String data_i = resultSet.getString("data_i");
				String data_m = resultSet.getString("data_m");
				String type_t = resultSet.getString("type_t");
				codes = new Codes(nome, data_i, data_m , type_t);
				codes.setId(id);
				codesList.add(codes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codesList;
	}
		
	
	
	public boolean insert(Codes codesToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(3, codesToInsert.getData_m());
			preparedStatement.setString(2, codesToInsert.getData_i());
			preparedStatement.setString(1, codesToInsert.getNome());
			preparedStatement.setString(4, codesToInsert.getType_t());
			
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	public Codes read(int codesId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, codesId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String data_m, data_i, nome ,type_t;
			nome = resultSet.getString("nome");
			data_i = resultSet.getString("data_i");
			data_m = resultSet.getString("data_m");
			type_t = resultSet.getString("type_t");
			
			Codes codes = new Codes(nome, data_i, data_m , type_t);
	        codes.setId(resultSet.getInt("id"));

			return codes;
		} catch (SQLException e) {
			return null;
		}

	}
	
	public boolean update(Codes codesToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		
		if (codesToUpdate.getId() == 0)
			return false;

		Codes codesRead = read(codesToUpdate.getId());
		if (!codesRead.equals(codesToUpdate)) {
			try {
				
				if (codesToUpdate.getData_m() == null || codesToUpdate.getData_m().equals("")) {
					codesToUpdate.setData_m(codesRead.getData_m());}
				if (codesToUpdate.getType_t()== null || codesToUpdate.getType_t().equals("")) {
					codesToUpdate.setType_t(codesRead.getType_t()); }


				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(3,  codesToUpdate.getData_m());
				preparedStatement.setString(2,  codesToUpdate.getData_i());
				preparedStatement.setString(1,  codesToUpdate.getNome());
				preparedStatement.setString(4, codesToUpdate.getType_t());
				preparedStatement.setInt(5, codesToUpdate.getId());
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
	
		
		
		
		
		
	
	
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

