package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ProgettiConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.ProjectRepository;
import it.contrader.dto.ProgettiDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Progetti;

@Service
public class ProgettiService extends AbstractService<Progetti,ProgettiDTO> {
	
	@Autowired
	private ProgettiConverter progettiConverter;

	@Autowired
	private ProjectRepository progettiRepository;
	
	@Autowired
	private UserConverter userConverter;
	

	
	public  List<ProgettiDTO> getByUser_id(long id){
		return converter.toDTOList(progettiRepository.findByUser_id(id));
	}
	public List<ProgettiDTO> getAllByUser(UserDTO userDTO){
		return progettiConverter.toDTOList(progettiRepository.findAllByUser(userConverter.toEntity(userDTO)));
	}
	/*public List<ProgettiDTO> insert(String nome, String idUser){
		return progettiConverter.toDTOList(progettiRepository.insert(nome, idUser));
	}*/
}
