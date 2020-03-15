package it.contrader.converter;

import org.springframework.stereotype.Component;
import it.contrader.dto.SupportDTO;
import it.contrader.model.Support;

@Component
public class SupportConverter extends AbstractConverter<Support,SupportDTO> {
	
	@Override
	public Support toEntity(SupportDTO supportDTO) {
		
		Support support=null;
		if(supportDTO!=null) {
			support=new Support(supportDTO.getId(),supportDTO.getDomanda(),supportDTO.getRisposta());
			
		}
		return support;
	}
	@Override
	public SupportDTO toDTO(Support support) {
		
		SupportDTO supportDTO=null;
		if(support!=null) {
			
			supportDTO=new SupportDTO(support.getId(),support.getDomanda(),support.getRisposta());
		}
		return supportDTO;
	}
	

}
