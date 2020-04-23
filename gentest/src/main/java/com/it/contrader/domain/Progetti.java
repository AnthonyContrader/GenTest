package com.it.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Progetti.
 */
@Entity
@Table(name = "progetti")
public class Progetti implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "progetti")
    private Set<Codes> codes = new HashSet<>();

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

    public Progetti nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Codes> getCodes() {
        return codes;
    }

    public Progetti codes(Set<Codes> codes) {
        this.codes = codes;
        return this;
    }

    public Progetti addCodes(Codes codes) {
        this.codes.add(codes);
        codes.setProgetti(this);
        return this;
    }

    public Progetti removeCodes(Codes codes) {
        this.codes.remove(codes);
        codes.setProgetti(null);
        return this;
    }

    public void setCodes(Set<Codes> codes) {
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
        Progetti progetti = (Progetti) o;
        if (progetti.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), progetti.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Progetti{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
