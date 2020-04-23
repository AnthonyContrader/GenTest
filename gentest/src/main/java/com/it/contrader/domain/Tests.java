package com.it.contrader.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Tests.
 */
@Entity
@Table(name = "tests")
public class Tests implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_i")
    private String data_i;

    @Column(name = "data_m")
    private String data_m;

    @OneToOne
    @JoinColumn(unique = true)
    private Codes codes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Tests nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_i() {
        return data_i;
    }

    public Tests data_i(String data_i) {
        this.data_i = data_i;
        return this;
    }

    public void setData_i(String data_i) {
        this.data_i = data_i;
    }

    public String getData_m() {
        return data_m;
    }

    public Tests data_m(String data_m) {
        this.data_m = data_m;
        return this;
    }

    public void setData_m(String data_m) {
        this.data_m = data_m;
    }

    public Codes getCodes() {
        return codes;
    }

    public Tests codes(Codes codes) {
        this.codes = codes;
        return this;
    }

    public void setCodes(Codes codes) {
        this.codes = codes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tests tests = (Tests) o;
        if (tests.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tests.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tests{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data_i='" + getData_i() + "'" +
            ", data_m='" + getData_m() + "'" +
            "}";
    }
}
