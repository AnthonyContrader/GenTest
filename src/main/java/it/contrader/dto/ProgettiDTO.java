package it.contrader.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.contrader.model.Progetti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")

public class ProgettiDTO {

	private long id;
	private String nome;
	//private LocalDateTime  data_i;
	//private LocalDateTime  data_m;
	private UserDTO userDTO;
	
}
