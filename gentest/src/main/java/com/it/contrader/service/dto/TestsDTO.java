package com.it.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Tests entity.
 */
public class TestsDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    private String data_i;

    private String data_m;

    private Long codesId;

    private String codesNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_i() {
        return data_i;
    }

    public void setData_i(String data_i) {
        this.data_i = data_i;
    }

    public String getData_m() {
        return data_m;
    }

    public void setData_m(String data_m) {
        this.data_m = data_m;
    }

    public Long getCodesId() {
        return codesId;
    }

    public void setCodesId(Long codesId) {
        this.codesId = codesId;
    }

    public String getCodesNome() {
        return codesNome;
    }

    public void setCodesNome(String codesNome) {
        this.codesNome = codesNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TestsDTO testsDTO = (TestsDTO) o;
        if (testsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), testsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TestsDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data_i='" + getData_i() + "'" +
            ", data_m='" + getData_m() + "'" +
            ", codes=" + getCodesId() +
            ", codes='" + getCodesNome() + "'" +
            "}";
    }
}
