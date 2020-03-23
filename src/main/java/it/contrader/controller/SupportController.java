package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.SupportDTO;
import it.contrader.service.SupportService;

@RestController
@RequestMapping("/support")
@CrossOrigin(origins = "http://localhost:4200")

public class SupportController extends AbstractController<SupportDTO>{
	
	@Autowired
	private SupportService supportService;
}
