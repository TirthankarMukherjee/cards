package com.nike.card.model;

// A card may be represented as a simple string such as "5-heart", or "K-spade".
public class Card {

	private String cardName;

	public String getCardName() {
		return cardName;
	}

	public Card(String cardName) {
		super();
		this.cardName = cardName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
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
		Card other = (Card) obj;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [cardName=" + cardName + "]";
	}

}
