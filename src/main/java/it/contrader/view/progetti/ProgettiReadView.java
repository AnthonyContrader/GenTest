package it.contrader.view.progetti;

import it.contrader.controller.Request;
import it.contrader.dto.ProgettiDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ProgettiReadView extends AbstractView {
	private int id;
	private Request request;
	private final String mode = "READ";
	
	public ProgettiReadView() {
		
	}
	@Override
	public void showResults(Request request) {
		if (request != null) {
			ProgettiDTO progetti = (ProgettiDTO) request.get("progetti");
			System.out.println(progetti);
			MainDispatcher.getInstance().callView("Progetti", null);
		}
	}
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID del progetto:");
		id = Integer.parseInt(getInput());
	}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Progetti", "doControl", request);
	}

	
}
