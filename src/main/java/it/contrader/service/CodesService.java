package it.contrader.service;

import it.contrader.converter.CodesConverter;
import it.contrader.dao.CodesRepository;
import it.contrader.dto.CodesDTO;
import it.contrader.model.Codes;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class CodesService extends AbstractService<Codes, CodesDTO> {

    @Autowired
    private CodesConverter converter;
    @Autowired
    private CodesRepository repository;
}

