package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Fields.GGoPrison;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class GGoPrisonTest {

	Player lander;
	GGoPrison gGoPrison;
	GUIController GGUI;
	ChanceCardList cc;
	Cup cup;
	GameBoard gb;
	
	@Before
	public void setup() throws Exception
	{
		lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
		gGoPrison = new GGoPrison(31, "Gå i fængsel");
		cup = new Cup();
		gb = new GameBoard();
		cc = new ChanceCardList();
		GGUI = new GUIController();
		GGUI.createGameboard(gb);
	}
	
	@Test 
	public void testGGoPrison() 
	{
		int expectedPosition = 11;
		gGoPrison.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(lander.getPosition(),expectedPosition);
		assertTrue(lander.getConvict());
	}
}
