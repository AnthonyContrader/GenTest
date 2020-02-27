package it.contrader.view.codes;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CodesDeleteView extends AbstractView {
	private Request request;

	private int id;
	private final String mode = "DELETE";

	public CodesDeleteView() {
	}


public void showResults(Request request) {
	if (request!=null) {
		System.out.println("Cancellazione andata a buon fine.\n");
		MainDispatcher.getInstance().callView("Codes", null);
	}
}
public void showOptions() {
	System.out.println("Inserisci id del codice");
	id = Integer.parseInt(getInput());
}
	
		public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Codes", "doControl", request);
	}


}