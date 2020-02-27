package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ProgettiDTO;
import it.contrader.model.Progetti;


public class ProgettiConverter {
	
	public ProgettiDTO toDTO(Progetti progetti) {
		ProgettiDTO progettiDTO = new ProgettiDTO(progetti.getId(), progetti.getNome(), progetti.getData_i(), progetti.getData_m());
		return progettiDTO;
	}
	
	
	public Progetti toEntity(ProgettiDTO progettiDTO) {
		Progetti progetti = new Progetti( progettiDTO.getId(), progettiDTO.getNome(), progettiDTO.getData_i(), progettiDTO.getData_m());
		return progetti;
	}
	
	public List<ProgettiDTO> toDTOList(List<Progetti> progettiList) {
		List<ProgettiDTO> progettiDTOList = new ArrayList<ProgettiDTO>();
		
		for(Progetti progetti : progettiList) {
			progettiDTOList.add(toDTO(progetti));
		}
		return progettiDTOList;
	}
	
}
