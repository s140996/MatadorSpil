package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Die.Cup;
import Game.GUIController;
import Player.Player;

public class PlayerTest {

	Player testPlay;
	GUIController GGUI;
	Cup cup;
	
	@Before
	public void setup() throws Exception
	{
	testPlay = new Player("PlayerOne", 0, 0, 0, 0, 0, false, false, false);
	GGUI = new GUIController();
	cup = new Cup();
	}
	
		// *** Checking if the contruct is made with the correct name. ***
	
	@Test 
	public void testPlayerName() 
	{
		
		assertEquals(testPlay.toString(),"PlayerOne");
	}

		// *** Checks and sees if the name has been changed ***
	
	@Test 
	public void testSetName() 
	{
		testPlay.setName("TestName");
		assertEquals(testPlay.toString(),"TestName");
	}

		// *** Tests that the method returns the name of the initialized player ***
	
	@Test 
	public void testGetName() 
	{
		assertEquals(testPlay.toString(),"PlayerOne");
	}

		// *** Test - Startamount
	
	@Test
	public void testStartBalance() 
	{
		assertEquals(testPlay.acc.getBalance(), 30000);
	}
	
	@Test
	public void testChangePosition()
	{
		testPlay.setPosition(40);
		cup.roll();
		
		testPlay.changePosition(cup.getLastRoll(), GGUI);
		
		assertEquals(cup.getLastRoll(), testPlay.getPosition());
		assertEquals(testPlay.acc.getBalance(), 34000);
	}
	
	@Test
	public void testChangePositionStart()
	{
		testPlay.goToStart();
		cup.roll();
		
		testPlay.changePosition(cup.getLastRoll(), GGUI);
		
		assertEquals(cup.getLastRoll(), testPlay.getPosition() - 1);
		assertEquals(testPlay.acc.getBalance(), 34000);
	}
	
	@Test
	public void testLastPosition()
	{
		testPlay.setPosition(39);
		
		testPlay.changePosition(2, GGUI);
		
		assertEquals(testPlay.getLastPosition(), true);
		assertEquals(testPlay.getPosition(), 1);
	}
	
}
