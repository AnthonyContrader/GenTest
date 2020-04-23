package com.it.contrader.service.impl;

import com.it.contrader.service.ProgettiService;
import com.it.contrader.domain.Progetti;
import com.it.contrader.repository.ProgettiRepository;
import com.it.contrader.service.dto.ProgettiDTO;
import com.it.contrader.service.mapper.ProgettiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Progetti.
 */
@Service
@Transactional
public class ProgettiServiceImpl implements ProgettiService {

    private final Logger log = LoggerFactory.getLogger(ProgettiServiceImpl.class);

    private final ProgettiRepository progettiRepository;

    private final ProgettiMapper progettiMapper;

    public ProgettiServiceImpl(ProgettiRepository progettiRepository, ProgettiMapper progettiMapper) {
        this.progettiRepository = progettiRepository;
        this.progettiMapper = progettiMapper;
    }

    /**
     * Save a progetti.
     *
     * @param progettiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProgettiDTO save(ProgettiDTO progettiDTO) {
        log.debug("Request to save Progetti : {}", progettiDTO);
        Progetti progetti = progettiMapper.toEntity(progettiDTO);
        progetti = progettiRepository.save(progetti);
        return progettiMapper.toDto(progetti);
    }

    /**
     * Get all the progettis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProgettiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Progettis");
        return progettiRepository.findAll(pageable)
            .map(progettiMapper::toDto);
    }


    /**
     * Get one progetti by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProgettiDTO> findOne(Long id) {
        log.debug("Request to get Progetti : {}", id);
        return progettiRepository.findById(id)
            .map(progettiMapper::toDto);
    }

    /**
     * Delete the progetti by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Progetti : {}", id);
        progettiRepository.deleteById(id);
    }
}
