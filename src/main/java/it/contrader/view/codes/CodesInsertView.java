package it.contrader.view.codes;
import java.util.Date;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
public class CodesInsertView<Requets>  extends AbstractView {
	private it.contrader.controller.Request Request;
	
	private String data_m;
	private final String mode = "INSERT";
	public CodesInsertView () {
		
	}
	public void showResults (Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("Codes", null);
		}
	}
	public void showOptions() {
		System.out.println("Inserisci username dell'utente:");
		data_m = getInput();
		
}public void submit() {
	Request = new Request();
	Request.put("data_m", data_m);
	Request.put("mode", mode);
	MainDispatcher.getInstance().callAction("Codes", "doControl", Request);
}
}


