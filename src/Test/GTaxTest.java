package Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Fields.GOwnable;
import Fields.GTax;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;



public class GTaxTest {
	
	private GUIController GGUI;
	private ChanceCardList cc;
	private Cup cup;
	private Player player;
	private Player owner;
	private GameBoard gameBoard;
	private GOwnable Gownable;
	private GTax Gtax;

	
@Before
public void setUp(){

	this.gameBoard = new GameBoard();
	this.player = new Player("Tester", 40000, 0, 0, 0, 0, false, true);
	this.owner = new Player("Ejeren", 10000, 0, 0, 0, 0, false, true);
	
	this.gameBoard.getField(4);
	this.gameBoard.getField(38);
	
	}
	
@Test
public void TestLandOnField(){
	
	int expected = 30000;
	int actual = this.player.acc.getBalance();
	Assert.assertEquals(expected, actual);
	
	
	
	Gtax.landOnField(player, GGUI, cc, cup, gameBoard);
	expected = 30000-4000;
	actual = this.player.acc.getBalance();
	Assert.assertEquals(expected, actual);
	
	
	
	
}
	
}