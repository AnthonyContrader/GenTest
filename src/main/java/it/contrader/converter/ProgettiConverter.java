package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.contrader.dto.ProgettiDTO;
import it.contrader.model.Progetti;



	@Component
	public class ProgettiConverter extends AbstractConverter<Progetti, ProgettiDTO>{
		
		@Autowired
		UserConverter userConverter; 
		
		@Override
		public Progetti toEntity(ProgettiDTO progettiDTO) {
			
			Progetti progetti=null;
			if(progettiDTO!=null) {
				progetti = new Progetti();
				progetti.setId(progettiDTO.getId());
				if(progettiDTO.getUserDTO()!=null) {
					progetti.setUser(userConverter.toEntity(progettiDTO.getUserDTO()));
				}
				
				progetti.setNome(progettiDTO.getNome());
				//progetti.setData_i(progettiDTO.getData_i());
				//progetti.setData_m(progettiDTO.getData_m());


				
			}
			return progetti;
		}
		
			@Override
			public ProgettiDTO toDTO(Progetti progetti) {
				
				ProgettiDTO progettiDTO = null;
				if(progetti!=null) {
				progettiDTO = new ProgettiDTO();
				progettiDTO.setId(progetti.getId());
				if(progetti.getUser()!=null) {
					progettiDTO.setUserDTO(userConverter.toDTO(progetti.getUser()));
				}
				progettiDTO.setNome(progetti.getNome());
				//progettiDTO.setData_i(progetti.getData_i());
				//progettiDTO.setData_m(progetti.getData_m());
				
			}
				return progettiDTO;
			}
	}
