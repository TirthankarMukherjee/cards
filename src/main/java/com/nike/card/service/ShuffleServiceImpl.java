package com.nike.card.service;

import org.springframework.stereotype.Service;

import com.nike.card.model.Deck;
import com.nike.card.model.ShuffleAlgo;
import com.nike.card.utils.Utils;

//Rather than using switch & helper methods in utils, we can also write Shuffle as an interface, and different impls of it like SortedShuffle, RandomShuffel
// but it needs to change on parameters also, so some where there would be a switch case / if else

@Service
public class ShuffleServiceImpl implements ShuffleService {

	@Override
	public Deck getShuffeledDeck(ShuffleAlgo algo, Deck initialDeck) {
		switch (algo) {
		case SORTED:
			// for update call, initial deck don't matter for sorted deck
			return new Deck(Utils.getSortedDeck(), algo); 
		case HAND_SHUFFELED:
			return new Deck(Utils.getHandShuffeledDeck(initialDeck), algo);
		default:
			// for update call, initial deck don't matter for random deck
			return new Deck(Utils.getRandomDeck(), algo);
		}
	}
}
