package com.nike.card.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nike.card.dao.DeckDao;
import com.nike.card.model.Deck;
import com.nike.card.model.ShuffleAlgo;

@Service
public class DeckServiceImpl implements DeckService {

	@Autowired
	private DeckDao deckDao;
	@Autowired
	private ShuffleService shuffleService;

	@Override
	public void createDeck(String name, ShuffleAlgo algo) {
		Deck deck = shuffleService.getShuffeledDeck(algo,null);
		deckDao.upsertDeck(deck, name);
	}

	@Override
	public void deleteDeck(String name) {
		if (!deckDao.deleteDeck(name)) {
			throw new IllegalArgumentException("No deck exists by the name : " + name);
		}

	}

	@Override
	public Deck getDeck(String name) {
		Deck deck = deckDao.getDeck(name);
		if (deck == null) {
			throw new IllegalArgumentException("No deck exists by the name : " + name);
		}
		return deck;
	}

	@Override
	public Map<String, Deck> getDeckList() {
		return deckDao.getDeckList();
	}

	@Override
	public void updateDeck(String name, ShuffleAlgo algo) {
		Deck deck = deckDao.getDeck(name);
		Deck reShuffeledDeck = shuffleService.getShuffeledDeck(algo,deck);
		deckDao.upsertDeck(reShuffeledDeck, name);
		
	}

	public void setDeckDao(DeckDao deckDao) {
		this.deckDao = deckDao;
	}

	public void setShuffleService(ShuffleService shuffleService) {
		this.shuffleService = shuffleService;
	}
	
	

}
