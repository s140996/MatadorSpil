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
		TaxOne = new GTax(4, "Tax1", 4000, 10); 
		TaxTwo = new GTax(39,"Ekstra- ordinær statsskat", 2000, 0);
		cup = new Cup();
		gb = new GameBoard();
		cc = new ChanceCardList();
		GGUI = new GUIController();
		GGUI.createGameboard(gb);
	}
	
	@Test 
	public void testPercentageTax() 
	{
		GGUI.showMessage("Test Percentage Tax");
		int expected = 30000 - lander.getWorth() * 10 / 100; 
		TaxOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.getWorth());
	}
	
	@Test 
	public void testBaseTaxOne() 
	{
		GGUI.showMessage("Test Base Tax");
		int expected = 30000 - TaxOne.getBasetax();
		TaxOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.getWorth());
	}
	
	@Test 
	public void testLanderCantAffordBaseTax() 
	{
		GGUI.showMessage("Test can't afford Base Tax");
		lander.acc.setBalance(3000);
		int expected = 2700; 
		TaxOne.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.getWorth());
	}

	@Test 
	public void testBaseTaxTwo() 
	{
		GGUI.showMessage("Test Base Tax");
		int expected = 30000 - TaxTwo.getBasetax();
		TaxTwo.landOnField(lander, GGUI, cc, cup, gb);
		assertEquals(expected, lander.getWorth());
	}
	
}
