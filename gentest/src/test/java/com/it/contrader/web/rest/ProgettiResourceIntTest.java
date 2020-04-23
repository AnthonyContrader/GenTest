package com.it.contrader.web.rest;

import com.it.contrader.GentestApp;

import com.it.contrader.domain.Progetti;
import com.it.contrader.repository.ProgettiRepository;
import com.it.contrader.service.ProgettiService;
import com.it.contrader.service.dto.ProgettiDTO;
import com.it.contrader.service.mapper.ProgettiMapper;
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
 * Test class for the ProgettiResource REST controller.
 *
 * @see ProgettiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GentestApp.class)
public class ProgettiResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private ProgettiRepository progettiRepository;


    @Autowired
    private ProgettiMapper progettiMapper;
    

    @Autowired
    private ProgettiService progettiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProgettiMockMvc;

    private Progetti progetti;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgettiResource progettiResource = new ProgettiResource(progettiService);
        this.restProgettiMockMvc = MockMvcBuilders.standaloneSetup(progettiResource)
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
    public static Progetti createEntity(EntityManager em) {
        Progetti progetti = new Progetti()
            .nome(DEFAULT_NOME);
        return progetti;
    }

    @Before
    public void initTest() {
        progetti = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgetti() throws Exception {
        int databaseSizeBeforeCreate = progettiRepository.findAll().size();

        // Create the Progetti
        ProgettiDTO progettiDTO = progettiMapper.toDto(progetti);
        restProgettiMockMvc.perform(post("/api/progettis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(progettiDTO)))
            .andExpect(status().isCreated());

        // Validate the Progetti in the database
        List<Progetti> progettiList = progettiRepository.findAll();
        assertThat(progettiList).hasSize(databaseSizeBeforeCreate + 1);
        Progetti testProgetti = progettiList.get(progettiList.size() - 1);
        assertThat(testProgetti.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createProgettiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = progettiRepository.findAll().size();

        // Create the Progetti with an existing ID
        progetti.setId(1L);
        ProgettiDTO progettiDTO = progettiMapper.toDto(progetti);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgettiMockMvc.perform(post("/api/progettis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(progettiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Progetti in the database
        List<Progetti> progettiList = progettiRepository.findAll();
        assertThat(progettiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = progettiRepository.findAll().size();
        // set the field null
        progetti.setNome(null);

        // Create the Progetti, which fails.
        ProgettiDTO progettiDTO = progettiMapper.toDto(progetti);

        restProgettiMockMvc.perform(post("/api/progettis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(progettiDTO)))
            .andExpect(status().isBadRequest());

        List<Progetti> progettiList = progettiRepository.findAll();
        assertThat(progettiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProgettis() throws Exception {
        // Initialize the database
        progettiRepository.saveAndFlush(progetti);

        // Get all the progettiList
        restProgettiMockMvc.perform(get("/api/progettis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progetti.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    

    @Test
    @Transactional
    public void getProgetti() throws Exception {
        // Initialize the database
        progettiRepository.saveAndFlush(progetti);

        // Get the progetti
        restProgettiMockMvc.perform(get("/api/progettis/{id}", progetti.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(progetti.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProgetti() throws Exception {
        // Get the progetti
        restProgettiMockMvc.perform(get("/api/progettis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgetti() throws Exception {
        // Initialize the database
        progettiRepository.saveAndFlush(progetti);

        int databaseSizeBeforeUpdate = progettiRepository.findAll().size();

        // Update the progetti
        Progetti updatedProgetti = progettiRepository.findById(progetti.getId()).get();
        // Disconnect from session so that the updates on updatedProgetti are not directly saved in db
        em.detach(updatedProgetti);
        updatedProgetti
            .nome(UPDATED_NOME);
        ProgettiDTO progettiDTO = progettiMapper.toDto(updatedProgetti);

        restProgettiMockMvc.perform(put("/api/progettis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(progettiDTO)))
            .andExpect(status().isOk());

        // Validate the Progetti in the database
        List<Progetti> progettiList = progettiRepository.findAll();
        assertThat(progettiList).hasSize(databaseSizeBeforeUpdate);
        Progetti testProgetti = progettiList.get(progettiList.size() - 1);
        assertThat(testProgetti.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingProgetti() throws Exception {
        int databaseSizeBeforeUpdate = progettiRepository.findAll().size();

        // Create the Progetti
        ProgettiDTO progettiDTO = progettiMapper.toDto(progetti);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restProgettiMockMvc.perform(put("/api/progettis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(progettiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Progetti in the database
        List<Progetti> progettiList = progettiRepository.findAll();
        assertThat(progettiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProgetti() throws Exception {
        // Initialize the database
        progettiRepository.saveAndFlush(progetti);

        int databaseSizeBeforeDelete = progettiRepository.findAll().size();

        // Get the progetti
        restProgettiMockMvc.perform(delete("/api/progettis/{id}", progetti.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Progetti> progettiList = progettiRepository.findAll();
        assertThat(progettiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progetti.class);
        Progetti progetti1 = new Progetti();
        progetti1.setId(1L);
        Progetti progetti2 = new Progetti();
        progetti2.setId(progetti1.getId());
        assertThat(progetti1).isEqualTo(progetti2);
        progetti2.setId(2L);
        assertThat(progetti1).isNotEqualTo(progetti2);
        progetti1.setId(null);
        assertThat(progetti1).isNotEqualTo(progetti2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgettiDTO.class);
        ProgettiDTO progettiDTO1 = new ProgettiDTO();
        progettiDTO1.setId(1L);
        ProgettiDTO progettiDTO2 = new ProgettiDTO();
        assertThat(progettiDTO1).isNotEqualTo(progettiDTO2);
        progettiDTO2.setId(progettiDTO1.getId());
        assertThat(progettiDTO1).isEqualTo(progettiDTO2);
        progettiDTO2.setId(2L);
        assertThat(progettiDTO1).isNotEqualTo(progettiDTO2);
        progettiDTO1.setId(null);
        assertThat(progettiDTO1).isNotEqualTo(progettiDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(progettiMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(progettiMapper.fromId(null)).isNull();
    }
}
