package it.contrader.controller;

import java.util.List;
import it.contrader.dto.TestTypeDTO;
import it.contrader.service.TestTypeService;
import it.contrader.main.MainDispatcher;

public class TestTypeController implements Controller {

	private static String sub_package = "testType.";
	
	private TestTypeService testTypeService;
	
	public TestTypeController() {
		this.testTypeService = new TestTypeService();
	}

	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		int id;
		String type_t;
		String descrizione;
		
		switch (mode) {
		
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			TestTypeDTO testTypeDTO = testTypeService.read(id);
			request.put("testType", testTypeDTO);
			MainDispatcher.getInstance().callView(sub_package + "TestTypeRead", request);
			break;
			
		case "INSERT":
			type_t = request.get("type_t").toString();
			descrizione = request.get("descrizione").toString();
			
			TestTypeDTO testTypetoinsert = new TestTypeDTO(type_t,descrizione);
			testTypeService.insert(testTypetoinsert);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "TestTypeInsert", request);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			testTypeService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "TestTypeDelete", request);
			break;
		
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			type_t = request.get("type_t").toString();
			descrizione = request.get("descrizione").toString();
			TestTypeDTO testTypetoupdate = new TestTypeDTO(type_t,descrizione);
			testTypetoupdate.setId(id);
			testTypeService.update(testTypetoupdate);
			request= new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package +"TestTypeUpdate", request);
			break;
			
		case "TESTTYPELIST":
			List<TestTypeDTO> testTypesDTO = testTypeService.getAll();
			request.put("testType", testTypesDTO);
			MainDispatcher.getInstance().callView("TestType",request);
			break;
			
		case "GETCHOICE":
			switch (choice.toUpperCase()) {
				case "L":
					MainDispatcher.getInstance().callView(sub_package + "TestTypeRead", null);
					break;
					
				case "I":
					MainDispatcher.getInstance().callView(sub_package + "TestTypeInsert", null);
					break;
					
				case "M":
					MainDispatcher.getInstance().callView(sub_package + "TestTypeUpdate", null);
					break;
					
				case "C":
					MainDispatcher.getInstance().callView(sub_package + "TestTypeDelete", null);
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
			
