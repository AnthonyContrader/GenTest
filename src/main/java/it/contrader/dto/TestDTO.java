package it.contrader.dto;

import it.contrader.model.Codes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data

public class TestDTO {

    private Long id;
    private String nome;
    private String data_i;
    private String data_m;
    private String type_t;
    private Codes codes;

}