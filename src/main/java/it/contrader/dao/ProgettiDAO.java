package it.contrader.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Progetti;
import java.util.Date;
 
public class ProgettiDAO {
	
	private final String QUERY_ALL = "SELECT * FROM progetti";
	private final String QUERY_CREATE = "INSERT INTO progetti (nome, data_i, data_m) VALUES(?,?,?)";
	private final String QUERY_READ = "SELECT * FROM progetti WHERE id = ?";
	private final String QUERY_UPDATE = "UPDATE progetti SET nome=?, data_i=?, data_m=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM progetti WHERE id=?";
	
	public ProgettiDAO() {
		
	}
	
	public List<Progetti>getALL(){
		List<Progetti> progettiList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Progetti progetti;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String data_i = resultSet.getString("data_i");
				String data_m = resultSet.getString("data_m");
				progetti = new Progetti(nome, data_i, data_m);
				progetti.setId(id);
				progettiList.add(progetti);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return progettiList;
	}
	public boolean insert(Progetti progettiToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, progettiToInsert.getNome());
			preparedStatement.setString(2, progettiToInsert.getData_i());
			preparedStatement.setString(3, progettiToInsert.getData_m());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public Progetti read(int Id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			String nome;
			String data_i, data_m;
			
			nome = resultSet.getString("nome");
			data_i = resultSet.getString("data_i");
			data_m = resultSet.getString("data_m");
			Progetti progetti = new Progetti(nome, data_i, data_m);
			progetti.setId(resultSet.getInt("id"));
			
			return progetti;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public boolean update(Progetti progettiToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		
		if(progettiToUpdate.getId() == 0)
			return false;
		
		Progetti progettiRead = read(progettiToUpdate.getId());
		if(!progettiRead.equals(progettiToUpdate)) {
			try {
				if (progettiToUpdate.getNome() == null || progettiToUpdate.getNome().equals("")) {
					progettiToUpdate.setNome(progettiRead.getNome());
				}
				if (progettiToUpdate.getData_i() == null || progettiToUpdate.getData_i().equals("")) {
					progettiToUpdate.setData_i(progettiRead.getData_i());
				}
				if (progettiToUpdate.getData_m() == null || progettiToUpdate.getData_m().equals("")) {
					progettiToUpdate.setData_m(progettiRead.getData_m());
				}
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1,  progettiToUpdate.getNome());
				preparedStatement.setString (2, progettiToUpdate.getData_i());
				preparedStatement.setString (3, progettiToUpdate.getData_m());
				preparedStatement.setInt(4, progettiToUpdate.getId());
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
			if(n != 0 )
				return true;
			
		} catch (SQLException e) {			
		}
		return false;
	}
}

