package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.SupportDTO;
import it.contrader.service.SupportService;

@Controller
@RequestMapping("/support")

public class SupportController {

	@Autowired
	private SupportService service;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "support";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto",service.read(id));
		return "updatesupport";
	}
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("domanda") String domanda, @RequestParam("risposta") String risposta) {
		SupportDTO dto = new SupportDTO();
		dto.setDomanda(domanda);
		dto.setRisposta(risposta);
		service.insert(dto);
		setAll(request);
		return "support";
	}
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("domanda") String domanda, @RequestParam("risposta") String risposta) {
	
	SupportDTO dto = new SupportDTO();
	dto.setId(id);
	dto.setDomanda(domanda);
	dto.setRisposta(risposta);
	service.update(dto);
	setAll(request);
	return "support";
	}
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readsupport";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id ) {
		service.delete(id);
		setAll(request);
		return "support";
	}
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list",service.getAll());
	}
	
}
