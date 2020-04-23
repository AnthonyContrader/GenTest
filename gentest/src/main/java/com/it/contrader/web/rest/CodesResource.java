package com.it.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.it.contrader.service.CodesService;
import com.it.contrader.web.rest.errors.BadRequestAlertException;
import com.it.contrader.web.rest.util.HeaderUtil;
import com.it.contrader.web.rest.util.PaginationUtil;
import com.it.contrader.service.dto.CodesDTO;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Codes.
 */
@RestController
@RequestMapping("/api")
public class CodesResource {

    private final Logger log = LoggerFactory.getLogger(CodesResource.class);

    private static final String ENTITY_NAME = "codes";

    private final CodesService codesService;

    public CodesResource(CodesService codesService) {
        this.codesService = codesService;
    }

    /**
     * POST  /codes : Create a new codes.
     *
     * @param codesDTO the codesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new codesDTO, or with status 400 (Bad Request) if the codes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/codes")
    @Timed
    public ResponseEntity<CodesDTO> createCodes(@Valid @RequestBody CodesDTO codesDTO) throws URISyntaxException {
        log.debug("REST request to save Codes : {}", codesDTO);
        if (codesDTO.getId() != null) {
            throw new BadRequestAlertException("A new codes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodesDTO result = codesService.save(codesDTO);
        System.out.println("gatto");
        return ResponseEntity.created(new URI("/api/codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /codes : Updates an existing codes.
     *
     * @param codesDTO the codesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated codesDTO,
     * or with status 400 (Bad Request) if the codesDTO is not valid,
     * or with status 500 (Internal Server Error) if the codesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/codes")
    @Timed
    public ResponseEntity<CodesDTO> updateCodes(@Valid @RequestBody CodesDTO codesDTO) throws URISyntaxException {
        log.debug("REST request to update Codes : {}", codesDTO);
        if (codesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodesDTO result = codesService.save(codesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, codesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /codes : get all the codes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of codes in body
     */
    @GetMapping("/codes")
    @Timed
    public ResponseEntity<List<CodesDTO>> getAllCodes(Pageable pageable) {
        log.debug("REST request to get a page of Codes");
        Page<CodesDTO> page = codesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/codes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /codes/:id : get the "id" codes.
     *
     * @param id the id of the codesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the codesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/codes/{id}")
    @Timed
    public ResponseEntity<CodesDTO> getCodes(@PathVariable Long id) {
        log.debug("REST request to get Codes : {}", id);
        Optional<CodesDTO> codesDTO = codesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(codesDTO);
    }

    /**
     * DELETE  /codes/:id : delete the "id" codes.
     *
     * @param id the id of the codesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/codes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCodes(@PathVariable Long id) {
        log.debug("REST request to delete Codes : {}", id);
        codesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    String nome;
    @PostMapping(value = "/setnamep")
    @Timed
    public void setnamep (@RequestBody String nome){
        this.nome = nome;
        System.out.println("cane");
    }
    
    @PostMapping(value = "/uploadcontent")
    @Timed
    public void uploadcontent (@RequestBody String fileString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\MarioGiuliano\\workspace_contrader\\gentest-angular\\codes\\"+nome+".txt"));
        writer.write(fileString);
        writer.close();
        System.out.println("gatto");
    }
    /*@PostMapping(value="/codes")
    @Timed
    public void inseriscinome() {
    	System.out.println("gattoddd");
    }*/
}
