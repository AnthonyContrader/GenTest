package it.contrader.dto;


public class ProgettiDTO {

	private int id;
	private String data_i;
	private String data_m;
	private String nome;
	
	public ProgettiDTO() {
		
	}
	
	public ProgettiDTO(String nome, String data_i, String data_m) {
		this.nome = nome;
		this.data_i = data_i;
		this.data_m = data_m;
	}
	public ProgettiDTO(int id, String nome, String data_i, String data_m) {
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
	public String getData_i() {
		return this.data_i;
	}
	public void setData_i(String data_i) {
		this.data_i = data_i;
	}
	public String getData_m() {
		return this.data_m;
	}
	public void setData_m(String data_m) {
		this.data_m = data_m;
	}
	@Override
	public String toString() {
		return id + "\t"  + nome +"\t\t" +  data_i + "\t\t" + data_m;
	}
}
