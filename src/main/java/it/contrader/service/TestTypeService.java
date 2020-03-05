package it.contrader.service;

import it.contrader.converter.TestTypeConverter;
import it.contrader.dao.TestTypeDAO;
import it.contrader.dto.TestTypeDTO;
import it.contrader.model.TestType;

public class TestTypeService extends AbstractService<TestType, TestTypeDTO> {
	

	public TestTypeService(){
		this.dao = new TestTypeDAO();
		this.converter = new TestTypeConverter();
	}
	

}