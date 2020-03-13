package it.contrader.controller;
import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;

import it.contrader.dto.CodesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.contrader.service.CodesService;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
                         @RequestParam("type_t") String type_t) {

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
        return "codes";
    }

    private void setAll(HttpServletRequest request) {
        request.getSession().setAttribute("list", service.getAll());
    }
}
