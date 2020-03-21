package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.contrader.dto.CodesDTO;
import it.contrader.service.CodesService;
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
}
