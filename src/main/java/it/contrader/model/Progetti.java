package it.contrader.model;

import javax.persistence.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Progetti {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //Long
	
	@Column(unique=true)
	private String nome;
	//private LocalDateTime  data_i;
	//private LocalDateTime  data_m;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idUser", referencedColumnName = "id")
	private User user;
	
}
