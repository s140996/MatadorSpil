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
	public void setup() throws Exception{
		
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
		// *** Owner ejer ét Brewery ***
		BreweryOne.setOwner(owner);
		owner.setBrewerysOwned(1);
		
		// *** Lander slår med terninger ***
		cup.roll();
		int expected = 30000 - cup.getLastRoll() * 100;
		
		// *** Lander betaler for det første ***
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af resultat ***
		assertEquals(expected, lander.acc.getBalance() );
	}
	
	@Test 
	public void testRentTwo() 
	{
		// *** Owner ejer ét Brewery ***
		BreweryOne.setOwner(owner);
		BreweryTwo.setOwner(owner);
		owner.setBrewerysOwned(2);
		
		// *** Lander slår med terninger ***
		cup.roll();
		int expected = 30000 - cup.getLastRoll() * 200;
		
		// *** Lander betaler for det første ***
		BreweryOne.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af resultat ***
		assertEquals(expected, lander.acc.getBalance() );
	}
	
	
	
}
