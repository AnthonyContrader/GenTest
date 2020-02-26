package it.contrader.view.testType;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TestTypeInsertView extends AbstractView{

	private Request request;
	private String type_t;
	private String descrizione;
	private final String mode = "INSERT";
	
	public TestTypeInsertView() {
		
	}
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("TestType", null);
		}
	}
		@Override
		public void showOptions() {
				System.out.println("Inserisci il nome del tipo di test:");
				type_t = getInput();
				System.out.println("Inserisci la descrizione della tipologia:");
				descrizione = getInput();
			
		}
		
		@Override
		public void submit() {
			request = new Request();
			request.put("type_t", type_t);
			request.put("desrizione", descrizione);
			request.put("mode", mode);
			MainDispatcher.getInstance().callAction("TestType", "doControl", request);
		}
}
