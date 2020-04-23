package com.it.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.it.contrader.service.ProgettiService;
import com.it.contrader.web.rest.errors.BadRequestAlertException;
import com.it.contrader.web.rest.util.HeaderUtil;
import com.it.contrader.web.rest.util.PaginationUtil;
import com.it.contrader.service.dto.ProgettiDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Progetti.
 */
@RestController
@RequestMapping("/api")
public class ProgettiResource {

    private final Logger log = LoggerFactory.getLogger(ProgettiResource.class);

    private static final String ENTITY_NAME = "progetti";

    private final ProgettiService progettiService;

    public ProgettiResource(ProgettiService progettiService) {
        this.progettiService = progettiService;
    }

    /**
     * POST  /progettis : Create a new progetti.
     *
     * @param progettiDTO the progettiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new progettiDTO, or with status 400 (Bad Request) if the progetti has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/progettis")
    @Timed
    public ResponseEntity<ProgettiDTO> createProgetti(@Valid @RequestBody ProgettiDTO progettiDTO) throws URISyntaxException {
        log.debug("REST request to save Progetti : {}", progettiDTO);
        if (progettiDTO.getId() != null) {
            throw new BadRequestAlertException("A new progetti cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgettiDTO result = progettiService.save(progettiDTO);
        return ResponseEntity.created(new URI("/api/progettis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /progettis : Updates an existing progetti.
     *
     * @param progettiDTO the progettiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated progettiDTO,
     * or with status 400 (Bad Request) if the progettiDTO is not valid,
     * or with status 500 (Internal Server Error) if the progettiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/progettis")
    @Timed
    public ResponseEntity<ProgettiDTO> updateProgetti(@Valid @RequestBody ProgettiDTO progettiDTO) throws URISyntaxException {
        log.debug("REST request to update Progetti : {}", progettiDTO);
        if (progettiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProgettiDTO result = progettiService.save(progettiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, progettiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /progettis : get all the progettis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of progettis in body
     */
    @GetMapping("/progettis")
    @Timed
    public ResponseEntity<List<ProgettiDTO>> getAllProgettis(Pageable pageable) {
        log.debug("REST request to get a page of Progettis");
        Page<ProgettiDTO> page = progettiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/progettis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /progettis/:id : get the "id" progetti.
     *
     * @param id the id of the progettiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the progettiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/progettis/{id}")
    @Timed
    public ResponseEntity<ProgettiDTO> getProgetti(@PathVariable Long id) {
        log.debug("REST request to get Progetti : {}", id);
        Optional<ProgettiDTO> progettiDTO = progettiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(progettiDTO);
    }

    /**
     * DELETE  /progettis/:id : delete the "id" progetti.
     *
     * @param id the id of the progettiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/progettis/{id}")
    @Timed
    public ResponseEntity<Void> deleteProgetti(@PathVariable Long id) {
        log.debug("REST request to delete Progetti : {}", id);
        progettiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
