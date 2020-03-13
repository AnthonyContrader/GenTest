package it.contrader.service;

import java.util.List;

import it.contrader.dto.CodesDTO;
import it.contrader.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import it.contrader.converter.Converter;


@Service
public abstract class AbstractService<Entity, DTO> implements ServiceDTO<DTO> {

	@Autowired
	protected CrudRepository<Entity, Long> crudRepository;
	@Autowired
	protected Converter<Entity, DTO> converter;

	public AbstractService() {
	}

	@Override
	public DTO insert(UserDTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity((DTO) dto)));
	}
	public DTO insertc(CodesDTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity((DTO) dto)));
	}

	@Override
	public List<DTO> getAll() {
		return converter.toDTOList((crudRepository.findAll()));
	}

	@Override
	public DTO read(long id) {
		return converter.toDTO(crudRepository.findById(id).get());
	}

	@Override
	public DTO update(DTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity(dto)));
	}

	@Override
	public void delete(long id) {
		crudRepository.deleteById(id);
	}

}