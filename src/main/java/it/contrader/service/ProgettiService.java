package it.contrader.service;

import java.util.List;
import it.contrader.converter.ProgettiConverter;
import it.contrader.dao.ProgettiDAO;
import it.contrader.dto.ProgettiDTO;



public class ProgettiService {

	private ProgettiDAO progettiDAO;
	private ProgettiConverter progettiConverter;
	
	public ProgettiService() {
		this.progettiDAO = new ProgettiDAO();
		this.progettiConverter = new ProgettiConverter();
	}
	
	public List<ProgettiDTO> getAll() {
		return progettiConverter.toDTOList(progettiDAO.getALL());
	}
	
	public ProgettiDTO read(int id) {
		return progettiConverter.toDTO(progettiDAO.read(id));
	}
	
	public boolean insert(ProgettiDTO dto) {
		return progettiDAO.insert(progettiConverter.toEntity(dto));
	}
	 
	public boolean update(ProgettiDTO dto) {
		return progettiDAO.update(progettiConverter.toEntity(dto));
	}
	
	public boolean delete(int id) {
		return progettiDAO.delete(id);
	}
	
}
