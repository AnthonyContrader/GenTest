package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;


import it.contrader.dto.TestDTO;
import it.contrader.model.Test;


public class TestConverter implements Converter<Test, TestDTO> {


    @Override
    public TestDTO toDTO(Test test) {
        TestDTO testDTO = new TestDTO(test.getId(), test.getNome(), test.getData_i(), test.getData_m(), test.getType_t()) ;
        return testDTO;
    }




    @Override
    public Test toEntity(TestDTO testDTO) {
        Test test = new Test( testDTO.getId(), testDTO.getNome(), testDTO.getData_i(), testDTO.getData_m(), testDTO.getType_t());
        return test;
    }

    public List<TestDTO> toDTOList(List<Test> testList) {
        List<TestDTO> testDTOList = new ArrayList<TestDTO>();

        for(Test test : testList) {
            testDTOList.add(toDTO(test));
        }
        return testDTOList;
    }

}
