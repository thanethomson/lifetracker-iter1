package com.thanethomson.lifetracker.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanethomson.lifetracker.models.User;
import com.thanethomson.lifetracker.repos.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BasicApiTests {

    @Autowired
    private MetricFamilyRepo metricFamilyRepo;

    @Autowired
    private MetricRepo metricRepo;

    @Autowired
    private MetricThemeRepo metricThemeRepo;

    @Autowired
    private SampleGroupRepo sampleGroupRepo;

    @Autowired
    private SampleRepo sampleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        // flush all of our repos
        userRepo.deleteAll();
        sampleRepo.deleteAll();
        sampleGroupRepo.deleteAll();
        metricThemeRepo.deleteAll();
        metricRepo.deleteAll();
        metricFamilyRepo.deleteAll();
    }

    @Test
    public void testSampleCollection() throws IOException {
        // first create a few users
        User michael = loadUser("/fixtures/users/michael.json");
        michael = userRepo.save(michael);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/users", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode json = mapper.readTree(response.getBody());
    }

    private User loadUser(String resourceName) {
        JsonNode json = Resources.loadJson(resourceName, mapper);
        assertNotNull(json);
        User user = new User();
        user.setEmail(json.get("email").asText());
        user.setFirstName(json.get("firstName").asText());
        user.setLastName(json.get("lastName").asText());
        user.setPasswordHash(passwordEncoder.encode(json.get("password").asText()));
        return user;
    }

}
