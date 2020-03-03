package it.contrader.service;

import it.contrader.converter.SupportConverter;
import it.contrader.dao.SupportDAO;
import it.contrader.dto.SupportDTO;
import it.contrader.model.Support;


public class SupportService extends AbstractService<Support, SupportDTO> {
	
	public SupportService() {
		this.dao = new SupportDAO();
		this.converter = new SupportConverter();
	}

}
