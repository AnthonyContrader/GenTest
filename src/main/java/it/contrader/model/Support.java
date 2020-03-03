package it.contrader.model;

public class Support {

	private int id;
	private String domanda;
	private String risposta;
	
	public Support() {
		
	}
	
	public Support (String domanda, String risposta) {
		
		this.domanda=domanda;
		this.risposta=risposta;
	}
	
	public Support(int id,String domanda, String risposta) {
		
		this.id=id;
		this.domanda=domanda;
		this.risposta=risposta;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomanda() {
		return this.domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public String getRisposta() {
		return this.risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	
	@Override
	public String toString() {
		return  id + "\t"  + domanda +"\t\t" +   risposta + "\t\t" ;
		}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Support other = (Support) obj;
		if(id != other.id)
			return false;
		if(domanda == null) {
			if(other.domanda!=null)
				return false;
		}else if(!domanda.equals(other.domanda))
			return false;
		if(risposta==null) {
			if(other.risposta!=null)
				return false;
		}else if(!risposta.equals(other.risposta))
			return false;
		return true;
		}
		
	}
	

