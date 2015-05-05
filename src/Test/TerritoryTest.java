package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import Fields.GBrewery;
import Fields.GTerritory;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;
import ChanceCard.ChanceCardList;
import Die.*;

public class TerritoryTest {

	Player owner;
	Player lander;
	GTerritory t1;
	GTerritory t2;
	GTerritory t3;
	GUIController GGUI;
	ChanceCardList cc;
	Cup cup;
	GameBoard gb;
	
	@Before
	public void setup() throws Exception{
		
		owner = new Player("Owner", 0, 0, 0, 0, 0, false ,false, true);
		lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
		t1 = new GTerritory(17, "Bernstorfsvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
		t2 = new GTerritory(19, "Hellerupvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
		t3 = new GTerritory(20, "Strandvej", 4000, 350, 2000, 1600, 4400, 12000, 16000, 20000, new Color(129, 129, 129));
		cup = new Cup();
		gb = new GameBoard();
		cc = new ChanceCardList();
		GGUI = new GUIController();
	
	}
	
	@Test 
	public void testRentOne() 
	{
		// *** Owner ejer ét Territory ***
		t1.setOwner(owner);
	
		// *** Forventet balance for lander ***
		int expected = 30000 - t1.getRent();
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + t1.getRent();
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
}
