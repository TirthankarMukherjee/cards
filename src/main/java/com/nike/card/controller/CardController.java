package com.nike.card.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nike.card.model.Deck;
import com.nike.card.model.ShuffleAlgo;
import com.nike.card.service.DeckService;

@RestController
public class CardController {

	@Autowired
	private DeckService deckService;
	
	@Value("${reshuffle.algorithm}")
	private String reshuffleAlgo;


	@RequestMapping(value = "/getDeck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Deck> getDeck(@RequestParam(value = "name") String name) {
		return new ResponseEntity<Deck>( deckService.getDeck(name),HttpStatus.OK);
	}

	@RequestMapping(value="/getDeckList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Deck>> getDeckList() {
		return new ResponseEntity<Map<String, Deck>>( deckService.getDeckList(),HttpStatus.OK);
	}

	@RequestMapping(value="/createDeck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createDeck(@RequestParam(value = "algo") String algorithm,@RequestParam(value = "name") String name) {
		ShuffleAlgo algo = ShuffleAlgo.lookup(algorithm);
		deckService.createDeck(name, algo);
		return  new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateDeck", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateDeck(@RequestParam(value = "name") String name) {
		ShuffleAlgo algo = ShuffleAlgo.lookup(reshuffleAlgo);
		System.out.println(" >>>>>>>>>>>  " + algo);
		deckService.updateDeck(name, algo);
		return  new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value="/deleteDeck", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteDeck(@RequestParam(value = "name") String name) {
		deckService.deleteDeck(name);
		return  new ResponseEntity<Object>(HttpStatus.OK);
	}

	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	
	
}
