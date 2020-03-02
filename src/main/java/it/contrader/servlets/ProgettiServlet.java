package it.contrader.servlets;

import java.util.List;



import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.ProgettiDTO;
import it.contrader.service.Service;
import it.contrader.service.ProgettiService;

public class ProgettiServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ProgettiServlet() {
		
	}

	public void updateList(HttpServletRequest request) {
		Service<ProgettiDTO> service = (Service<ProgettiDTO>) new ProgettiService();
		List<ProgettiDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
		
	}
	
	@Override 
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<ProgettiDTO> service = (Service<ProgettiDTO>) new ProgettiService();
		String mode = request.getParameter("mode");
		ProgettiDTO dto;
		int id;
		boolean ans;
		long millis=System.currentTimeMillis(); 
		switch (mode.toUpperCase()) {
		case "PROGETTILIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/progetti/progettimanager.jsp").forward(request, response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				getServletContext().getRequestDispatcher("/progetti/readprogetti.jsp").forward(request, response);
				
			}
			
			else 
				getServletContext().getRequestDispatcher("/progetti/updateprogetti.jsp").forward(request, response);
			
			break;
			
		case "INSERT":
			String nome = request.getParameter("nome").toString();
			 
	        String data_i=new java.sql.Date(millis).toString();  
	        String data_m=new java.sql.Date(millis).toString();  
			dto = new ProgettiDTO (nome, data_i, data_m);
			ans = service.insert(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/progetti/progettimanager.jsp").forward(request, response);
		
		case "UPDATE":
			nome = request.getParameter("nome");
			data_i=new java.sql.Date(millis).toString();  
	        data_m=new java.sql.Date(millis).toString(); 
			id = Integer.parseInt(request.getParameter("id"));
			dto = new ProgettiDTO (id,nome, data_i, data_m);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/progetti/progettimanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/progetti/progettimanager.jsp").forward(request, response);
			break;
			
		}
	}
}
