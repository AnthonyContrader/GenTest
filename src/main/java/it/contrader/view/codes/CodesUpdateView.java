package it.contrader.view.codes;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CodesUpdateView  extends AbstractView {
	private Request request;

	private int id;
	private String data_m;


	private final String mode = "UPDATE";

	public  void UserUpdateView() {
	}

	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Codes", null);
		}
	}
	public void showOptions() {
		try {
			System.out.println("Inserisci id del progetto");
			id = Integer.parseInt(getInput());
			
			System.out.println("Inserisci la data di modifica");
			data_m = getInput();
		} catch (Exception e) {

		}
	
	}

	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("data_m", data_m);
		;
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Codes", "doControl", request);
	}



}
