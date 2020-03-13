package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Codes;

@Repository
@Transactional
public interface CodesRepository extends CrudRepository<Codes, Long> {



}
