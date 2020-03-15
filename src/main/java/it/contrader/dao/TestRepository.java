package it.contrader.dao;

import javax.transaction.Transactional;

import it.contrader.model.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface TestRepository extends CrudRepository<Test, Long> {

    Test findByNome(String nome);
}