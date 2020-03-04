package it.contrader.servlets;

import java.util.Calendar;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import it.contrader.dto.ProgettiDTO;
import it.contrader.service.Service;
import it.contrader.service.ProgettiService;
import javax.servlet.http.Part;


@MultipartConfig

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
		DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
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
			Calendar data_ = Calendar.getInstance();
			String data_i = f.format(data_.getTime()).toString();
	        String data_m = f.format(data_.getTime()).toString(); 
			dto = new ProgettiDTO (nome, data_i, data_m);
			ans = service.insert(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/progetti/progettimanager.jsp").forward(request, response);
			break;
		case "UPDATE":
			nome = request.getParameter("nome"); 
			Calendar data__ = Calendar.getInstance();
			data_m = f.format(data__.getTime()).toString();
			id = Integer.parseInt(request.getParameter("id"));
			dto = new ProgettiDTO(id, nome, data_m);
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
