package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Fields.GBrewery;
import Fields.GFleet;
import Fields.GTerritory;
import Fields.Pawn;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class PawnTest {

		Player owner;
		Player lander;
		GTerritory t1;
		GTerritory t2;
		GTerritory t3;
		GFleet f1;
		GBrewery b1;
		GUIController GGUI;
		ChanceCardList cc;
		Cup cup;
		GameBoard gb;
		Pawn pawn;
		
		@Before
		public void setup() throws Exception{
			
			owner = new Player("Owner", 0, 0, 0, 0, 0, false ,false, true);
			lander = new Player("Lander", 0, 0, 0, 0, 0, false, false, true);
			GGUI = new GUIController();
			gb = new GameBoard();
			GGUI.createGameboard(gb);
			t1 = (GTerritory) gb.getField(16); //new GTerritory(17, "Bernstorfsvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
			t2 = (GTerritory) gb.getField(18); //GTerritory(19, "Hellerupvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
			t3 = (GTerritory) gb.getField(19); //new GTerritory(20, "Strandvej", 4000, 350, 2000, 1600, 4400, 12000, 16000, 20000, new Color(129, 129, 129));
			f1 = (GFleet) gb.getField(5); //new GFleet(6, "LB Færgerne", 4000, 500);
			b1 = (GBrewery) gb.getField(12); //new GBrewery(13, "Tuborg", 3000, 100);
			cup = new Cup();
			cc = new ChanceCardList();
			pawn = new Pawn();
		}
		
		@Test
		public void testPawnTerritory()
		{
			// *** Sætter owner til t1 ***
			t1.setOwner(owner);
			
			// *** Viser hvad hvordan testen skal køres ***
			GGUI.showMessage("Pantsæt grunden");
			
			// *** Kører pawn metoden igennem ***
			pawn.pawnGround(owner, gb, GGUI);
			
			// *** Tjekker om owner har pantsat grunden og om balance er opdateret rigtigt ***
			assertEquals(t1.getPawn(), true);
			assertEquals(owner.acc.getBalance(), 31800);
		}
		
		@Test
		public void testPawnFleet()
		{
			// *** Sætter owner til t1 ***
			f1.setOwner(owner);
			
			// *** Viser hvad hvordan testen skal køres ***
			GGUI.showMessage("Pantsæt grunden");
			
			// *** Kører pawn metoden igennem ***
			pawn.pawnGround(owner, gb, GGUI);
			
			// *** Tjekker om owner har pantsat grunden og om balance er opdateret rigtigt ***
			assertEquals(f1.getPawn(), true);
			assertEquals(owner.acc.getBalance(), 32000);
		}
		
		@Test
		public void testPawnBrewery()
		{
			// *** Sætter owner til t1 ***
			b1.setOwner(owner);
			
			// *** Viser hvad hvordan testen skal køres ***
			GGUI.showMessage("Pantsæt grunden");
			
			// *** Kører pawn metoden igennem ***
			pawn.pawnGround(owner, gb, GGUI);
			
			// *** Tjekker om owner har pantsat grunden og om balance er opdateret rigtigt ***
			assertEquals(b1.getPawn(), true);
			assertEquals(owner.acc.getBalance(), 31500);
		}
		
		@Test
		public void testNoPawn()
		{
			// *** Kører pawn metoden igennem ***
			pawn.pawnGround(owner, gb, GGUI);
		}
		
		@Test
		public void testSellHotel()
		{
			// *** Owner ejer tre territory og har hotel ***
			t1.setOwner(owner);
			t2.setOwner(owner);
			t3.setOwner(owner);
			t1.setHotel(1);
			
			// *** Viser hvad hvordan testen skal køres ***
			GGUI.showMessage("Sælg hotel");
			
			// *** Kører sellHotel metoden fra Pawn ***
			pawn.sellHotel(owner, gb, GGUI);
			
			// *** Checker om der er stadig er hotel og fire huse og owner modtager penge ***
			assertEquals(t1.getHotelCount(), 0);
			assertEquals(t1.getHouseCount(), 4);
			assertEquals(owner.acc.getBalance(), 31000);
		}
		
		@Test
		public void testSellHouse()
		{
			// *** Owner ejer tre territory og har hotel ***
			t1.setOwner(owner);
			t2.setOwner(owner);
			t3.setOwner(owner);
			t1.setHouse(4);
			
			// *** Viser hvad hvordan testen skal køres ***
			GGUI.showMessage("Sælg huse");
			
			// *** Kører sellHouse metoden fra Pawn ***
			pawn.sellHouse(owner, gb, GGUI);
			
			// *** Checker husene er fjernet og owner modtager penge ***
			assertEquals(t1.getHouseCount(), 0);
			assertEquals(owner.acc.getBalance(), 34000);
		}
}
		
		
