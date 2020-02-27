package it.contrader.model;


public class Codes{

	
	private int id;
	private String data_m;

	

	
	public Codes() {
		
	}

	public  Codes(String data_m) {
		this.data_m = data_m;
		
	}

	public Codes (int id, String data_m) {
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
			return true;
	}
}
