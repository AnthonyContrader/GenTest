package it.contrader.dto;


public class CodesDTO {

	
	
	private int id;
	private String data_m;
	private String data_i;
	private String nome;

	
	public CodesDTO() {
		
	}

	public  CodesDTO( String data_m, String data_i, String nome) {
		this.data_m = data_m;
		this.nome = nome;
		this.data_i = data_i;
	}
	

	public CodesDTO (int id, String data_m, String data_i, String nome) {
		this.id = id;
		this.data_m = data_m;
		this.data_i = data_i;
		this.nome = nome;		
	}

	
	public CodesDTO(int id, String data_m, String nome) {
		this.id = id;
		this.data_m = data_m;
		this.nome = nome;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getData_m() {
		return this.data_m;
	}
	
	public void setData_m(String data_m) {
		this.data_m = data_m;
	}
	
	public String getData_i() {
		return this.data_i;
	}
	
	public void setData_i(String data_i) {
		this.data_i = data_i;
	}
	
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return  id + "\t"  + nome + "\t\t" + data_i +"\t\t" + data_m ;
	}
}
	
	
	
	
	
	
	
	

