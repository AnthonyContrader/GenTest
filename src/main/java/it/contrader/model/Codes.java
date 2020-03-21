package it.contrader.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

//    @OneToOne
//    @JoinColumn(unique = true)
//    private Progetti progetti;
}