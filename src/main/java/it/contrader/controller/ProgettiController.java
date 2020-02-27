package it.contrader.controller;
import java.util.List;
import it.contrader.dto.ProgettiDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ProgettiService;

public class ProgettiController implements Controller {

	private static String sub_package = "progetti.";
	
	private ProgettiService progettiService;
	
	public ProgettiController() {
		this.progettiService = new ProgettiService();
		
	}
	
	
	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");
		
		
		int id;
		String nome;
		String data_i;
		String data_m;
		
		switch (mode) {
		
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			ProgettiDTO progettiDTO = progettiService.read(id);
			request.put("progetti", progettiDTO);
			MainDispatcher.getInstance().callView(sub_package + "ProgettiRead", request);
			break;
			
		case "INSERT":
			 nome = request.get("nome").toString();
			 data_i = request.get("data_i").toString();
			 data_m = request.get("data_m").toString();
			 
			 ProgettiDTO progettitoinsert = new ProgettiDTO(nome, data_i, data_m);
			 progettiService.insert(progettitoinsert);
			 request = new Request();
			 request.put("mode", "mode");
			 MainDispatcher.getInstance().callView(sub_package + "ProgettiInsert", request);
			 break;
		
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			progettiService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "ProgettiDelete", request);
			break;
			 
		case "UPDATE":
			 id = Integer.parseInt(request.get("id").toString());
			 nome = request.get("nome").toString();
			 data_i =  request.get("data_i").toString();
			 data_m =  request.get("data_m").toString();
			 ProgettiDTO progettitoupdate = new ProgettiDTO(nome, data_i, data_m);
			 progettitoupdate.setId(id);
			 progettiService.update(progettitoupdate);
			 request = new Request();
			 request.put("mode", "mode");
			 MainDispatcher.getInstance().callView(sub_package + "ProgettiUpdate", request);
			 break;
			 
		case "PROGETTILIST":
			List<ProgettiDTO> progetti_DTO = progettiService.getAll();
			request.put("progetti", progetti_DTO);
			MainDispatcher.getInstance().callView("Progetti", request);
			break;
		case "GETCHOICE":
			
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "ProgettiRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "ProgettiInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "ProgettiUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "ProgettiDelete", null);
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

