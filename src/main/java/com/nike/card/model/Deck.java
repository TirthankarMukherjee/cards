package com.nike.card.model;

import java.util.List;

public class Deck {

	public final static String CARD_SUITES[] = { "HEARTS", "SPADES", "CLUB", "DIAMOND" };
	public final static String CARD_VALUES[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	private List<Card> deckOfCards;
	private ShuffleAlgo algo;

	public Deck(){
		
	}
	
	public Deck(List<Card> deckOfCards, ShuffleAlgo algo) {
		super();
		this.deckOfCards = deckOfCards;
		this.algo = algo;
	}

	public List<Card> getDeckOfCards() {
		return deckOfCards;
	}

	public ShuffleAlgo getAlgo() {
		return algo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((algo == null) ? 0 : algo.hashCode());
		result = prime * result + ((deckOfCards == null) ? 0 : deckOfCards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		if (algo != other.algo)
			return false;
		if (deckOfCards == null) {
			if (other.deckOfCards != null)
				return false;
		} else if (!deckOfCards.equals(other.deckOfCards))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deck [deckOfCards=" + deckOfCards + ", algo=" + algo + "]";
	}

}
