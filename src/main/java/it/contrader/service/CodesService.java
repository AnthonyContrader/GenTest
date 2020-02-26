package it.contrader.service;

import java.util.List;

import it.contrader.converter.CodesConverter;
import it.contrader.dao.CodesDAO;
import it.contrader.dto.CodesDTO;

public class CodesService  {

	
	
	private CodesDAO codesDAO;
	private CodesConverter codesConverter;
	
    public CodesService () {
        this.codesDAO = new CodesDAO ();
        this.codesConverter = new CodesConverter (); 
}
        
  public List<CodesDTO> getAll () {
	  return codesConverter.toDTOlist(codesDAO.getAll());
	  
  }
  


public CodesDTO read(int id) {
	
	return codesConverter.toDTO(codesDAO.read(id));
}
public boolean insert(CodesDTO dto) {
	
	return codesDAO.insert(codesConverter.toEntity(dto));
}


public boolean update(CodesDTO dto) {
	
	return codesDAO.update(codesConverter.toEntity(dto));
}


public boolean delete(int id) {
	
	return codesDAO.delete(id);
}


}