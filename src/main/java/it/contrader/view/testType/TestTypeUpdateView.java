package it.contrader.view.testType;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TestTypeUpdateView extends AbstractView {
	private Request request;

	private int id;
	private String type_t;
	private String descrizione;
	private final String mode = "UPDATE";

	public TestTypeUpdateView() {
	}


	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("TestType", null);
		}
	}


	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id del tipo del test");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci il tipo del test");
			type_t = getInput();
			System.out.println("Inserisci la descrizione");
			descrizione = getInput();
		} catch (Exception e) {

		}
	}


	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("type_t", type_t);
		request.put("descrizione", descrizione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("TestType", "doControl", request);
	}

}
