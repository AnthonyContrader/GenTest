package it.contrader.dto;

public class SupportDTO {

	private int id;
	private String domanda;
	private String risposta;
	
	public SupportDTO() {
		
	}
	
	public SupportDTO(String domanda, String risposta) {
		
		this.domanda=domanda;
		this.risposta=risposta;
		
	}

	public SupportDTO(int id, String domanda, String risposta) {
		
		this.id=id;
		this.domanda= domanda;
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
}
	