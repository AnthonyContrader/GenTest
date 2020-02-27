package it.contrader.view.codes;



import it.contrader.controller.Request;

import it.contrader.dto.CodesDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CodesReadView extends AbstractView {

		private int id;
		private Request request;
		private final String mode = "READ";

		public void CodesReadView() {
		}
		
		
		public void showResults(Request request) {
			if (request != null) {
				CodesDTO codes = (CodesDTO) request.get("codes");
				System.out.println(codes);
				MainDispatcher.getInstance().callView("Codes", null);
			}
				
				
			}
		
		public void showOptions() {
				System.out.println("Inserisci id del codice:");
				id = Integer.parseInt(getInput());
				
				
				
			}public void submit() {
				request = new Request();
				request.put("id", id);
				request.put("mode", mode);
				MainDispatcher.getInstance().callAction("Codes", "doControl", request);
			}
	}
		
