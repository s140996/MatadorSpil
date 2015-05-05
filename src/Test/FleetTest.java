package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fields.GFleet;
import Player.Player;


public class FleetTest {
	
	Player owner;
	Player lander;
	GFleet FleetOne;
	GFleet FleetTwo;
	GFleet FleetThree;

	@Before
	public void setup() throws Exception{
		
		owner = new Player("Owner", 0, 0, 0, 0, 0, false ,false, true);
		lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
		FleetOne = new GFleet(6, "LB Færgerne", 4000, 500);
		FleetTwo = new GFleet(16, "Danmark", 4000, 500);
		FleetThree = new GFleet(26, "Mols-Linien A/S", 4000, 500);
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
	
	
}
