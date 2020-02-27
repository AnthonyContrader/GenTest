package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CodesDTO;
import it.contrader.main.MainDispatcher;


		public class CodesView extends AbstractView {
	
			private Request request;
			private String choice;
	
			public CodesView() {
				
			}
	
			
			@Override
			public void showResults(Request request) {
				if (request != null) {
					System.out.println("\n------------------- Gestione codici ----------------\n");
					System.out.println("ID\tData_m");
					System.out.println("----------------------------------------------------\n");
					
					@SuppressWarnings("unchecked")
					List<CodesDTO> codes = (List<CodesDTO>) request.get("codess");
					for (CodesDTO u: codes)
						System.out.println(u);
					System.out.println();
				}
			}
	
			
			@Override
			public void showOptions() {
				System.out.println("          Scegli l'operazione da effettuare:");
				System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");
	
				this.choice = getInput();
			}
			
			
			@Override
			public void submit() {
				request = new Request();
				request.put("choice", choice);
				request.put("mode", "GETCHOICE");
				MainDispatcher.getInstance().callAction("Codes", "doControl", this.request);
			}
	
		}
