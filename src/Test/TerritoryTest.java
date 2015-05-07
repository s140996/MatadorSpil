package Test;

import static org.junit.Assert.*;

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
	public void testGetRent()
	{
		t1.setOwner(owner);
		int expected = 300;
		assertEquals(expected, t1.getRent());
	}
	
	@Test 
	public void testRentOne() 
	{
		t1.setOwner(owner);
		int expected = 30000 - 300;
		int expected2 = 30000 + 300;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentThree() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		int expected = 30000 - 300 * 2;
		int expected2 = 30000 + 300 * 2;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance());
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentOneHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(1);
		int expected = 30000 - 1400;
		int expected2 = 30000 + 1400;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentTwoHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(2);
		int expected = 30000 - 4000;
		int expected2 = 30000 + 4000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentThreeHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(3);
		int expected = 30000 - 11000;
		int expected2 = 30000 + 11000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentFourHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(4);
		int expected = 30000 - 15000;
		int expected2 = 30000 + 15000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentHotel() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHotel(1);
		t2.setHotel(1);
		t3.setHotel(1);
		int expected = 30000 - 19000;
		int expected2 = 30000 + 19000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test
	public void testBuyHouse()
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		GGUI.showMessage("Køb fire huse for test");
		t1.landOnField(owner, GGUI, cc, cup, gb);
		int expected = 30000 - 2000*4;
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testBuyHotel()
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(4);
		t2.setHouse(4);
		t3.setHouse(4);
		GGUI.showMessage("Køb hotel for test");
		t1.landOnField(owner, GGUI, cc, cup, gb);
		int expected = 30000 - 2000;
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testPawn()
	{
		t1.setOwner(owner);
		t1.setPawn(true);
		GGUI.showMessage("Køb pantsat grund tilbage");
		t1.landOnField(owner, GGUI, cc, cup, gb);
		int expected = 30000 - 1800;
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testLandOnPawned()
	{
		t1.setOwner(owner);
		t1.setPawn(true);
		t1.landOnField(lander, GGUI, cc, cup, gb);
		int expected = 30000;
		assertEquals(expected, owner.acc.getBalance());
		assertEquals(expected, lander.acc.getBalance());
	}
	
	@Test
	public void testBuyTerritory()
	{
		GGUI.showMessage("Køb Grunden");
		t1.landOnField(lander, GGUI, cc, cup, gb);
		int expected = 30000 - 3600;
		assertEquals(expected, lander.acc.getBalance());
		equals(t1.getOwner().equals(lander));
	}
	
	@Test
	public void testRemoveOwner()
	{
		t1.setOwner(owner);
		t1.removeOwner(owner, t1.getID(), GGUI);
		assertEquals(t1.getOwner(), null);
	}
	
	@Test
	public void testPlayerLost()
	{
		lander.acc.setBalance(200);
		t1.setOwner(owner);
		t1.landOnField(lander, GGUI, cc, cup, gb);
		int expectedO = 30000 + 200;
		int expectedL = 0;
		assertEquals(expectedO, owner.acc.getBalance());
		assertEquals(expectedL, lander.acc.getBalance());
	}
	
	@Test
	public void testConvict()
	{
		t1.setOwner(owner);
		owner.setConvict(true);
		t1.landOnField(lander, GGUI, cc, cup, gb);
		int expected = 30000;
		assertEquals(expected, lander.acc.getBalance());
		assertEquals(expected, owner.acc.getBalance());
	}

	@Test 
	public void testCantAffordRentHotel() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHotel(1);
		t2.setHotel(1);
		t3.setHotel(1);
		lander.acc.setBalance(10000);
		int expected = 0;
		int expected2 = 30000 + 10000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}

	@Test 
	public void testCantAffordRentFourHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(4);
		lander.acc.setBalance(5000);
		int expected = 0;
		int expected2 = 30000 + 5000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}

	@Test 
	public void testCantAffordRentThreeHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(3);
		lander.acc.setBalance(1000);
		int expected = 0;
		int expected2 = 30000 + 1000;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}

	@Test 
	public void testCantAffordRentTwoHouse() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(2);
		lander.acc.setBalance(500);
		int expected = 0;
		int expected2 = 30000 + 500;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testCantAffordRentOneHouse()
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(1);
		lander.acc.setBalance(500);
		int expected = 0;
		int expected2 = 30000 + 500;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}

	@Test 
	public void testCantAffordRent() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(1);
		lander.acc.setBalance(200);
		int expected = 0;
		int expected2 = 30000 + 200;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertEquals(expected2, owner.acc.getBalance());
	}

	@Test
	public void testDontBuyHouse()
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		GGUI.showMessage("Test Køb ikke hus");
		t1.landOnField(owner, GGUI, cc, cup, gb);
		int expected = 30000;
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testDontBuyHotel()
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(4);
		t2.setHouse(4);
		t3.setHouse(4);
		GGUI.showMessage("Test Køb ikke hotel");
		t1.landOnField(owner, GGUI, cc, cup, gb);
		int expected = 30000;
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testDontBuyTerritory()
	{
		GGUI.showMessage("Test Køb ikke Grunden");
		t1.landOnField(lander, GGUI, cc, cup, gb);
		int expected = 30000;
		assertEquals(expected, lander.acc.getBalance());
		assertNull(t1.getOwner());
	}
	
	@Test 
	public void testCantAffordRentThree() 
	{
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		lander.acc.setBalance(200);
		int expected = 0;
		int expected2 = 30000 + 200;
		t1.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance());
		assertEquals(expected2, owner.acc.getBalance());
	}
	
}
