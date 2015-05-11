package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fields.GBrewery;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;
import ChanceCard.ChanceCardList;
import Die.*;

public class BreweryTest {

	Player owner;
	Player lander;
	GBrewery BreweryOne;
	GBrewery BreweryTwo;
	GUIController GGUI;
	ChanceCardList cc;
	Cup cup;
	GameBoard gb;
	
	@Before
	public void setup() throws Exception
	{	
		owner = new Player("Owner", 0, 0, 0, 0, 0, false ,false, true);
		lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
		BreweryOne = new GBrewery(13, "Tuborg", 3000, 100);
		BreweryTwo = new GBrewery(29, "Carlsberg", 3000, 100);
		cup = new Cup();
		gb = new GameBoard();
		cc = new ChanceCardList();
		GGUI = new GUIController(); 
	}
	
	@Test 
	public void testRentOne() 
	{
		BreweryOne.setOwner(owner);
		owner.setBrewerysOwned(1);
		cup.roll();
		int expected = 30000 - cup.getLastRoll() * 100;
		int expected2 = 30000 + cup.getLastRoll() * 100;
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance()); 
	}
	
	@Test 
	public void testRentTwo() 
	{
		BreweryOne.setOwner(owner);
		BreweryTwo.setOwner(owner);
		owner.setBrewerysOwned(2);
		cup.roll();
		int expected = 30000 - cup.getLastRoll() * 200;
		int expected2 = 30000 + cup.getLastRoll() * 200;
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testBuyBrewery()
	{
		int expected = 27000;
		GGUI.showMessage("Test Buy Brewery");
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertNotNull(BreweryOne.getOwner());
	}
	
	@Test 
	public void testDontBuyBrewery()
	{
		int expected = 30000;
		GGUI.showMessage("Test Dont Buy Brewery");
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertNull(BreweryOne.getOwner());
	}
	
	@Test 
	public void testCantAffordBrewery()
	{
		int expected = 2000;
		BreweryOne.setOwner(null);
		lander.acc.setBalance(2000); 
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb); 
		assertEquals(expected, lander.acc.getBalance());
		assertNull(BreweryOne.getOwner());
	}
	
	@Test 
	public void testLanderOwnsBrewery()
	{
		int expected = 30000;
		BreweryOne.setOwner(lander);
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance());	
	}
	
	@Test 
	public void testOwnerInPrison()
	{
		int expectedLander = 30000;
		int expectedOwner = 30000;
		BreweryOne.setOwner(owner);
		owner.setConvict(true);
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(owner.acc.getBalance() , expectedOwner);
		assertEquals(lander.acc.getBalance() , expectedLander);	
	}
	
	@Test 
	public void testLanderCantAffordRent()
	{
		lander.acc.setBalance(100);
		int expectedLander = 0;
		int expectedOwner = 30100; 
		BreweryOne.setOwner(owner);
		owner.setBrewerysOwned(1);
		cup.roll();
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expectedLander, lander.acc.getBalance());
		assertEquals(expectedOwner, owner.acc.getBalance());
	}
	
	@Test 
	public void testBreweryPawned()
	{
		BreweryOne.setOwner(owner);
		BreweryOne.setPawn(true);
		int expectedLander = 30000;
		int expectedOwner = 30000;
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expectedLander, lander.acc.getBalance());
		assertEquals(expectedOwner, owner.acc.getBalance());
	}
	
	@Test 
	public void testBreweryRePawned()
	{
		GGUI.showMessage("Test Re-Pawned");
		BreweryOne.setOwner(owner); 
		BreweryOne.setPawn(true);
		int expectedOwner = 28500;
		BreweryOne.landOnField(owner, GGUI, cc, cup, gb);
		assertEquals(expectedOwner, owner.acc.getBalance());
		assertFalse(BreweryOne.getPawn());
	}
	
	@Test 
	public void testBreweryNotRePawned()
	{
		GGUI.showMessage("Test Not Re-Pawned");
		BreweryOne.setOwner(owner);
		BreweryOne.setPawn(true);
		int expectedOwner = 30000;
		BreweryOne.landOnField(owner, GGUI, cc, cup, gb);
		assertEquals(expectedOwner, owner.acc.getBalance());
		assertTrue(BreweryOne.getPawn());
	}

}
