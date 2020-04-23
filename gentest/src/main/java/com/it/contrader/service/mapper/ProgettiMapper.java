package com.it.contrader.service.mapper;

import com.it.contrader.domain.*;
import com.it.contrader.service.dto.ProgettiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Progetti and its DTO ProgettiDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProgettiMapper extends EntityMapper<ProgettiDTO, Progetti> {


    @Mapping(target = "codes", ignore = true)
    Progetti toEntity(ProgettiDTO progettiDTO);

    default Progetti fromId(Long id) {
        if (id == null) {
            return null;
        }
        Progetti progetti = new Progetti();
        progetti.setId(id);
        return progetti;
    }
}
