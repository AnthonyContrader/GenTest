package it.contrader.model;

import java.util.Date;

public class Progetti {
	
	private int id;
	private String nome;
	private String data_i;
	private String data_m;
	
	public Progetti() {
		
	}

	public Progetti (String nome, String data_i2, String data_m2) {
		this.nome = nome;
		this.data_i = data_i2;
		this.data_m = data_m2;	
	}
	
	public Progetti (int id, String nome, String data_i, String data_m) {
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
		this.data_i = data_m;
	}
	@Override
	public String toString() {
		return id + "\t"  + nome +"\t\t" +  data_i + "\t\t" + data_m;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
				return false;
		Progetti other = (Progetti) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (data_i == null) {
			if (other.data_i != null)
				return false;
		} else if (!data_i.equals(other.data_i))
			return false;
		if (data_m == null) {
			if(other.data_m != null)
				return false;
		} else if (!data_m.equals(other.data_m))
			return false;
		return true;
	}
}