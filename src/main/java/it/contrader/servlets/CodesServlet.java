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
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import it.contrader.dto.CodesDTO;
import it.contrader.service.Service;
import it.contrader.service.CodesService;
import it.contrader.dto.UserDTO;
import it.contrader.servlets.LoginServlet;

import org.apache.commons.io.FileUtils;



@MultipartConfig
public class CodesServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public CodesServlet() {
		
	}

	public void updateList(HttpServletRequest request) {
		Service<CodesDTO> service = (Service<CodesDTO>) new CodesService();
		List<CodesDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
		
	}
	

	@Override 
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<CodesDTO> service = (Service<CodesDTO>) new CodesService();
		String mode = request.getParameter("mode");
		CodesDTO dto;
		int id;
		boolean ans;
		DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		switch (mode.toUpperCase()) {
		
		case "CODESLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				getServletContext().getRequestDispatcher("/codes/readcodes.jsp").forward(request, response);
				
			}
			
			else 
				getServletContext().getRequestDispatcher("/codes/updatecodes.jsp").forward(request, response);
			
			break;
			
		case "INSERT":
			String cuser= request.getSession().getAttribute("user").toString();
			String nome = request.getParameter("nome").toString();
			Calendar data_ = Calendar.getInstance();
			String data_i = f.format(data_.getTime()).toString();
	        String data_m = f.format(data_.getTime()).toString(); 
	        String type_t = request.getParameter("type_t").toString();
			dto = new CodesDTO ( data_m, data_i, nome, type_t);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			Part filePart = request.getPart("file"); 
			nome = request.getParameter("nome").toString();			
			InputStream filecontent = filePart.getInputStream();
			File ciao = new File("c:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"+cuser+"/"+nome+".java");
			FileUtils.copyInputStreamToFile(filecontent, ciao);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);		
			break;
			
			
		case "UPDATE":
			nome = request.getParameter("nome"); 
			Calendar data__ = Calendar.getInstance();
			data_m = f.format(data__.getTime()).toString();
			type_t= request.getParameter("type_t");
		
			id = Integer.parseInt(request.getParameter("id"));
			dto = new CodesDTO(id ,data_m, nome, type_t);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			cuser = request.getSession().getAttribute("user").toString();
			nome = request.getParameter("nome").toString();
			id = Integer.parseInt(request.getParameter("id"));
			ciao = new File("c:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"+cuser+"/"+nome+".java");
			ciao.delete();
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;
			
		}
	}
}
