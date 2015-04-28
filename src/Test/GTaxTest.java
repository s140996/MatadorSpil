package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;


import Player.Player;
import Player.Account;
import Game.GameBoard;
import Fields.GField;
import Fields.GTax;



public class GTaxTest {
	
	GTax GTax = new GTax(1, "Hej", 2000, 10);
	
	private String name;
	private int worth = 10000;
	private int position = 1;
	private int prisonCard;
	private int fleetsOwned;
	private int brewerysOwned;
	private int houseCount;
	private int hotelCount;
	private boolean convict = false;
	private boolean alive = true;
	
	Player Player = new Player(name, worth, position, prisonCard, fleetsOwned, brewerysOwned, houseCount, hotelCount, convict, alive);
	Account Account = new Account(10000);
	
	@Test
	public void BaseTaxTest() {
		
		GTax.getBasetax();
		assertEquals(GTax.getBasetax(),2000);
		
	}
		
		@Test
		public void PercentageTaxTest() {
			
			GTax.getPercentageTax();
			assertSame(GTax.getPercentageTax(), 10);
		
		
		}
	
	@Test
	public void PercentageTaTest(){
		
		GTax.landOnField(Player, null, null, null, null);
		
		
		
		
	}

}
