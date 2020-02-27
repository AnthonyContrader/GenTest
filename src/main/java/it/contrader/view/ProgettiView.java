package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.ProgettiDTO;
import it.contrader.main.MainDispatcher;


		public class ProgettiView extends AbstractView {
	
			private Request request;
			private String choice;
	
			public ProgettiView() {
				
			}
	
			
			@Override
			public void showResults(Request request) {
				if (request != null) {
					System.out.println("\n------------------- Gestione progetti ----------------\n");
					System.out.println("ID\tNome\tData_i\tData_m");
					System.out.println("----------------------------------------------------\n");
					
					@SuppressWarnings("unchecked")
					List<ProgettiDTO> progetti = (List<ProgettiDTO>) request.get("progetti");
					for (ProgettiDTO u: progetti)
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
				MainDispatcher.getInstance().callAction("Progetti", "doControl", this.request);
			}
	
		}


