package com.it.contrader.web.rest;

import com.it.contrader.GentestApp;

import com.it.contrader.domain.Codes;
import com.it.contrader.repository.CodesRepository;
import com.it.contrader.service.CodesService;
import com.it.contrader.service.dto.CodesDTO;
import com.it.contrader.service.mapper.CodesMapper;
import com.it.contrader.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.it.contrader.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CodesResource REST controller.
 *
 * @see CodesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GentestApp.class)
public class CodesResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_I = "AAAAAAAAAA";
    private static final String UPDATED_DATA_I = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_M = "AAAAAAAAAA";
    private static final String UPDATED_DATA_M = "BBBBBBBBBB";

    @Autowired
    private CodesRepository codesRepository;


    @Autowired
    private CodesMapper codesMapper;
    

    @Autowired
    private CodesService codesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCodesMockMvc;

    private Codes codes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CodesResource codesResource = new CodesResource(codesService);
        this.restCodesMockMvc = MockMvcBuilders.standaloneSetup(codesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Codes createEntity(EntityManager em) {
        Codes codes = new Codes()
            .nome(DEFAULT_NOME)
            .data_i(DEFAULT_DATA_I)
            .data_m(DEFAULT_DATA_M);
        return codes;
    }

    @Before
    public void initTest() {
        codes = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodes() throws Exception {
        int databaseSizeBeforeCreate = codesRepository.findAll().size();

        // Create the Codes
        CodesDTO codesDTO = codesMapper.toDto(codes);
        restCodesMockMvc.perform(post("/api/codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codesDTO)))
            .andExpect(status().isCreated());

        // Validate the Codes in the database
        List<Codes> codesList = codesRepository.findAll();
        assertThat(codesList).hasSize(databaseSizeBeforeCreate + 1);
        Codes testCodes = codesList.get(codesList.size() - 1);
        assertThat(testCodes.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCodes.getData_i()).isEqualTo(DEFAULT_DATA_I);
        assertThat(testCodes.getData_m()).isEqualTo(DEFAULT_DATA_M);
    }

    @Test
    @Transactional
    public void createCodesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codesRepository.findAll().size();

        // Create the Codes with an existing ID
        codes.setId(1L);
        CodesDTO codesDTO = codesMapper.toDto(codes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodesMockMvc.perform(post("/api/codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Codes in the database
        List<Codes> codesList = codesRepository.findAll();
        assertThat(codesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = codesRepository.findAll().size();
        // set the field null
        codes.setNome(null);

        // Create the Codes, which fails.
        CodesDTO codesDTO = codesMapper.toDto(codes);

        restCodesMockMvc.perform(post("/api/codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codesDTO)))
            .andExpect(status().isBadRequest());

        List<Codes> codesList = codesRepository.findAll();
        assertThat(codesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCodes() throws Exception {
        // Initialize the database
        codesRepository.saveAndFlush(codes);

        // Get all the codesList
        restCodesMockMvc.perform(get("/api/codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codes.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].data_i").value(hasItem(DEFAULT_DATA_I.toString())))
            .andExpect(jsonPath("$.[*].data_m").value(hasItem(DEFAULT_DATA_M.toString())));
    }
    

    @Test
    @Transactional
    public void getCodes() throws Exception {
        // Initialize the database
        codesRepository.saveAndFlush(codes);

        // Get the codes
        restCodesMockMvc.perform(get("/api/codes/{id}", codes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(codes.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.data_i").value(DEFAULT_DATA_I.toString()))
            .andExpect(jsonPath("$.data_m").value(DEFAULT_DATA_M.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCodes() throws Exception {
        // Get the codes
        restCodesMockMvc.perform(get("/api/codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodes() throws Exception {
        // Initialize the database
        codesRepository.saveAndFlush(codes);

        int databaseSizeBeforeUpdate = codesRepository.findAll().size();

        // Update the codes
        Codes updatedCodes = codesRepository.findById(codes.getId()).get();
        // Disconnect from session so that the updates on updatedCodes are not directly saved in db
        em.detach(updatedCodes);
        updatedCodes
            .nome(UPDATED_NOME)
            .data_i(UPDATED_DATA_I)
            .data_m(UPDATED_DATA_M);
        CodesDTO codesDTO = codesMapper.toDto(updatedCodes);

        restCodesMockMvc.perform(put("/api/codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codesDTO)))
            .andExpect(status().isOk());

        // Validate the Codes in the database
        List<Codes> codesList = codesRepository.findAll();
        assertThat(codesList).hasSize(databaseSizeBeforeUpdate);
        Codes testCodes = codesList.get(codesList.size() - 1);
        assertThat(testCodes.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCodes.getData_i()).isEqualTo(UPDATED_DATA_I);
        assertThat(testCodes.getData_m()).isEqualTo(UPDATED_DATA_M);
    }

    @Test
    @Transactional
    public void updateNonExistingCodes() throws Exception {
        int databaseSizeBeforeUpdate = codesRepository.findAll().size();

        // Create the Codes
        CodesDTO codesDTO = codesMapper.toDto(codes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCodesMockMvc.perform(put("/api/codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Codes in the database
        List<Codes> codesList = codesRepository.findAll();
        assertThat(codesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodes() throws Exception {
        // Initialize the database
        codesRepository.saveAndFlush(codes);

        int databaseSizeBeforeDelete = codesRepository.findAll().size();

        // Get the codes
        restCodesMockMvc.perform(delete("/api/codes/{id}", codes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Codes> codesList = codesRepository.findAll();
        assertThat(codesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Codes.class);
        Codes codes1 = new Codes();
        codes1.setId(1L);
        Codes codes2 = new Codes();
        codes2.setId(codes1.getId());
        assertThat(codes1).isEqualTo(codes2);
        codes2.setId(2L);
        assertThat(codes1).isNotEqualTo(codes2);
        codes1.setId(null);
        assertThat(codes1).isNotEqualTo(codes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodesDTO.class);
        CodesDTO codesDTO1 = new CodesDTO();
        codesDTO1.setId(1L);
        CodesDTO codesDTO2 = new CodesDTO();
        assertThat(codesDTO1).isNotEqualTo(codesDTO2);
        codesDTO2.setId(codesDTO1.getId());
        assertThat(codesDTO1).isEqualTo(codesDTO2);
        codesDTO2.setId(2L);
        assertThat(codesDTO1).isNotEqualTo(codesDTO2);
        codesDTO1.setId(null);
        assertThat(codesDTO1).isNotEqualTo(codesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(codesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(codesMapper.fromId(null)).isNull();
    }
}
