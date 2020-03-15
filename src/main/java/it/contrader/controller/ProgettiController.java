package it.contrader.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ProgettiDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
//import it.contrader.model.User.Usertype;
import it.contrader.service.ProgettiService;

import java.util.Calendar;
@Controller
@RequestMapping("/progetti")
public class ProgettiController {

	DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	@Autowired
	private ProgettiService service;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "progetti";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateprogetti";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("nome") String nome) {
		Calendar data_ = Calendar.getInstance();
		String data_i = f.format(data_.getTime()).toString();
        String data_m = f.format(data_.getTime()).toString();
        ProgettiDTO dto = new ProgettiDTO();
		dto.setNome(nome);
		dto.setData_i(data_i);
		dto.setData_m(data_m);
		dto.setUser(null);
		service.insert(dto);
		setAll(request);
		return "progetti";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request,@RequestParam("id") Long id, @RequestParam("nome") String nome) {

		Calendar data__ = Calendar.getInstance();
		String data_m = f.format(data__.getTime()).toString();
		String data_i = f.format(data__.getTime()).toString();
		ProgettiDTO dto = new ProgettiDTO();
		dto.setId(id);
		dto.setNome(nome);
		dto.setData_i(data_i);
		dto.setData_m(data_m);
		dto.setUser(null);
		service.update(dto);
		setAll(request);
		return "progetti";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readprogetti";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "progetti";
	}


	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
