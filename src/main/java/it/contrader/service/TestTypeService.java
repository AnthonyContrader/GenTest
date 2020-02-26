package it.contrader.service;
import java.util.List;
import it.contrader.converter.TestTypeConverter;
import it.contrader.dao.TestTypeDAO;
import it.contrader.dto.TestTypeDTO;
import it.contrader.dto.UserDTO;

public class TestTypeService {
	private TestTypeDAO testTypeDAO;
	private TestTypeConverter testTypeConverter;
	
	public TestTypeService() {
		this.testTypeConverter = new TestTypeConverter();
		this.testTypeDAO = new TestTypeDAO();
	
	}
	public List<TestTypeDTO> getAll() {
		return testTypeConverter.toDTOList(testTypeDAO.getAll());
	}

	public TestTypeDTO read(int id) {
		return testTypeConverter.toDTO(testTypeDAO.read(id));
	}
	public boolean insert(TestTypeDTO dto) {
		return testTypeDAO.insert(testTypeConverter.toEntity(dto));
	}
	public boolean update(TestTypeDTO dto) {
		return testTypeDAO.update(testTypeConverter.toEntity(dto));
	}
	public boolean delete(int id) {
		return testTypeDAO.delete(id);
	}
}
