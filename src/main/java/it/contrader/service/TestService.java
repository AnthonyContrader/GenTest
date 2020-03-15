package it.contrader.service;

import it.contrader.converter.TestConverter;
import it.contrader.dao.TestRepository;
import it.contrader.dto.TestDTO;
import it.contrader.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService extends AbstractService<Test, TestDTO> {

    @Autowired
    private TestConverter converter;
    @Autowired
    private TestRepository repository;

    public TestDTO findByNome(String nome) {
        return converter.toDTO( repository.findByNome(nome) );
    }
}
