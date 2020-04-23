package com.it.contrader.service;

import com.it.contrader.service.dto.TestsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Tests.
 */
public interface TestsService {

    /**
     * Save a tests.
     *
     * @param testsDTO the entity to save
     * @return the persisted entity
     */
    TestsDTO save(TestsDTO testsDTO);

    /**
     * Get all the tests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TestsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tests.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TestsDTO> findOne(Long id);

    /**
     * Delete the "id" tests.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
