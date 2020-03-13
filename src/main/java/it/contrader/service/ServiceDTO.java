package it.contrader.service;

import it.contrader.dto.UserDTO;

import java.util.List;

public interface ServiceDTO<DTO> {

	public List<DTO> getAll();

	public DTO read(long id);

	public DTO insert(UserDTO dto);

	public DTO update(DTO dto);

	public void delete(long id);

}
