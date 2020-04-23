package com.it.contrader.web.rest;

import com.it.contrader.GentestApp;

import com.it.contrader.domain.Tests;
import com.it.contrader.repository.TestsRepository;
import com.it.contrader.service.TestsService;
import com.it.contrader.service.dto.TestsDTO;
import com.it.contrader.service.mapper.TestsMapper;
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
 * Test class for the TestsResource REST controller.
 *
 * @see TestsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GentestApp.class)
public class TestsResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_I = "AAAAAAAAAA";
    private static final String UPDATED_DATA_I = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_M = "AAAAAAAAAA";
    private static final String UPDATED_DATA_M = "BBBBBBBBBB";

    @Autowired
    private TestsRepository testsRepository;


    @Autowired
    private TestsMapper testsMapper;
    

    @Autowired
    private TestsService testsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTestsMockMvc;

    private Tests tests;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TestsResource testsResource = new TestsResource(testsService);
        this.restTestsMockMvc = MockMvcBuilders.standaloneSetup(testsResource)
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
    public static Tests createEntity(EntityManager em) {
        Tests tests = new Tests()
            .nome(DEFAULT_NOME)
            .data_i(DEFAULT_DATA_I)
            .data_m(DEFAULT_DATA_M);
        return tests;
    }

    @Before
    public void initTest() {
        tests = createEntity(em);
    }

    @Test
    @Transactional
    public void createTests() throws Exception {
        int databaseSizeBeforeCreate = testsRepository.findAll().size();

        // Create the Tests
        TestsDTO testsDTO = testsMapper.toDto(tests);
        restTestsMockMvc.perform(post("/api/tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testsDTO)))
            .andExpect(status().isCreated());

        // Validate the Tests in the database
        List<Tests> testsList = testsRepository.findAll();
        assertThat(testsList).hasSize(databaseSizeBeforeCreate + 1);
        Tests testTests = testsList.get(testsList.size() - 1);
        assertThat(testTests.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTests.getData_i()).isEqualTo(DEFAULT_DATA_I);
        assertThat(testTests.getData_m()).isEqualTo(DEFAULT_DATA_M);
    }

    @Test
    @Transactional
    public void createTestsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testsRepository.findAll().size();

        // Create the Tests with an existing ID
        tests.setId(1L);
        TestsDTO testsDTO = testsMapper.toDto(tests);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestsMockMvc.perform(post("/api/tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tests in the database
        List<Tests> testsList = testsRepository.findAll();
        assertThat(testsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = testsRepository.findAll().size();
        // set the field null
        tests.setNome(null);

        // Create the Tests, which fails.
        TestsDTO testsDTO = testsMapper.toDto(tests);

        restTestsMockMvc.perform(post("/api/tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testsDTO)))
            .andExpect(status().isBadRequest());

        List<Tests> testsList = testsRepository.findAll();
        assertThat(testsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTests() throws Exception {
        // Initialize the database
        testsRepository.saveAndFlush(tests);

        // Get all the testsList
        restTestsMockMvc.perform(get("/api/tests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tests.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].data_i").value(hasItem(DEFAULT_DATA_I.toString())))
            .andExpect(jsonPath("$.[*].data_m").value(hasItem(DEFAULT_DATA_M.toString())));
    }
    

    @Test
    @Transactional
    public void getTests() throws Exception {
        // Initialize the database
        testsRepository.saveAndFlush(tests);

        // Get the tests
        restTestsMockMvc.perform(get("/api/tests/{id}", tests.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tests.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.data_i").value(DEFAULT_DATA_I.toString()))
            .andExpect(jsonPath("$.data_m").value(DEFAULT_DATA_M.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTests() throws Exception {
        // Get the tests
        restTestsMockMvc.perform(get("/api/tests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTests() throws Exception {
        // Initialize the database
        testsRepository.saveAndFlush(tests);

        int databaseSizeBeforeUpdate = testsRepository.findAll().size();

        // Update the tests
        Tests updatedTests = testsRepository.findById(tests.getId()).get();
        // Disconnect from session so that the updates on updatedTests are not directly saved in db
        em.detach(updatedTests);
        updatedTests
            .nome(UPDATED_NOME)
            .data_i(UPDATED_DATA_I)
            .data_m(UPDATED_DATA_M);
        TestsDTO testsDTO = testsMapper.toDto(updatedTests);

        restTestsMockMvc.perform(put("/api/tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testsDTO)))
            .andExpect(status().isOk());

        // Validate the Tests in the database
        List<Tests> testsList = testsRepository.findAll();
        assertThat(testsList).hasSize(databaseSizeBeforeUpdate);
        Tests testTests = testsList.get(testsList.size() - 1);
        assertThat(testTests.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTests.getData_i()).isEqualTo(UPDATED_DATA_I);
        assertThat(testTests.getData_m()).isEqualTo(UPDATED_DATA_M);
    }

    @Test
    @Transactional
    public void updateNonExistingTests() throws Exception {
        int databaseSizeBeforeUpdate = testsRepository.findAll().size();

        // Create the Tests
        TestsDTO testsDTO = testsMapper.toDto(tests);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restTestsMockMvc.perform(put("/api/tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tests in the database
        List<Tests> testsList = testsRepository.findAll();
        assertThat(testsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTests() throws Exception {
        // Initialize the database
        testsRepository.saveAndFlush(tests);

        int databaseSizeBeforeDelete = testsRepository.findAll().size();

        // Get the tests
        restTestsMockMvc.perform(delete("/api/tests/{id}", tests.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tests> testsList = testsRepository.findAll();
        assertThat(testsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tests.class);
        Tests tests1 = new Tests();
        tests1.setId(1L);
        Tests tests2 = new Tests();
        tests2.setId(tests1.getId());
        assertThat(tests1).isEqualTo(tests2);
        tests2.setId(2L);
        assertThat(tests1).isNotEqualTo(tests2);
        tests1.setId(null);
        assertThat(tests1).isNotEqualTo(tests2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestsDTO.class);
        TestsDTO testsDTO1 = new TestsDTO();
        testsDTO1.setId(1L);
        TestsDTO testsDTO2 = new TestsDTO();
        assertThat(testsDTO1).isNotEqualTo(testsDTO2);
        testsDTO2.setId(testsDTO1.getId());
        assertThat(testsDTO1).isEqualTo(testsDTO2);
        testsDTO2.setId(2L);
        assertThat(testsDTO1).isNotEqualTo(testsDTO2);
        testsDTO1.setId(null);
        assertThat(testsDTO1).isNotEqualTo(testsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(testsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(testsMapper.fromId(null)).isNull();
    }
}
