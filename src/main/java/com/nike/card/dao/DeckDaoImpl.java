package com.nike.card.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.nike.card.model.Deck;

@Repository
public class DeckDaoImpl implements DeckDao {

	private Map<String, Deck> deckDataStore = new ConcurrentHashMap<String, Deck>();

	@Override
	public boolean upsertDeck(Deck deck, String name) {
		deckDataStore.put(name, deck);
		return true;
	}

	@Override
	public boolean deleteDeck(String name) {
		if (deckDataStore.containsKey(name)) {
			deckDataStore.remove(name);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Deck getDeck(String name) {
		return deckDataStore.get(name);
	}

	@Override
	public Map<String, Deck> getDeckList() {
		// Deck class is immutable so this shallow copy will keep things safe
		return new HashMap<String, Deck>(deckDataStore);
	}

}
