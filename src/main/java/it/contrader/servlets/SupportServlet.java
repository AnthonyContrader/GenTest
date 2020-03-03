package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.SupportDTO;
import it.contrader.service.Service;
import it.contrader.service.SupportService;

public class SupportServlet extends HttpServlet {

	private static final long serialVersionUID =1L;
	
	public SupportServlet() {
		
	}
	
	public void updateList(HttpServletRequest request) {
		Service<SupportDTO> service =(Service<SupportDTO>) new SupportService();
		List<SupportDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Service<SupportDTO> service =(Service<SupportDTO>) new SupportService();
		String mode = request.getParameter("mode");
		SupportDTO dto;
		int id;
		boolean ans;
		
		switch(mode.toUpperCase()) {
		
		case "SUPPORTLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/support/supportmanager.jsp").forward(request,response);
			break;
			
		case "READ":
			id= Integer.parseInt(request.getParameter("id"));
			dto=service.read(id);
			request.setAttribute("dto", dto);
			
			if(request.getParameter("update")==null) {
				getServletContext().getRequestDispatcher("/support/readsupport.jsp").forward(request,response);
			}
			
			else getServletContext().getRequestDispatcher("/support/updatesupport.jsp").forward(request,response);
			break;
		
		case "INSERT":
			String domanda = request.getParameter("domanda").toString();
			String risposta = request.getParameter("risposta").toString();
			dto = new SupportDTO (domanda, risposta);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/support/supportmanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			domanda = request.getParameter("domanda");
			risposta = request.getParameter("risposta");
			id = Integer.parseInt(request.getParameter("id"));
			dto = new SupportDTO (id,domanda,risposta);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/support/supportmanager.jsp").forward(request, response);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans",ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/support/supportmanager.jsp").forward(request, response);
			break;
			
		}
		
	}
}
