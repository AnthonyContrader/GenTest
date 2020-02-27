package it.contrader.view.progetti;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class ProgettiDeleteView extends AbstractView{
	
	private Request request;
	private int id;
	private final String mode = "DELETE";
	
	public ProgettiDeleteView() {
		
	}
	
	
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Cancellazione andata a buon fine");
			MainDispatcher.getInstance().callView("Progetti", null);
		}
		
	}
	@Override
	public void showOptions() {
		System.out.println("inserisci id del progetto");
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
