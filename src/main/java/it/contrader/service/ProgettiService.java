package it.contrader.service;


import it.contrader.converter.ProgettiConverter;
import it.contrader.dao.ProgettiDAO;
import it.contrader.dto.ProgettiDTO;
import it.contrader.model.Progetti;



public class ProgettiService extends AbstractService<Progetti, ProgettiDTO>{
	
	public ProgettiService() {
		this.dao = new ProgettiDAO();
		this.converter = new ProgettiConverter();
	}
	
}
