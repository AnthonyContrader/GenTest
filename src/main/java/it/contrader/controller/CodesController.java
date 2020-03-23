package it.contrader.controller;

import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import it.contrader.dto.CodesDTO;
import it.contrader.service.CodesService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@RestController
@RequestMapping("/codes")
@CrossOrigin(origins = "http://localhost:4200")
public class CodesController extends AbstractController<CodesDTO>{
    DateFormat f = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Autowired
    private CodesService codesService;


    @PostMapping(value = "/insertcodes")
    public CodesDTO insert (@RequestBody CodesDTO dto) {
        Calendar data_ = Calendar.getInstance();
        String data_i = f.format(data_.getTime()).toString();
        String data_m = f.format(data_.getTime()).toString();
        dto.setData_m(data_m);
        dto.setData_i(data_i);
        codesService.insert(dto);
        return dto;
    }
    String nome;
    @PostMapping(value = "/setnamep")
    public void setnamep (@RequestBody String nome){
        this.nome = nome;
    }

    @PostMapping(value = "/uploadcontent")
    public void uploadcontent (@RequestBody String fileString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/samirhysa/workspace_contrader/gentest-angular/codes/"+nome+".txt"));
        writer.write(fileString);
        writer.close();
    }
}
