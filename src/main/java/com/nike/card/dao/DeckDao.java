package com.nike.card.dao;

import java.util.Map;

import com.nike.card.model.Deck;

public interface DeckDao {
	
	public boolean upsertDeck(Deck deck, String name);
	public boolean deleteDeck(String name);
	public Deck getDeck(String name);
	public Map<String,Deck> getDeckList();

}
