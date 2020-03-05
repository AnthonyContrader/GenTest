package it.contrader.service;


import it.contrader.converter.CodesConverter;
import it.contrader.dao.CodesDAO;
import it.contrader.dto.CodesDTO;
import it.contrader.model.Codes;



public class CodesService extends AbstractService <Codes, CodesDTO> {
    public CodesService ()  {
        this.dao = new CodesDAO ();
        this.converter = new CodesConverter (); 
    }
}
