package it.contrader.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ProgettiDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ProgettiService;
import it.contrader.service.UserService;

@RestController
@RequestMapping("/progetti")
@CrossOrigin(origins = "http://localhost:4200")

public class ProgettiController extends AbstractController<ProgettiDTO> {
	
	DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	@Autowired
	private ProgettiService progettiService;
	@Autowired
	private UserService userService;
	
	/*@PostMapping(value="/insertprogetti")
	public ProgettiDTO insert(@RequestBody ProgettiDTO dto ) {
		Calendar data_ = Calendar.getInstance();
		String data_i = f.format(data_.getTime()).toString();
        String data_m = f.format(data_.getTime()).toString();
        dto.setData_i(data_i);
		dto.setData_m(data_m);
		//dto.setUserDTO(userService.read(Long.parseLong(idUser)));
		progettiService.insert(dto);
		
		return dto;
	}*/
	@PostMapping(value="/getallbyuser")
	
	public List<ProgettiDTO> getAllByUser(@RequestBody UserDTO userDTO){
		
		return progettiService.getAllByUser(userDTO);
	}
	
}
