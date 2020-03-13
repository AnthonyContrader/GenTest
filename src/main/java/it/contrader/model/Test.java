package it.contrader.model;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
    private String data_i;
    private String data_m;

    @OneToOne(mappedBy = "test")
    private Codes codes;

    public Test(String nome){
        this.nome = nome;
    }

}

