package it.contrader.model;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;


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
    private String type_t;

    @OneToOne
    @JoinColumn(unique = true)
    private Codes codes;
}

