package com.it.contrader.service;

import com.it.contrader.service.dto.ProgettiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Progetti.
 */
public interface ProgettiService {

    /**
     * Save a progetti.
     *
     * @param progettiDTO the entity to save
     * @return the persisted entity
     */
    ProgettiDTO save(ProgettiDTO progettiDTO);

    /**
     * Get all the progettis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProgettiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" progetti.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProgettiDTO> findOne(Long id);

    /**
     * Delete the "id" progetti.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
