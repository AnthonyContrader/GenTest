package it.contrader.servlets;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import it.contrader.dto.CodesDTO;
import it.contrader.service.Service;
import it.contrader.service.CodesService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



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
		String UPLOAD_DIRECTORY = "/Users/samirhysa";
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
			String nome = request.getParameter("nome").toString();
			Calendar data_ = Calendar.getInstance();
			String data_i = f.format(data_.getTime()).toString();
	        String data_m = f.format(data_.getTime()).toString(); 
			dto = new CodesDTO ( data_m, data_i, nome);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;
			
			
		case "INSERTCODE":
			File file ;
			int maxFileSize = 5000 * 1024;
			int maxMemSize = 5000 * 1024;
			String filePath = "/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/data";
			String contentType = request.getContentType();
			if ((contentType.indexOf("multipart/form-data") >= 0)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			factory.setRepository(new File("c:\\temp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax( maxFileSize );
			try{
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			while ( i.hasNext () )
			{
			FileItem fi = (FileItem)i.next();
			if ( !fi.isFormField () ) {
			String fieldName = fi.getFieldName();
			String fileName = fi.getName();
			boolean isInMemory = fi.isInMemory();
			long sizeInBytes = fi.getSize();
			file = new File( filePath + "yourFileName") ;
			fi.write( file ) ;
			System.out.println("Uploaded Filename: " + filePath + fileName + "<br>");
			}
			}
			}catch(Exception ex) {
			System.out.println(ex);
			}
			}else{
			System.out.println("Error in file upload.");
			}
			break;
		case "UPDATE":
			nome = request.getParameter("nome"); 
			Calendar data__ = Calendar.getInstance();
			data_m = f.format(data__.getTime()).toString();
			id = Integer.parseInt(request.getParameter("id"));
			dto = new CodesDTO(id ,data_m, nome);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;
			
		}
	}
}
