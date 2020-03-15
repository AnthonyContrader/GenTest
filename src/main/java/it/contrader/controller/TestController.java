package it.contrader.controller;
import it.contrader.dto.CodesDTO;
import it.contrader.dto.TestDTO;
import it.contrader.model.Codes;
import it.contrader.service.CodesService;
import it.contrader.service.TestService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/test", method = RequestMethod.GET)
public class TestController {
    DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Autowired
    private TestService service;

    @Autowired
    private CodesService servicec;

    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "test";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
        String  cuser = request.getSession().getAttribute("user").toString();
        String nome = request.getParameter("nome").toString();
        File ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/test/" +cuser+"/"+nome+".java");
        ciao.delete();
        service.delete(id);
        setAll(request);
        return "test";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
        request.getSession().setAttribute("dto", service.read(id));
        return "updatetest";
    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request, @RequestParam("Codesname") Codes nomec,
                         @RequestParam("type_t") String type_t) throws IOException, ServletException {

        String cuser= request.getSession().getAttribute("user").toString();
        Calendar data_ = Calendar.getInstance();
        String data_i = f.format(data_.getTime()).toString();
        String data_m = f.format(data_.getTime()).toString();
        TestDTO dto = new TestDTO();
        dto.setNome(nomec.getNome()+"TEST");
        dto.setData_i(data_i);
        dto.setData_m(data_m);
        dto.setType_t(type_t);
        dto.setCodes(nomec);
        service.insert(dto);
        setAll(request);

        File origin = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/codes/"+cuser+"/"+nomec.getNome()+".java");
        File ciao = new File("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/test/"+cuser+"/"+nomec.getNome()+"TEST"+".java");
        FileUtils.copyFile(origin, ciao);
        return "test";
    }

    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("id") Long id) {
        request.getSession().setAttribute("dto", service.read(id));
        return "readtest";
    }

    private void setAll(HttpServletRequest request) {
        request.getSession().setAttribute("list", service.getAll());
        request.getSession().setAttribute( "listos", servicec.getAll());
    }
}
