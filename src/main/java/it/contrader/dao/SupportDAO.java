package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Support;

public class SupportDAO implements DAO<Support> {

	private final String QUERY_ALL = "SELECT * FROM support";
	private final String QUERY_CREATE = "INSERT INTO support (domanda,risposta) VALUES(?,?)";
	private final String QUERY_READ = "SELECT * FROM support WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE support SET domanda=?, risposta=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM support WHERE id=?";

	public SupportDAO() {
		
	}
	
	public List<Support> getAll(){
		
		List<Support> supportList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement= connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Support support;
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String domanda = resultSet.getString("domanda");
				String risposta = resultSet.getString("risposta");
				support = new Support(domanda, risposta);
				support.setId(id);
				supportList.add(support);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return supportList;
	}
	public boolean insert(Support supportToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, supportToInsert.getDomanda());
			preparedStatement.setString(2, supportToInsert.getRisposta());
			preparedStatement.execute();
			return true;
		}catch (SQLException e) {
			return false;
			
		}
	}
	
	public Support read(int supportId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, supportId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String domanda, risposta;
			
			domanda=resultSet.getString("domanda");
			risposta=resultSet.getString("risposta");
			Support support = new Support(domanda, risposta);
			support.setId(resultSet.getInt("id"));
			
			return support;
			
		}catch (SQLException e) {
			return null;
		}
	}
	public boolean update(Support supportToUpdate) {
		
		Connection connection = ConnectionSingleton.getInstance();
		
		if(supportToUpdate.getId() == 0)
			return false;
		
		Support supportRead = read(supportToUpdate.getId());
		if(!supportRead.equals(supportToUpdate)) {
			try {
				if(supportToUpdate.getDomanda()==null || supportToUpdate.getDomanda().equals("")) {
					supportToUpdate.setDomanda(supportRead.getDomanda());
				}
				if(supportToUpdate.getRisposta()==null || supportToUpdate.getRisposta().equals("")) {
					supportToUpdate.setRisposta(supportRead.getRisposta());
				}
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, supportToUpdate.getDomanda());
				preparedStatement.setString(2, supportToUpdate.getRisposta());
				preparedStatement.setInt(3, supportToUpdate.getId());
				
				int a = preparedStatement.executeUpdate();
				if(a>0)
					return true;
				else
					return false;
			}catch (SQLException e) {
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
			if( n != 0)
				return true;
		}catch (SQLException e) {
		}
		return false;
	}
}