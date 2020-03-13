package it.contrader.model;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Codes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String nome;
    private String data_i;
    private String data_m;
    private String type_t;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Test test;

    public Codes(String nome, Test test){

        this.nome = nome;
        this.test = test;
        this.test.setCodes(this);
    }

}