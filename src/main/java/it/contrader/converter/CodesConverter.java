package it.contrader.converter;

import it.contrader.dto.CodesDTO;
import it.contrader.model.Codes;
import org.springframework.stereotype.Component;



import it.contrader.model.User;

@Component
public class CodesConverter extends AbstractConverter<Codes, CodesDTO> {

    @Override
    public Codes toEntity(CodesDTO codesDTO) {
        Codes codes = null;
        if (codesDTO != null) {
            codes = new Codes(codesDTO.getId(), codesDTO.getNome(), codesDTO.getData_i(), codesDTO.getData_m(), codesDTO.getType_t(), codesDTO.getTest());
        }
        return codes;
    }

    @Override
    public CodesDTO toDTO(Codes codes) {
        CodesDTO codesDTO = null;
        if (codes != null) {
            codesDTO = new CodesDTO(codes.getId(), codes.getNome(), codes.getData_i(), codes.getData_m(), codes.getType_t(), codes.getTest());

        }
        return codesDTO;
    }
}