package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Player.Player;

public class PlayerTest {

	Player testPlay;
	
	@Before
	public void setup() throws Exception{
	testPlay = new Player("PlayerOne", 0, 0, 0, 0, 0, false, false);
	}
	
		// *** Checking if the contruct is made with the correct name. ***
	
	@Test 
	public void testPlayerName() {
		
		assertEquals(testPlay.toString(),"PlayerOne");
	}

		// *** Checks and sees if the name has been changed ***
	
	@Test 
	public void testSetName() {
		testPlay.setName("TestName");
		assertEquals(testPlay.toString(),"TestName");
	}

		// *** Tests that the method returns the name of the initialized player ***
	
	@Test 
	public void testGetName() {
		assertEquals(testPlay.toString(),"PlayerOne");
	}

}
