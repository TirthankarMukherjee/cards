package com.nike.card.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.nike.card.model.Card;
import com.nike.card.model.Deck;

public class Utils {

	public static List<Card> getSortedDeck() {
		List<Card> sortedDeck = new LinkedList<Card>();
		for (String suite : Deck.CARD_SUITES) {
			for (String value : Deck.CARD_VALUES) {
				sortedDeck.add(new Card(value + "-" + suite));
			}
		}
		return sortedDeck;
	}

	public static List<Card> getRandomDeck() {
		List<Card> deck = getSortedDeck();
		Collections.shuffle(deck);
		return deck;
	}

	public static List<Card> getHandShuffeledDeck(Deck initialDeck) {
		Random r = new Random();
		int Low = 2;
		int High = 10;
		int numberOfTimesToShuffle = r.nextInt(High - Low) + Low;

		List<Card> deck = initialDeck == null ? getSortedDeck() : initialDeck.getDeckOfCards();

		for (int i = 0; i < numberOfTimesToShuffle; i++) {
			ArrayList<Card> halfOne = new ArrayList<Card>(deck.subList(0, 26));
			ArrayList<Card> halfTwo = new ArrayList<Card>(deck.subList(26, 52));

			deck = new ArrayList<Card>();
			for (int j = 0; j < 26; j++) {
				deck.add(halfOne.get(j));
				deck.add(halfTwo.get(j));
			}
		}

		return deck;
	}
	

}
