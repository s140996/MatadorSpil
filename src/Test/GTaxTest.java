package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Fields.*;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;
import ChanceCard.ChanceCardList;
import Die.*;

public class GTaxTest {

	
	
	Player lander;
	GTax TaxOne;
	GTax TaxTwo;
	GUIController GGUI;
	ChanceCardList cc;
	Cup cup;
	GameBoard gb;
	
	@Before
	public void setup() throws Exception{
		
	
		lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
		TaxOne = new GTax(4, "Tax1", 3000, 10);
		cup = new Cup();
		gb = new GameBoard();
		cc = new ChanceCardList();
		GGUI = new GUIController();
	
	}
	
	@Test 
	public void testPercentageTax() 
	{
		
		cup.roll();
		int expected = 30000 - lander.getWorth() * 10/100;
		
		
		TaxOne.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af resultat ***
		assertEquals(expected, lander.getWorth());
	}
	
	@Test 
	public void testBaseTax() 
	{
		
		cup.roll();
		int expected = 30000 - TaxOne.getBasetax();
		
		
		TaxOne.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af resultat ***
		assertEquals(expected, lander.getWorth());
	}
	
	
}
