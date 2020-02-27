package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.TestTypeDTO;
import it.contrader.model.TestType;


public class TestTypeConverter {

	public TestTypeDTO toDTO(TestType testType) {
		TestTypeDTO testTypeDTO = new TestTypeDTO(testType.getId(),testType.getType_t(),testType.getDescrizione());
		return testTypeDTO;
	}
	public TestType toEntity(TestTypeDTO testTypeDTO) {
		TestType testType = new TestType(testTypeDTO.getId(),testTypeDTO.getType_t(),testTypeDTO.getDescrizione());
		return testType;
	}
	
	 public List<TestTypeDTO> toDTOList(List<TestType> testTypeList) {
		 List<TestTypeDTO> testTypeDTOList = new ArrayList<TestTypeDTO>();
		 for (TestType testType : testTypeList) {
			 testTypeDTOList.add(toDTO(testType));
		 }
		 return testTypeDTOList;
	 }
}
