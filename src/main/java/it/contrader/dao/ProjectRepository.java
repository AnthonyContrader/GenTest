package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Progetti;
import it.contrader.model.User;

@Repository
@Transactional

public interface ProjectRepository extends CrudRepository<Progetti,Long>{
	
	public List<Progetti> findByUser_id(long id);
	//public List<Progetti> insert(String nome, String idUser);
	public List<Progetti> findAllByUser(User user);
}
