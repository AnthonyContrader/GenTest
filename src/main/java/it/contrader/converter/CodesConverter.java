package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CodesDTO;
import it.contrader.model.Codes;


public class CodesConverter implements Converter<Codes, CodesDTO> {


	@Override
	public CodesDTO toDTO(Codes codes) {
		CodesDTO codesDTO = new CodesDTO(codes.getId(), codes.getData_m(), codes.getData_i(), codes.getNome(), codes.getType_t()) ;
		return codesDTO;
	}
	
	
	
	
	@Override
	public Codes toEntity(CodesDTO codesDTO) {
		Codes codes = new Codes( codesDTO.getId(), codesDTO.getData_m(), codesDTO.getData_i(), codesDTO.getNome(), codesDTO.getType_t());
		return codes;
	}
	
	public List<CodesDTO> toDTOList(List<Codes> codesList) {
		List<CodesDTO> codesDTOList = new ArrayList<CodesDTO>();
		
		for(Codes codes : codesList) {
			codesDTOList.add(toDTO(codes));
		}
		return codesDTOList;
	}
	
}
