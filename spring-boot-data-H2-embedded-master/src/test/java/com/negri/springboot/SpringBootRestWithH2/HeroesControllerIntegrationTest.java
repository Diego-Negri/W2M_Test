package com.negri.springboot.SpringBootRestWithH2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.dnegri.springboot.SpringBootRestWithH2.SpringApp;
import com.dnegri.springboot.SpringBootRestWithH2.model.Heroes;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@ContextConfiguration
@SpringBootTest(classes = SpringApp.class,webEnvironment = RANDOM_PORT)

public class HeroesControllerIntegrationTest {

  
    @Autowired
    private TestRestTemplate restTemplate;

 
  
     @Test
     public void testAddHeroes() {
    	 Heroes heroes = new Heroes("18", "SuperTEST");
    	 ResponseEntity<String> responseEntity = this.restTemplate
         .postForEntity("http://localhost:8090/heroes", heroes, String.class);
       assertEquals(201, responseEntity.getStatusCodeValue());
     }

		private void assertEquals(int i, int statusCodeValue) {
			// TODO Auto-generated method stub
			
		}
}

