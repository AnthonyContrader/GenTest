package com.it.contrader.service.impl;

import com.it.contrader.service.SupportService;
import com.it.contrader.domain.Support;
import com.it.contrader.repository.SupportRepository;
import com.it.contrader.service.dto.SupportDTO;
import com.it.contrader.service.mapper.SupportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Support.
 */
@Service
@Transactional
public class SupportServiceImpl implements SupportService {

    private final Logger log = LoggerFactory.getLogger(SupportServiceImpl.class);

    private final SupportRepository supportRepository;

    private final SupportMapper supportMapper;

    public SupportServiceImpl(SupportRepository supportRepository, SupportMapper supportMapper) {
        this.supportRepository = supportRepository;
        this.supportMapper = supportMapper;
    }

    /**
     * Save a support.
     *
     * @param supportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SupportDTO save(SupportDTO supportDTO) {
        log.debug("Request to save Support : {}", supportDTO);
        Support support = supportMapper.toEntity(supportDTO);
        support = supportRepository.save(support);
        return supportMapper.toDto(support);
    }

    /**
     * Get all the supports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SupportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Supports");
        return supportRepository.findAll(pageable)
            .map(supportMapper::toDto);
    }


    /**
     * Get one support by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SupportDTO> findOne(Long id) {
        log.debug("Request to get Support : {}", id);
        return supportRepository.findById(id)
            .map(supportMapper::toDto);
    }

    /**
     * Delete the support by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Support : {}", id);
        supportRepository.deleteById(id);
    }
}
