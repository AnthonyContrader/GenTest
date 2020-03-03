package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.SupportDTO;
import it.contrader.model.Support;

public class SupportConverter implements Converter<Support,SupportDTO>{
	
	@Override
	public SupportDTO toDTO(Support support) {
		SupportDTO supportDTO = new SupportDTO(support.getId(),support.getDomanda(),support.getRisposta());
		return supportDTO;
	}
	@Override
	public Support toEntity(SupportDTO supportDTO) {
		Support support= new Support(supportDTO.getId(), supportDTO.getDomanda(),supportDTO.getRisposta());
				return support;
	}
	@Override
	public List<SupportDTO> toDTOList(List<Support> supportList){
		List<SupportDTO> supportDTOList = new ArrayList<SupportDTO>();
		for(Support support:supportList) {
			supportDTOList.add(toDTO(support));
		}
		return supportDTOList;
	}
}
