package com.nike.card.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

//Integration Test

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateDeck() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", "test1");
		map.add("algo", "SORTED");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);

		ResponseEntity<?> response = this.restTemplate.postForEntity("/createDeck", requestEntity, String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test
	public void testCreateDeckFail() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", "test1_1");
		map.add("algo", "SORTEDD"); //bad request

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);

		ResponseEntity<?> response = this.restTemplate.postForEntity("/createDeck", requestEntity, String.class);
		System.out.println(response.getStatusCode());
		assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	
	@Test
	public void testGetDeck() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> mapCreate = new LinkedMultiValueMap<String, String>();
		mapCreate.add("name", "test2");
		mapCreate.add("algo", "SORTED");

		HttpEntity<MultiValueMap<String, String>> requestEntityCreate = new HttpEntity<MultiValueMap<String, String>>(
				mapCreate, headers);

		ResponseEntity<?> responseCreate = this.restTemplate.postForEntity("/createDeck", requestEntityCreate,
				String.class);
		assertTrue(responseCreate.getStatusCode().equals(HttpStatus.OK));

		ResponseEntity<?> responseGetDeck = this.restTemplate.getForEntity("/getDeck?name=test2", String.class);

		assertTrue(responseGetDeck.getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test
	public void testDeleteDeck() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> mapCreate = new LinkedMultiValueMap<String, String>();
		mapCreate.add("name", "test3");
		mapCreate.add("algo", "SORTED");
		HttpEntity<MultiValueMap<String, String>> requestEntityCreate = new HttpEntity<MultiValueMap<String, String>>(
				mapCreate, headers);
		ResponseEntity<?> responseCreate = this.restTemplate.postForEntity("/createDeck", requestEntityCreate,
				String.class);
		assertTrue(responseCreate.getStatusCode().equals(HttpStatus.OK));
		
		//delete
		this.restTemplate.delete("/deleteDeck?name=test3", String.class);
		
		//get again, you should not find it
		ResponseEntity<?> responseGetDeck = this.restTemplate.getForEntity("/getDeck?name=test3", String.class);
		assertTrue(responseGetDeck.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testUpdateDeck() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> mapCreate = new LinkedMultiValueMap<String, String>();
		mapCreate.add("name", "test4");
		mapCreate.add("algo", "SORTED");

		HttpEntity<MultiValueMap<String, String>> requestEntityCreate = new HttpEntity<MultiValueMap<String, String>>(
				mapCreate, headers);
		//create
		ResponseEntity<?> responseCreate = this.restTemplate.postForEntity("/createDeck", requestEntityCreate,
				String.class);
		assertTrue(responseCreate.getStatusCode().equals(HttpStatus.OK));
		
		//update
		this.restTemplate.put("/updateDeck?name=test4&algo=RANDOM", String.class);

		//get again, you should find it randomly sorted
		ResponseEntity<String> responseGetDeck = this.restTemplate.getForEntity("/getDeck?name=test4", String.class);
		assertTrue(responseGetDeck.getStatusCode().equals(HttpStatus.OK));
	
		assertTrue(responseGetDeck.getBody().contains("\"algo\":\"RANDOM\""));
	}

}
