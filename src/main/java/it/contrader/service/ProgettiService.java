package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ProgettiConverter;

import it.contrader.dao.ProgettiRepository;
import it.contrader.dto.ProgettiDTO;

import it.contrader.model.Progetti;

@Service
public class ProgettiService extends AbstractService<Progetti, ProgettiDTO> {

	@Autowired
	private ProgettiConverter converter;

	@Autowired
	private ProgettiRepository repository;
	
	/*public ProgettiDTO findByIdUser(Long iduser){
		return converter.toDTO(repository.findByIdUser(iduser).get());	
	
	}*/

}
