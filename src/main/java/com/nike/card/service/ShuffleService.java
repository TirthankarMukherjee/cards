package com.nike.card.service;

import com.nike.card.model.Deck;
import com.nike.card.model.ShuffleAlgo;

/**
 * Implement this service to return a deck in differnt order like sorted, random and hand-shuffeled
 */


public interface ShuffleService {
	
	public Deck getShuffeledDeck(ShuffleAlgo algo,Deck initialDeck );

}
