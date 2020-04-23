package com.it.contrader.service.mapper;

import com.it.contrader.domain.*;
import com.it.contrader.service.dto.TestsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tests and its DTO TestsDTO.
 */
@Mapper(componentModel = "spring", uses = {CodesMapper.class})
public interface TestsMapper extends EntityMapper<TestsDTO, Tests> {

    @Mapping(source = "codes.id", target = "codesId")
    @Mapping(source = "codes.nome", target = "codesNome")
    TestsDTO toDto(Tests tests);

    @Mapping(source = "codesId", target = "codes")
    Tests toEntity(TestsDTO testsDTO);

    default Tests fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tests tests = new Tests();
        tests.setId(id);
        return tests;
    }
}
