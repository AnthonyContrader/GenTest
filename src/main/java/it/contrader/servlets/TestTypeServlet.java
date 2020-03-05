package it.contrader.servlets;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.TestTypeDTO;
import it.contrader.service.Service;
import it.contrader.service.TestTypeService;

public class TestTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public TestTypeServlet() {
		
	}
	
	public void updateList(HttpServletRequest request) {
		Service<TestTypeDTO> service = (Service<TestTypeDTO>) new TestTypeService();
		List<TestTypeDTO>listDTO=service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Service<TestTypeDTO> service = (Service<TestTypeDTO>) new TestTypeService();
		String mode = request.getParameter("mode");
		TestTypeDTO dto;
		int id;
		boolean ans;
		
		switch(mode.toUpperCase()) {
		
		case "TESTTYPELIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/testType/testTypemanager.jsp").forward(request,response);
			break;
			
		case "READ":
			id=Integer.parseInt(request.getParameter("id"));
			dto= service.read(id);
			request.setAttribute("dto", dto);
			if(request.getParameter("update")==null) {
				getServletContext().getRequestDispatcher("/testType/readtestType.jsp").forward(request, response);
			}
			else getServletContext().getRequestDispatcher("/testType/updatetestType.jsp").forward(request, response);
			break;
			
		case "INSERT":
			String type_t = request.getParameter("type_t").toString();
			String descrizione=request.getParameter("descrizione").toString();
			dto=new TestTypeDTO(type_t,descrizione);
			ans=service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/testType/testTypemanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			type_t=request.getParameter("type_t");
			descrizione=request.getParameter("descrizione");
			id = Integer.parseInt(request.getParameter("id"));
			dto=new TestTypeDTO(id,type_t,descrizione);
			ans=service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/testType/testTypemanager.jsp").forward(request, response);
			break;
			
		case "DELETE":
			id=Integer.parseInt(request.getParameter("id"));
			ans=service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/testType/testTypemanager.jsp").forward(request,response);
			break;
				
			}
					
			
		}
		
	}
	
	


