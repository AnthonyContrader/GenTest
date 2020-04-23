package com.it.contrader.service.mapper;

import com.it.contrader.domain.*;
import com.it.contrader.service.dto.SupportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Support and its DTO SupportDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SupportMapper extends EntityMapper<SupportDTO, Support> {



    default Support fromId(Long id) {
        if (id == null) {
            return null;
        }
        Support support = new Support();
        support.setId(id);
        return support;
    }
}
