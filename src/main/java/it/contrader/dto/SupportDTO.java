package it.contrader.dto;

import it.contrader.model.Support;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupportDTO {

	private Long id;
	private String domanda;
	private String risposta;
	
}
