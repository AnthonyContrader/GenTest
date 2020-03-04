package it.contrader.dto;


public class CodesDTO {

	
	
	private int id;

	private String data_m;

	
	
public CodesDTO() {
		
	}

	public CodesDTO(String data_m) {
		this.data_m = data_m;
		
	}

	public CodesDTO (int id, String data_m) {
		this.id = id;
		this.data_m = data_m;
		
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
	
	public String toString() {
		return  id + "\t"  + data_m;
	}
}

	
	
	
	
	
	
	
	
	

