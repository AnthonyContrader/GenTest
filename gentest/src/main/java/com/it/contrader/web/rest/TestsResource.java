package com.it.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.it.contrader.service.TestsService;
import com.it.contrader.web.rest.errors.BadRequestAlertException;
import com.it.contrader.web.rest.util.HeaderUtil;
import com.it.contrader.web.rest.util.PaginationUtil;
import com.it.contrader.service.dto.TestsDTO;
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
 * REST controller for managing Tests.
 */
@RestController
@RequestMapping("/api")
public class TestsResource {

    private final Logger log = LoggerFactory.getLogger(TestsResource.class);

    private static final String ENTITY_NAME = "tests";

    private final TestsService testsService;

    public TestsResource(TestsService testsService) {
        this.testsService = testsService;
    }

    /**
     * POST  /tests : Create a new tests.
     *
     * @param testsDTO the testsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new testsDTO, or with status 400 (Bad Request) if the tests has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tests")
    @Timed
    public ResponseEntity<TestsDTO> createTests(@Valid @RequestBody TestsDTO testsDTO) throws URISyntaxException {
        log.debug("REST request to save Tests : {}", testsDTO);
        if (testsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tests cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestsDTO result = testsService.save(testsDTO);
        return ResponseEntity.created(new URI("/api/tests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tests : Updates an existing tests.
     *
     * @param testsDTO the testsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated testsDTO,
     * or with status 400 (Bad Request) if the testsDTO is not valid,
     * or with status 500 (Internal Server Error) if the testsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tests")
    @Timed
    public ResponseEntity<TestsDTO> updateTests(@Valid @RequestBody TestsDTO testsDTO) throws URISyntaxException {
        log.debug("REST request to update Tests : {}", testsDTO);
        if (testsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestsDTO result = testsService.save(testsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, testsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tests : get all the tests.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tests in body
     */
    @GetMapping("/tests")
    @Timed
    public ResponseEntity<List<TestsDTO>> getAllTests(Pageable pageable) {
        log.debug("REST request to get a page of Tests");
        Page<TestsDTO> page = testsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tests");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tests/:id : get the "id" tests.
     *
     * @param id the id of the testsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the testsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tests/{id}")
    @Timed
    public ResponseEntity<TestsDTO> getTests(@PathVariable Long id) {
        log.debug("REST request to get Tests : {}", id);
        Optional<TestsDTO> testsDTO = testsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testsDTO);
    }

    /**
     * DELETE  /tests/:id : delete the "id" tests.
     *
     * @param id the id of the testsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tests/{id}")
    @Timed
    public ResponseEntity<Void> deleteTests(@PathVariable Long id) {
        log.debug("REST request to delete Tests : {}", id);
        testsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
