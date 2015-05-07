package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Fields.GFleet;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;


public class FleetTest {
	
	Player owner;
	Player lander;
	GFleet FleetOne;
	GFleet FleetTwo;
	GFleet FleetThree;
	GUIController GGUI;
	ChanceCardList cc;
	Cup cup;
	GameBoard gb;

	@Before
	public void setup() throws Exception{
		
		owner = new Player("Owner", 0, 0, 0, 0, 0, false ,false, true);
		lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
		FleetOne = new GFleet(6, "LB Færgerne", 4000, 500);
		FleetTwo = new GFleet(16, "Danmark", 4000, 500);
		FleetThree = new GFleet(26, "Mols-Linien A/S", 4000, 500);
		gb = new GameBoard();
		GGUI = new GUIController();
		cup = new Cup();
		cc = new ChanceCardList();
		
	}
	
	@Test 
	public void testRentOne() 
	{
		FleetOne.setOwner(owner);
		owner.setFleetsOwned(1);
		int actual = FleetOne.getRent();
		int expected = 500;
		assertEquals(actual, expected);
	}
	
	@Test 
	public void testRentThree()
	{
		FleetOne.setOwner(owner);
		FleetTwo.setOwner(owner);
		FleetThree.setOwner(owner);
		owner.setFleetsOwned(3);
		int actual = FleetOne.getRent();
		int expected = 2000;
		assertEquals(actual, expected);
	}
	
	@Test 
	public void testBuyFleet()
	{
		int expected = 26000;
		FleetOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertNotNull(FleetOne.getOwner());
	}
	
	@Test 
	public void testDontBuyFleet()
	{
		int expected = 30000;
		FleetOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertNull(FleetOne.getOwner());
	}
	
	@Test 
	public void testCantAffordFleet()
	{
		int expected = 2000;
		lander.acc.withdraw(28000);
		FleetOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.acc.getBalance() );
		assertNull(FleetOne.getOwner());
	}
	
	
}
