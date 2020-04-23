package com.it.contrader.service;

import com.it.contrader.service.dto.CodesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Codes.
 */
public interface CodesService {

    /**
     * Save a codes.
     *
     * @param codesDTO the entity to save
     * @return the persisted entity
     */
    CodesDTO save(CodesDTO codesDTO);

    /**
     * Get all the codes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CodesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" codes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CodesDTO> findOne(Long id);

    /**
     * Delete the "id" codes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
