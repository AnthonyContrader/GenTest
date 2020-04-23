package com.it.contrader.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Support.
 */
@Entity
@Table(name = "support")
public class Support implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "domanda")
    private String domanda;

    @Column(name = "risposta")
    private String risposta;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomanda() {
        return domanda;
    }

    public Support domanda(String domanda) {
        this.domanda = domanda;
        return this;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public Support risposta(String risposta) {
        this.risposta = risposta;
        return this;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
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
        Support support = (Support) o;
        if (support.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), support.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Support{" +
            "id=" + getId() +
            ", domanda='" + getDomanda() + "'" +
            ", risposta='" + getRisposta() + "'" +
            "}";
    }
}
