package it.contrader.dto;

import java.util.Date;

public class ProgettiDTO {

	private int id;
	private Date data_i;
	private Date data_m;
	private String nome;
	
	public ProgettiDTO() {
		
	}
	
	public ProgettiDTO(String nome, Date data_i, Date data_m) {
		this.nome = nome;
		this.data_i = data_i;
		this.data_m = data_m;
	}
	public ProgettiDTO(int id, String nome, Date data_i, Date data_m) {
		this.id = id;
		this.nome = nome;
		this.data_i = data_i;
		this.data_m = data_m;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData_i() {
		return this.data_i;
	}
	public void setData_i(Date data_i) {
		this.data_i = data_i;
	}
	public Date getData_m() {
		return this.data_m;
	}
	public void setData_m(Date data_m) {
		this.data_m = data_m;
	}
	@Override
	public String toString() {
		return id + "\t"  + nome +"\t\t" +  data_i + "\t\t" + data_m;
	}
}
