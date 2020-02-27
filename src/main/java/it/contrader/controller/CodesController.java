package it.contrader.controller;



import java.util.List;
import java.util.Date;
import it.contrader.dto.CodesDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.CodesService;


public class CodesController implements Controller {

	
	private static String sub_package = "codes.";
	
	private CodesService codesService;
	
	public CodesController() {
		this.codesService = new CodesService();  
	}
	
	
	
	public void doControl(Request request) {
		
	
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		int id;
     	String data_m;
		

		switch (mode) {
		
	
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			CodesDTO codesDTO = codesService.read(id);
			request.put("codes", codesDTO);
			MainDispatcher.getInstance().callView(sub_package + "CodesRead", request);
			break;
		
		case "INSERT":
			data_m = request.get("data_m").toString();
			
		
			CodesDTO codestoinsert = new CodesDTO(data_m);
			
			codesService.insert(codestoinsert);
			request = new Request();
			request.put("mode", "mode");
			
			MainDispatcher.getInstance().callView(sub_package + "CodesInsert", request);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			
			codesService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "CodesDelete", request);
			break;
		
		
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			data_m = request.get("codestype").toString(); 
			CodesDTO codestoupdate = new CodesDTO(data_m);
			codestoupdate.setId(id);
			codesService.update(codestoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "CodesUpdate", request);
			break;
			
		
		case "CODESLIST":
			List<CodesDTO> codesDTO1 = codesService.getAll();
			
			request.put("codess", codesDTO1);
			MainDispatcher.getInstance().callView("Codes", request);
			break;
			
		
		case "GETCHOICE":
					
					
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "CodesRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "CodesInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "CodesUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "CodesDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeAdmin", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
			
		default:
			MainDispatcher.getInstance().callView("Login", null);
		}
	}
}

