package it.contrader.model;

import javax.persistence.*;

import antlr.collections.List;
import com.sun.tools.javac.jvm.Items;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Progetti {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String nome;
	private String data_i;
	private String data_m;

	@ManyToOne
	@JoinColumn(unique = true)
	private Codes codes;
}
