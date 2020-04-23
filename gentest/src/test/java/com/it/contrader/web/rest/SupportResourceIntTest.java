package com.it.contrader.web.rest;

import com.it.contrader.GentestApp;

import com.it.contrader.domain.Support;
import com.it.contrader.repository.SupportRepository;
import com.it.contrader.service.SupportService;
import com.it.contrader.service.dto.SupportDTO;
import com.it.contrader.service.mapper.SupportMapper;
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
 * Test class for the SupportResource REST controller.
 *
 * @see SupportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GentestApp.class)
public class SupportResourceIntTest {

    private static final String DEFAULT_DOMANDA = "AAAAAAAAAA";
    private static final String UPDATED_DOMANDA = "BBBBBBBBBB";

    private static final String DEFAULT_RISPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_RISPOSTA = "BBBBBBBBBB";

    @Autowired
    private SupportRepository supportRepository;


    @Autowired
    private SupportMapper supportMapper;
    

    @Autowired
    private SupportService supportService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupportMockMvc;

    private Support support;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupportResource supportResource = new SupportResource(supportService);
        this.restSupportMockMvc = MockMvcBuilders.standaloneSetup(supportResource)
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
    public static Support createEntity(EntityManager em) {
        Support support = new Support()
            .domanda(DEFAULT_DOMANDA)
            .risposta(DEFAULT_RISPOSTA);
        return support;
    }

    @Before
    public void initTest() {
        support = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupport() throws Exception {
        int databaseSizeBeforeCreate = supportRepository.findAll().size();

        // Create the Support
        SupportDTO supportDTO = supportMapper.toDto(support);
        restSupportMockMvc.perform(post("/api/supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportDTO)))
            .andExpect(status().isCreated());

        // Validate the Support in the database
        List<Support> supportList = supportRepository.findAll();
        assertThat(supportList).hasSize(databaseSizeBeforeCreate + 1);
        Support testSupport = supportList.get(supportList.size() - 1);
        assertThat(testSupport.getDomanda()).isEqualTo(DEFAULT_DOMANDA);
        assertThat(testSupport.getRisposta()).isEqualTo(DEFAULT_RISPOSTA);
    }

    @Test
    @Transactional
    public void createSupportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supportRepository.findAll().size();

        // Create the Support with an existing ID
        support.setId(1L);
        SupportDTO supportDTO = supportMapper.toDto(support);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupportMockMvc.perform(post("/api/supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Support in the database
        List<Support> supportList = supportRepository.findAll();
        assertThat(supportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSupports() throws Exception {
        // Initialize the database
        supportRepository.saveAndFlush(support);

        // Get all the supportList
        restSupportMockMvc.perform(get("/api/supports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(support.getId().intValue())))
            .andExpect(jsonPath("$.[*].domanda").value(hasItem(DEFAULT_DOMANDA.toString())))
            .andExpect(jsonPath("$.[*].risposta").value(hasItem(DEFAULT_RISPOSTA.toString())));
    }
    

    @Test
    @Transactional
    public void getSupport() throws Exception {
        // Initialize the database
        supportRepository.saveAndFlush(support);

        // Get the support
        restSupportMockMvc.perform(get("/api/supports/{id}", support.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(support.getId().intValue()))
            .andExpect(jsonPath("$.domanda").value(DEFAULT_DOMANDA.toString()))
            .andExpect(jsonPath("$.risposta").value(DEFAULT_RISPOSTA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSupport() throws Exception {
        // Get the support
        restSupportMockMvc.perform(get("/api/supports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupport() throws Exception {
        // Initialize the database
        supportRepository.saveAndFlush(support);

        int databaseSizeBeforeUpdate = supportRepository.findAll().size();

        // Update the support
        Support updatedSupport = supportRepository.findById(support.getId()).get();
        // Disconnect from session so that the updates on updatedSupport are not directly saved in db
        em.detach(updatedSupport);
        updatedSupport
            .domanda(UPDATED_DOMANDA)
            .risposta(UPDATED_RISPOSTA);
        SupportDTO supportDTO = supportMapper.toDto(updatedSupport);

        restSupportMockMvc.perform(put("/api/supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportDTO)))
            .andExpect(status().isOk());

        // Validate the Support in the database
        List<Support> supportList = supportRepository.findAll();
        assertThat(supportList).hasSize(databaseSizeBeforeUpdate);
        Support testSupport = supportList.get(supportList.size() - 1);
        assertThat(testSupport.getDomanda()).isEqualTo(UPDATED_DOMANDA);
        assertThat(testSupport.getRisposta()).isEqualTo(UPDATED_RISPOSTA);
    }

    @Test
    @Transactional
    public void updateNonExistingSupport() throws Exception {
        int databaseSizeBeforeUpdate = supportRepository.findAll().size();

        // Create the Support
        SupportDTO supportDTO = supportMapper.toDto(support);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restSupportMockMvc.perform(put("/api/supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Support in the database
        List<Support> supportList = supportRepository.findAll();
        assertThat(supportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSupport() throws Exception {
        // Initialize the database
        supportRepository.saveAndFlush(support);

        int databaseSizeBeforeDelete = supportRepository.findAll().size();

        // Get the support
        restSupportMockMvc.perform(delete("/api/supports/{id}", support.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Support> supportList = supportRepository.findAll();
        assertThat(supportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Support.class);
        Support support1 = new Support();
        support1.setId(1L);
        Support support2 = new Support();
        support2.setId(support1.getId());
        assertThat(support1).isEqualTo(support2);
        support2.setId(2L);
        assertThat(support1).isNotEqualTo(support2);
        support1.setId(null);
        assertThat(support1).isNotEqualTo(support2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupportDTO.class);
        SupportDTO supportDTO1 = new SupportDTO();
        supportDTO1.setId(1L);
        SupportDTO supportDTO2 = new SupportDTO();
        assertThat(supportDTO1).isNotEqualTo(supportDTO2);
        supportDTO2.setId(supportDTO1.getId());
        assertThat(supportDTO1).isEqualTo(supportDTO2);
        supportDTO2.setId(2L);
        assertThat(supportDTO1).isNotEqualTo(supportDTO2);
        supportDTO1.setId(null);
        assertThat(supportDTO1).isNotEqualTo(supportDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(supportMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(supportMapper.fromId(null)).isNull();
    }
}
