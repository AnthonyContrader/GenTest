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
import it.contrader.dto.TestDTO;
import it.contrader.service.Service;
import it.contrader.service.CodesService;

import it.contrader.service.TestService;
import org.apache.commons.io.FileUtils;



@MultipartConfig
public class CodesServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public CodesServlet() {
	
	}
	/** public static String inputStreamToString(InputStream inputStream) {
	        //In Java 9 use inputStream.readAllBytes()
	        return new Scanner(inputStream,"UTF-8").useDelimiter("\\A").next();
	    }**/
	public void updateList(HttpServletRequest request) {
		Service<CodesDTO> service = (Service<CodesDTO>) new CodesService();
		List<CodesDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	public void updateListt(HttpServletRequest request) {
		Service<TestDTO> service = (Service<TestDTO>) new TestService();
		List<TestDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);

	}
	

	@Override 
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<CodesDTO> service = (Service<CodesDTO>) new CodesService();
		Service<TestDTO> servicet = (Service<TestDTO>) new TestService();
		String mode = request.getParameter("mode");
		CodesDTO dto;
		TestDTO dtot;
		int id;
		boolean ans;
		boolean anst;
		DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		switch (mode.toUpperCase()) {
		
		case "CODESLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;

		case "TESTLIST":
			updateListt(request);
			getServletContext().getRequestDispatcher("/test/testmanager.jsp").forward(request, response);
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
			dto = new CodesDTO ( nome, data_i, data_m, type_t);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			Part filePart = request.getPart("file"); 
			nome = request.getParameter("nome").toString();			
			InputStream filecontent = filePart.getInputStream();
			File ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/" +cuser+"/"+nome+".java");
			FileUtils.copyInputStreamToFile(filecontent, ciao);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;

		case "GENERATE":
			cuser= request.getSession().getAttribute("user").toString();
			nome = request.getParameter("nome").toString()+"TEST";
			Calendar dataa = Calendar.getInstance();
			data_i = f.format(dataa.getTime()).toString();
			data_m = f.format(dataa.getTime()).toString();
			type_t = request.getParameter("type_t").toString();
			dtot = new TestDTO ( nome, data_i, data_m , type_t);
			anst = servicet.insert(dtot);
			request.setAttribute("ans", anst);
			//filePart = request.getPart("file");
			String nomee = request.getParameter("nome").toString();
			//filecontent = filePart.getInputStream();
			File origin = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/"+cuser+"/"+nomee+".java");
			ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/test/"+cuser+"/"+nomee+"TEST"+".java");
			FileUtils.copyFile(origin, ciao);
			updateListt(request);
			getServletContext().getRequestDispatcher("/test/testmanager.jsp").forward(request, response);


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
			ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/" +cuser+"/"+nome+".java");
			ciao.delete();
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/codes/codesmanager.jsp").forward(request, response);
			break;

		case "DELETET":
			cuser = request.getSession().getAttribute("user").toString();
			nome = request.getParameter("nome").toString();
			id = Integer.parseInt(request.getParameter("id"));
			ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/test/"+cuser+"/"+nome+".java");
			ciao.delete();
			anst = servicet.delete(id);
			request.setAttribute("ans", anst);
			updateListt(request);
			getServletContext().getRequestDispatcher("/test/testmanager.jsp").forward(request, response);
			break;
			
		}
	}
}
