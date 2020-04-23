package com.it.contrader.service.impl;

import com.it.contrader.service.CodesService;
import com.it.contrader.domain.Codes;
import com.it.contrader.repository.CodesRepository;
import com.it.contrader.service.dto.CodesDTO;
import com.it.contrader.service.mapper.CodesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
/**
 * Service Implementation for managing Codes.
 */
@Service
@Transactional
public class CodesServiceImpl implements CodesService {

    private final Logger log = LoggerFactory.getLogger(CodesServiceImpl.class);

    private final CodesRepository codesRepository;

    private final CodesMapper codesMapper;

    public CodesServiceImpl(CodesRepository codesRepository, CodesMapper codesMapper) {
        this.codesRepository = codesRepository;
        this.codesMapper = codesMapper;
    }

    /**
     * Save a codes.
     *
     * @param codesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CodesDTO save(CodesDTO codesDTO) {
        log.debug("Request to save Codes : {}", codesDTO);
        Codes codes = codesMapper.toEntity(codesDTO);
        codes = codesRepository.save(codes);
        return codesMapper.toDto(codes);
    }

    /**
     * Get all the codes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CodesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Codes");
        return codesRepository.findAll(pageable)
            .map(codesMapper::toDto);
    }


    /**
     * Get one codes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CodesDTO> findOne(Long id) {
        log.debug("Request to get Codes : {}", id);
        return codesRepository.findById(id)
            .map(codesMapper::toDto);
    }

    /**
     * Delete the codes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Codes : {}", id);
        codesRepository.deleteById(id);
    }
    
   
}
