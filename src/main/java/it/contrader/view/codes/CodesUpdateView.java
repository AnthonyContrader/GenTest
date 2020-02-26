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
			MainDispatcher.getInstance().callView("User", null);
		}
	}
	public void showOptions() {
		try {
			System.out.println("Inserisci id dell'utente:");
			id = Integer.parseInt(getInput());
			
			System.out.println("Inserisci tipo dell'utente:");
			String codestype = getInput();
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
