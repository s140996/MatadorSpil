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
		// *** Giver player et prison card ***
		player.setPrisonCard(1);
		
		// *** Tjekker om spilleren bruger sit fængselskort ***
		prison.inPrison(player, cup, GGUI);
		
		// *** Tjekker om spilleren har brugt sit prison card ***
		assertEquals(0 , player.getPrisonCard());
		
		// *** Tjekker om spilleren stadig er convict ***
		boolean expectedBool = false;
		assertEquals(expectedBool, player.getConvict());
	}

	@Test 
	public void testPay() 
	{
		// *** Kører inPrison, hvor spilleren skal betale sig ud ***
		prison.inPrison(player, cup, GGUI);
		int expected = 30000 - 1000;
		
		// *** Tjekker om spillerens konto er faldet med 1000 ***
		assertEquals(player.acc.getBalance(), expected);
	}
	
	@Test 
	public void testRoll() 
	{
		// *** Kører inPrison, hvor spilleren skal slå sig ud ***
		prison.inPrison(player, cup, GGUI);
		
		// *** Tjekker om spilleren slår dobbelt ***
		boolean expected = true;
		
		if (cup.getDoubleRoll() == 1)
		{
			expected = false;
		}
		else if (cup.getDoubleRoll() == 0)
		{
			expected = true;
		}
		
		// *** Tjekker om spilleren stadig er convict ***
		assertEquals(expected, player.getConvict());
	}
}
