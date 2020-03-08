package it.contrader.service;


import it.contrader.converter.TestConverter;
import it.contrader.dao.TestDAO;
import it.contrader.dto.TestDTO;
import it.contrader.model.Test;



public class TestService extends AbstractService <Test, TestDTO> {
    public TestService ()  {
        this.dao = new TestDAO ();
        this.converter = new TestConverter ();
    }
}
