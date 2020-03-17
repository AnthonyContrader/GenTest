package it.contrader.dto;
import javax.persistence.ManyToOne;

import it.contrader.model.Codes;
import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProgettiDTO {

	private Long id;
	private String nome;
	private String data_i;
	private String data_m;
	private Codes codes;

}
