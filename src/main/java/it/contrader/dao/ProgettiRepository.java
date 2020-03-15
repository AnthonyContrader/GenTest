package it.contrader.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Progetti;

@Repository
@Transactional
public interface ProgettiRepository extends CrudRepository<Progetti,Long>{

	//public Optional<Progetti> findByIdUser(Long iduser);
	
	 //Progetti findByIdAndNomeProgetto(Long id, String nome);
}
