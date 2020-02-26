package it.contrader.dto;

import java.util.*;
public class CodesDTO {

	
	
	private int id;

	private Date data_m;

	
	
public CodesDTO() {
		
	}

	public CodesDTO(Date data_m) {
		this.data_m = data_m;
		
	}

	public CodesDTO (int id, Date data_m) {
		this.id = id;
		this.data_m = data_m;
		
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getData_m() {
		return this.data_m;
	}

	
	public String toString() {
		return  id + "\t"  + data_m;
	}

	
	
}

	
	
	
	
	
	
	
	
	

