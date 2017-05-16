package com.nike.card.model;

public enum ShuffleAlgo {
	SORTED,
	RANDOM,
	HAND_SHUFFELED;
	
	
	public static ShuffleAlgo lookup(String id) {
        try {
            return ShuffleAlgo.valueOf(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Shuffle algorithm must be one of : SORTED,RANDOM,HAND_SHUFFELED.");
        }
    }
}
