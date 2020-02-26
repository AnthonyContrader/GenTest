package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CodesDTO;
import it.contrader.model.Codes;

public class CodesConverter {


public CodesDTO toDTO(Codes codes) {
	CodesDTO codesDTO = new CodesDTO(codes.getId(), codes.getData_m());
	return codesDTO;
	
}

public Codes toEntity(CodesDTO codesDTO) {
Codes codes = new Codes(codesDTO.getId(), codesDTO.getData_m());
return codes; 

}

public List<CodesDTO> toDTOlist (List<Codes> codesList){

	List<CodesDTO> codesDTOList = new ArrayList<CodesDTO>();
	for(Codes codes : codesList) {
	
	codesDTOList.add(toDTO(codes));
		}
	return codesDTOList;
	
	
}

}