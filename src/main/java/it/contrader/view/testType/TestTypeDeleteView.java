package it.contrader.view.testType;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TestTypeDeleteView extends AbstractView {
	private Request request;
	
	private int id;
	private final String mode = "DELETE";
	
	public TestTypeDeleteView() {
		
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Cancellazione andata a buon fine.\n");
			MainDispatcher.getInstance().callView("TestType", null);
		}
	}
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("TestType", "doControl", request);
	}

	@Override
	public void showOptions() {
			System.out.println("Inserisci id dell'utente:");
			id = Integer.parseInt(getInput());

	}

}
