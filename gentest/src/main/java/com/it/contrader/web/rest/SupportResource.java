package com.it.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.it.contrader.service.SupportService;
import com.it.contrader.web.rest.errors.BadRequestAlertException;
import com.it.contrader.web.rest.util.HeaderUtil;
import com.it.contrader.web.rest.util.PaginationUtil;
import com.it.contrader.service.dto.SupportDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Support.
 */
@RestController
@RequestMapping("/api")
public class SupportResource {

    private final Logger log = LoggerFactory.getLogger(SupportResource.class);

    private static final String ENTITY_NAME = "support";

    private final SupportService supportService;

    public SupportResource(SupportService supportService) {
        this.supportService = supportService;
    }

    /**
     * POST  /supports : Create a new support.
     *
     * @param supportDTO the supportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new supportDTO, or with status 400 (Bad Request) if the support has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/supports")
    @Timed
    public ResponseEntity<SupportDTO> createSupport(@RequestBody SupportDTO supportDTO) throws URISyntaxException {
        log.debug("REST request to save Support : {}", supportDTO);
        if (supportDTO.getId() != null) {
            throw new BadRequestAlertException("A new support cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupportDTO result = supportService.save(supportDTO);
        return ResponseEntity.created(new URI("/api/supports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /supports : Updates an existing support.
     *
     * @param supportDTO the supportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated supportDTO,
     * or with status 400 (Bad Request) if the supportDTO is not valid,
     * or with status 500 (Internal Server Error) if the supportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/supports")
    @Timed
    public ResponseEntity<SupportDTO> updateSupport(@RequestBody SupportDTO supportDTO) throws URISyntaxException {
        log.debug("REST request to update Support : {}", supportDTO);
        if (supportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SupportDTO result = supportService.save(supportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, supportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /supports : get all the supports.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of supports in body
     */
    @GetMapping("/supports")
    @Timed
    public ResponseEntity<List<SupportDTO>> getAllSupports(Pageable pageable) {
        log.debug("REST request to get a page of Supports");
        Page<SupportDTO> page = supportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/supports");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /supports/:id : get the "id" support.
     *
     * @param id the id of the supportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the supportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/supports/{id}")
    @Timed
    public ResponseEntity<SupportDTO> getSupport(@PathVariable Long id) {
        log.debug("REST request to get Support : {}", id);
        Optional<SupportDTO> supportDTO = supportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supportDTO);
    }

    /**
     * DELETE  /supports/:id : delete the "id" support.
     *
     * @param id the id of the supportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/supports/{id}")
    @Timed
    public ResponseEntity<Void> deleteSupport(@PathVariable Long id) {
        log.debug("REST request to delete Support : {}", id);
        supportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
