package it.contrader.view.testType;

import it.contrader.controller.Request;

import it.contrader.dto.TestTypeDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TestTypeReadView extends AbstractView {

	private int id;
	private Request request;
	private final String mode = "READ";
	
	public TestTypeReadView() {
		
	}
	@Override
	public void showResults(Request request) {
		if (request != null) {
			TestTypeDTO testType = (TestTypeDTO) request.get("testType");
			System.out.println(testType);
			MainDispatcher.getInstance().callView("TestType", null);
		}
	}
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID del Type Test:");
		id=Integer.parseInt(getInput());
		}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("TestType","doControl",request);
	}
}
