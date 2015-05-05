package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

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
		GGUI = new GUIController();
		gb = new GameBoard();
		GGUI.createGameboard(gb);
		t1 = (GTerritory) gb.getField(16); //new GTerritory(17, "Bernstorfsvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
		t2 = (GTerritory) gb.getField(18); //GTerritory(19, "Hellerupvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
		t3 = (GTerritory) gb.getField(19); //new GTerritory(20, "Strandvej", 4000, 350, 2000, 1600, 4400, 12000, 16000, 20000, new Color(129, 129, 129));
		cup = new Cup();
		cc = new ChanceCardList();
	}
	
	@Test 
	public void testRentOne() 
	{
		// *** Owner ejer ét Territory ***
		t1.setOwner(owner);
	
		// *** Forventet balance for lander ***
		int expected = 30000 - 300;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 300;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentThree() 
	{
		// *** Owner ejer tre Territory ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 300 * 2;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 300 * 2;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance());
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentOneHouse() 
	{
		// *** Owner ejer et Territory med 1 hus på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(1);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 1400;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 1400;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentFourHouse() 
	{
		// *** Owner ejer et Territory med 1 hus på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(4);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 15000;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 15000;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentHotel() 
	{
		// *** Owner ejer et Territory med 1 hus på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHotel(1);
		t2.setHotel(1);
		t3.setHotel(1);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 19000;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 19000;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
}
