package it.contrader.view.codes;
import java.util.Date;

import it.contrader.controller.Request;

import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.view.user.UserReadView;
public class CodesReadView extends UserReadView {
	public class UserReadView extends AbstractView {

		private int id;
		private Request request;
		private final String mode = "READ";

		public void CodesReadView() {
		}
		
		
		public void showResults(Request request) {
			if (request != null) {
				UserDTO user = (UserDTO) request.get("codes");
				System.out.println(user);
				MainDispatcher.getInstance().callView("Codes", null);
			}
				
				
			}
		
		public void showOptions() {
				System.out.println("Inserisci l'ID dell'utente:");
				id = Integer.parseInt(getInput());
				
				
				
			}public void submit() {
				request = new Request();
				request.put("id", id);
				request.put("mode", mode);
				MainDispatcher.getInstance().callAction("User", "doControl", request);
			}
	}
}
		
