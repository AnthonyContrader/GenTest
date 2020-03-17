package it.contrader.converter;

import org.springframework.stereotype.Component;
import it.contrader.dto.ProgettiDTO;
import it.contrader.model.Progetti;

@Component
public class ProgettiConverter extends AbstractConverter<Progetti,ProgettiDTO> {

	@Override
	public Progetti toEntity(ProgettiDTO progettiDTO) {
		
		Progetti progetti=null;
		if(progettiDTO!=null) {
			progetti=new Progetti(progettiDTO.getId(),progettiDTO.getNome(),progettiDTO.getData_i(),progettiDTO.getData_m(),progettiDTO.getCodes());
			
		}
		return progetti;
	}
	@Override
	public ProgettiDTO toDTO(Progetti progetti) {
		ProgettiDTO progettiDTO = null;
		if(progetti != null) {
			progettiDTO = new ProgettiDTO(progetti.getId(),progetti.getNome(),progetti.getData_i(),progetti.getData_m(), progetti.getCodes());
		}
		return progettiDTO;
	}
	
	
}
