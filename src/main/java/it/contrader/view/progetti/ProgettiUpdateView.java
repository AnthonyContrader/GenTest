package it.contrader.view.progetti;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ProgettiUpdateView extends AbstractView {
	private Request request;
	
	private int id;
	private String nome;
	private String data_i;
	private String data_m;
	private final String mode = "UPDATE";
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Progetti", null);
		}
		
	}
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id dell'utente:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci il nome del progetto");
			nome = getInput();
			System.out.println("Inserisci la data del progetto");
			data_i = getInput();
			System.out.println("Inserisci la data di modifica del progetto");
			data_m = getInput();
		} catch (Exception e) {

		}
		
	}
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("nome", nome);
		request.put("data_i", data_i);
		request.put("data_m", data_m);
		request.put("mode", mode);		
	}

}
