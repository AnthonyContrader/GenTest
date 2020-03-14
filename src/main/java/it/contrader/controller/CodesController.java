package it.contrader.controller;
import it.contrader.dto.UserDTO;
import it.contrader.dto.CodesDTO;
import it.contrader.service.CodesService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@MultipartConfig
@Controller
@RequestMapping("/codes")
public class CodesController {
    DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    @Autowired
    private CodesService service;

    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "codes";
    }
    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
        String  cuser = request.getSession().getAttribute("user").toString();
        String nome = request.getParameter("nome").toString();
        File ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/codes/" +cuser+"/"+nome+".java");
        ciao.delete();
        service.delete(id);
        setAll(request);
        return "codes";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
        request.getSession().setAttribute("dto", service.read(id));
        return "updatecodes";
    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request, @RequestParam("nome") String nome,
                         @RequestParam("type_t") String type_t) throws IOException, ServletException {

        String cuser= request.getSession().getAttribute("user").toString();
        Calendar data_ = Calendar.getInstance();
        String data_i = f.format(data_.getTime()).toString();
        String data_m = f.format(data_.getTime()).toString();
        CodesDTO dto = new CodesDTO();
        dto.setNome(nome);
        dto.setData_i(data_i);
        dto.setData_m(data_m);
        dto.setType_t(type_t);
        service.insertc(dto);
        setAll(request);

        Part filePart = request.getPart("file");
        nome = request.getParameter("nome").toString();
        InputStream filecontent = filePart.getInputStream();
        File ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/codes/"+cuser+"/"+nome+".java");
        FileUtils.copyInputStreamToFile(filecontent, ciao);
        return "codes";
    }

    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("id") Long id) {
        request.getSession().setAttribute("dto", service.read(id));
        return "readcodes";
    }

    private void setAll(HttpServletRequest request) {
        request.getSession().setAttribute("list", service.getAll());
    }
}
