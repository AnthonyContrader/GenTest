package com.it.contrader.service.mapper;

import com.it.contrader.domain.*;
import com.it.contrader.service.dto.CodesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Codes and its DTO CodesDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgettiMapper.class})
public interface CodesMapper extends EntityMapper<CodesDTO, Codes> {

    @Mapping(source = "progetti.id", target = "progettiId")
    @Mapping(source = "progetti.nome", target = "progettiNome")
    CodesDTO toDto(Codes codes);

    @Mapping(source = "progettiId", target = "progetti")
    Codes toEntity(CodesDTO codesDTO);

    default Codes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Codes codes = new Codes();
        codes.setId(id);
        return codes;
    }
}
