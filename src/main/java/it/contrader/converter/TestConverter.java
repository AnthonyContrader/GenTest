package it.contrader.converter;
import it.contrader.dto.TestDTO;
import it.contrader.model.Test;
import org.springframework.stereotype.Component;



@Component
public class TestConverter extends AbstractConverter<Test, TestDTO> {

    @Override
    public Test toEntity(TestDTO testDTO) {
        Test test = null;
        if (testDTO != null) {
            test = new Test(testDTO.getId(), testDTO.getNome(), testDTO.getData_i(), testDTO.getData_m(), testDTO.getType_t(), testDTO.getCodes());
        }
        return test;
    }

    @Override
    public TestDTO toDTO(Test test) {
        TestDTO testDTO = null;
        if (test != null) {
            testDTO = new TestDTO(test.getId(), test.getNome(), test.getData_i(), test.getData_m(), test.getType_t(), test.getCodes());

        }
        return testDTO;
    }
}