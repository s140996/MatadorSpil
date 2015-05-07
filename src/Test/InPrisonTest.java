package Test;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Die.Cup;
import Fields.InPrison;
import Game.GUIController;
import Player.*;

public class InPrisonTest {
	
	Player player;
	InPrison prison;
	Cup cup;
	GUIController GGUI;
	
	@Before
	public void setup() throws Exception{
		
		player = new Player("Tester", 0, 0, 0, 0, 0, false, true, true);
		prison = new InPrison();
		cup = new Cup();
		GGUI = new GUIController();
	}
	
	@Test 
	public void testPrisonCard() 
	{
		GGUI.showMessage("Test Prison Card");
		player.setPrisonCard(1);
		prison.inPrison(player, cup, GGUI);
		assertEquals(0 , player.getPrisonCard());
		boolean expectedBool = false;
		assertEquals(expectedBool, player.getConvict());
	}

	@Test 
	public void testPay() 
	{
		GGUI.showMessage("Test Betal");
		prison.inPrison(player, cup, GGUI);
		int expected = 30000 - 1000;
		assertEquals(player.acc.getBalance(), expected); 
	}
	
	@Test 
	public void testRoll() 
	{
		GGUI.showMessage("Test slå med terninger");
		prison.inPrison(player, cup, GGUI);
		if (cup.getDieOne() == cup.getDieTwo())
		{
			assertFalse(player.getConvict());
		}
		else
		{
			assertTrue(player.getConvict());
		}
	}
}
