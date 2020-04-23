package com.it.contrader.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Support entity.
 */
public class SupportDTO implements Serializable {

    private Long id;

    private String domanda;

    private String risposta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupportDTO supportDTO = (SupportDTO) o;
        if (supportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupportDTO{" +
            "id=" + getId() +
            ", domanda='" + getDomanda() + "'" +
            ", risposta='" + getRisposta() + "'" +
            "}";
    }
}
