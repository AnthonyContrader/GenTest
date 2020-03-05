package it.contrader.model;

import java.io.InputStream;

public class Codes{

	
	private int id;
	private String data_m;
	private String data_i;
	private String nome;

	
	public Codes() {
		
	}

	
	public  Codes( String data_m, String data_i, String nome) {
		this.data_m = data_m;
		this.nome = nome;
		this.data_i = data_i;
	}

	public Codes (int id, String data_m, String data_i,  String nome) {
		this.id = id;
		this.data_m = data_m;
		this.data_i = data_i;
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
		return  id + "\t"  + nome + "\t\t" + data_i +"\t\t" + data_m;
	}
		
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Codes other = (Codes) obj;
			if (id != other.id)
				return false;
			if (data_m==null) {
				if (other.data_m != null)
					return false;
			} else if (!data_m.equals(other.data_m))
				return false;
			if (data_i==null) {
				if (other.data_i != null)
					return false;
			} else if (!data_i.equals(other.data_i))
				return false;
			if (nome==null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			return true;
	}
}
