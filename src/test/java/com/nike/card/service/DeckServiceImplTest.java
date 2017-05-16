package com.nike.card.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.nike.card.dao.DeckDao;
import com.nike.card.model.Deck;
import com.nike.card.model.ShuffleAlgo;
import com.nike.card.utils.Utils;

public class DeckServiceImplTest {

	private DeckDao deckDao;
	private ShuffleService shuffleService;


	@Before
	public void setUp() {
		deckDao = Mockito.mock(DeckDao.class);
		shuffleService = Mockito.mock(ShuffleService.class);

	}
	
	@Test
    public void createDeckSuccessfully() {
		Deck deck = new Deck(Utils.getRandomDeck(),ShuffleAlgo.RANDOM);
		when(shuffleService.getShuffeledDeck(ShuffleAlgo.RANDOM,null)).thenReturn(deck);
		when(deckDao.upsertDeck(deck,"Test")).thenReturn(true);
		
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		testService.createDeck("Test",ShuffleAlgo.RANDOM);
       
    }
	
	@Test
    public void deleteDeckSuccessfully() {
		when(deckDao.deleteDeck("Test")).thenReturn(true);
		
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		testService.deleteDeck("Test");
       
    }
	
	@Test(expected=IllegalArgumentException.class)
    public void deleteDeckFailure()  {
		when(deckDao.deleteDeck("Test")).thenReturn(false);
	
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		testService.deleteDeck("Test");
       
    }
	
	@Test
    public void getDeckSuccessfully() {
		Deck deck = new Deck(Utils.getRandomDeck(),ShuffleAlgo.RANDOM);
		when(deckDao.getDeck("Test")).thenReturn(deck);
		
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		Deck returnedDeck = testService.getDeck("Test");
		assertTrue(returnedDeck.getAlgo().equals(ShuffleAlgo.RANDOM));
       
    }
	
	@Test(expected=IllegalArgumentException.class)
    public void getDeckFailure()  {
		when(deckDao.getDeck("Test")).thenReturn(null);
		
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		Deck returnedDeck = testService.getDeck("Test");
		
    }
	
	@Test
    public void getDeckListSuccessfully() {
		Map<String, Deck> deckList = new HashMap<String, Deck>();
		deckList.put("Test1",new Deck(Utils.getRandomDeck(),ShuffleAlgo.RANDOM));
		deckList.put("Test2",new Deck(Utils.getSortedDeck(),ShuffleAlgo.SORTED));
		when(deckDao.getDeckList()).thenReturn(deckList);
		
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		Map<String, Deck> returnedDeckList = testService.getDeckList();
		assertTrue(returnedDeckList.size()==2);
       
    }
	
	@Test
    public void updateDeckSuccessfully() {

		Deck sortedDeck = new Deck(Utils.getSortedDeck(),ShuffleAlgo.SORTED);
		Deck reShuffeledDeck = new Deck( Utils.getHandShuffeledDeck(sortedDeck),ShuffleAlgo.HAND_SHUFFELED) ;
		when(deckDao.getDeck("Test")).thenReturn(sortedDeck);
		when(shuffleService.getShuffeledDeck(ShuffleAlgo.HAND_SHUFFELED,sortedDeck)).thenReturn(reShuffeledDeck);
		
		DeckServiceImpl testService = new DeckServiceImpl();
		testService.setDeckDao(deckDao);
		testService.setShuffleService(shuffleService);
		testService.updateDeck("Test",ShuffleAlgo.HAND_SHUFFELED);
		
       
    }

	


}
