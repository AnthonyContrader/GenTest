package it.contrader.dao;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Support;

@Repository
@Transactional

public interface SupportRepository extends CrudRepository<Support, Long> {
	

}
