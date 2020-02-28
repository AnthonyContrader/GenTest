package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	private String choice;
	private Request request;


	@Override
	public void showResults(Request request) {
		System.out.println("");

	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("╭━┳━╭━╭━╮╮\n" + 
				"┃┈┈┈┣▅╋▅┫┃\n" + 
				"┃┈┃┈╰━╰━━━━━━╮\n" + 
				"╰┳╯┈┈┈┈┈┈┈┈┈◢▉◣\n" + 
				"╲┃┈┈┈┈┈┈┈┈┈┈▉▉▉\n" + 
				"╲┃┈┈┈┈┈┈┈┈┈┈◥▉◤\n" + 
				"╲┃┈┈┈┈╭━┳━━━━╯\n" + 
				"╲┣━━━━━━┫\n" + 
				"\n" + 
				"");
		System.out.println("QUI PUOI MODIFICARE IL TUO PROGETTO, CODICE E IL TIPO DEL TEST DA EFFETTUARE");
		System.out.println("[P]rogetti [T]esttype [C]odici [E]sci");

		choice = this.getInput();

	}

	@Override
	public void submit() {
		request = new Request();
		switch (choice) {

		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		   	
        case "p":
        	this.request.put("mode", "PROGETTILIST");
        	MainDispatcher.getInstance().callAction("Progetti", "doControl", request);
        	break;
        
        case "t":
        	this.request.put("mode", "TESTTYPELIST");
        	MainDispatcher.getInstance().callAction("TestType", "doControl", request);
        	break;
        	
        case "c":
        	this.request.put("mode", "CODESLIST");
        	MainDispatcher.getInstance().callAction("Codes", "doControl", request);
        	break;

		default:
			request.put("choice", choice);
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
