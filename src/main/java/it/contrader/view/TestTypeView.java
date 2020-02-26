package it.contrader.view;
import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.TestTypeDTO;
import it.contrader.main.MainDispatcher;

public class TestTypeView extends AbstractView {

	private Request request;
	private String choice;
	
	public TestTypeView() {
		
	}
	public void showResults(Request request) {
		if(request != null) {
			System.out.println("\n----------------Gestione Tipi di Test----------------\n");
			System.out.println("ID\tTest\tDescrizione della tipologia");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<TestTypeDTO> testType = (List<TestTypeDTO>) request.get("testType");
			for (TestTypeDTO t: testType)
				System.out.println(t);
			System.out.println();
		}
	}
	@Override
	public void showOptions() {
		System.out.println("Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");
		
		this.choice = getInput();
	}
	@Override
	public void submit() {
		request = new Request();
		request.put("choice",choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("TestType","doControl",this.request);
		
	}
	
}
