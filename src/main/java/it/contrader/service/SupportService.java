package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.SupportConverter;
import it.contrader.dao.SupportRepository;
import it.contrader.dto.SupportDTO;

import it.contrader.model.Support;

@Service
public class SupportService extends AbstractService<Support,SupportDTO> {
	
	@Autowired
	private SupportConverter converter;
	
	@Autowired
	private SupportRepository repository;


}
