package com.nike.card.service;

import java.util.Map;

import com.nike.card.model.Deck;
import com.nike.card.model.ShuffleAlgo;

public interface DeckService {

	public void createDeck(String name, ShuffleAlgo algo);
	
	public void updateDeck(String name, ShuffleAlgo algo);

	public void deleteDeck(String name);

	public Deck getDeck(String name);

	public Map<String, Deck> getDeckList();

}
