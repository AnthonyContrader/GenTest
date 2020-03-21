package it.contrader.dao;

import it.contrader.model.Codes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface
CodesRepository extends CrudRepository<Codes, Long> {

}
