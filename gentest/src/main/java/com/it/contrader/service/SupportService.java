package com.it.contrader.service;

import com.it.contrader.service.dto.SupportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Support.
 */
public interface SupportService {

    /**
     * Save a support.
     *
     * @param supportDTO the entity to save
     * @return the persisted entity
     */
    SupportDTO save(SupportDTO supportDTO);

    /**
     * Get all the supports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SupportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" support.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SupportDTO> findOne(Long id);

    /**
     * Delete the "id" support.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
