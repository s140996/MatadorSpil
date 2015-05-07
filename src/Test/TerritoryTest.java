package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fields.GTerritory;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;
import ChanceCard.ChanceCardList;
import Die.*;

public class TerritoryTest {

	Player owner;
	Player lander;
	GTerritory t1;
	GTerritory t2;
	GTerritory t3;
	GUIController GGUI;
	ChanceCardList cc;
	Cup cup;
	GameBoard gb;
	
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
		cup = new Cup();
		cc = new ChanceCardList();
	}
	
	@Test
	public void testGetRent()
	{
		// *** Owner ejer ét Territory
		t1.setOwner(owner);
		
		// *** Forventet værdi for rent ***
		int expected = 300;
		
		// *** Checker om getRent() stemmer overens med forventede ***
		assertEquals(expected, t1.getRent());
	}
	
	@Test 
	public void testRentOne() 
	{
		// *** Owner ejer ét Territory ***
		t1.setOwner(owner);
	
		// *** Forventet balance for lander ***
		int expected = 30000 - 300;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 300;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentThree() 
	{
		// *** Owner ejer tre Territory ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 300 * 2;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 300 * 2;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance());
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentOneHouse() 
	{
		// *** Owner ejer et Territory med 1 hus på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(1);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 1400;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 1400;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentTwoHouse() 
	{
		// *** Owner ejer et Territory med 2 huse på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(2);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 4000;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 4000;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentThreeHouse() 
	{
		// *** Owner ejer et Territory med 3 huse på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(3);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 11000;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 11000;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentFourHouse() 
	{
		// *** Owner ejer et Territory med 1 hus på ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHouse(4);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 15000;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 15000;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test 
	public void testRentHotel() 
	{
		// *** Owner ejer tre Territory med ét hotel på hver ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		t1.setHotel(1);
		t2.setHotel(1);
		t3.setHotel(1);
		
		// *** Forventet balance for lander ***
		int expected = 30000 - 19000;
		
		// *** Forventet balance for owner ***
		int expected2 = 30000 + 19000;
		
		// *** Lander betaler for det første ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Check af lander balance ***
		assertEquals(expected, lander.acc.getBalance() );
		
		// *** Check af owner balance ***
		assertEquals(expected2, owner.acc.getBalance());
	}
	
	@Test
	public void testBuyHouse()
	{
		// *** Owner ejer alle af samme slags ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		
		// *** Siger at man skal købe fire huse ***
		GGUI.showMessage("Køb fire huse for test");
		
		// *** Owner lander på et ejet felt og skal købe fire huse ***
		t1.landOnField(owner, GGUI, cc, cup, gb);
		
		// *** Forventet balance for owner efter køb af fire huse til en værdi på 2000 hver ***
		int expected = 30000 - 2000*4;
		
		// *** Check af owner balance ***
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testBuyHotel()
	{
		// *** Owner ejer alle af samme slags ***
		t1.setOwner(owner);
		t2.setOwner(owner);
		t3.setOwner(owner);
		
		// *** Owner har fire huse på hver af sine grunde, for at kunne købe hotel ***
		t1.setHouse(4);
		t2.setHouse(4);
		t3.setHouse(4);

		// *** Siger at man skal købe hotel for test ***
		GGUI.showMessage("Køb hotel for test");
		
		// *** Owner lander på et af sine felter for at købe hotel ***
		t1.landOnField(owner, GGUI, cc, cup, gb);
		
		// *** Forventet balance for owner efter køb af hotel til en værdi 2000 ***
		int expected = 30000 - 2000;
		
		// *** Check af owner balance ***
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testPawn()
	{
		// *** Owner ejer en grund som er pantsat ***
		t1.setOwner(owner);
		t1.setPawn(true);
		
		// *** Message så man ved hvad testen går ud på ***
		GGUI.showMessage("Køb pantsat grund tilbage");
		
		// *** Owner lander på sin egen grund som er pantsat ***
		t1.landOnField(owner, GGUI, cc, cup, gb);
		
		// *** Forventet værdi for owner efter køb af pantsat grund ***
		int expected = 30000 - 1800;
		
		// *** Checker af owner balance ***
		assertEquals(expected, owner.acc.getBalance());
	}
	
	@Test
	public void testLandOnPawned()
	{
		// *** Owner ejer en grund som er pantsat ***
		t1.setOwner(owner);
		t1.setPawn(true);
		
		// *** Lander lander på t1 som er pantsat af owner ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Forventet værdi efter lander har landet på feltet ***
		int expected = 30000;
		
		// *** Checker om owner og lander har uændret balance ***
		assertEquals(expected, owner.acc.getBalance());
		assertEquals(expected, lander.acc.getBalance());
	}
	
	@Test
	public void testBuyTerritory()
	{
		// *** Message om hvad testen går ud på ***
		GGUI.showMessage("Køb Grunden");
		
		// *** Lander lander på feltet og køber det ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Forventet balance for lander efter køb af territory til værdi af 3600 ***
		int expected = 30000 - 3600;
		
		// *** Checker lander balance, og ser om lander er owner af t1 ***
		assertEquals(expected, lander.acc.getBalance());
		equals(t1.getOwner().equals(lander));
	}
	
	@Test
	public void testRemoveOwner()
	{
		// *** Sætter owner som ejer af ét Territory ***
		t1.setOwner(owner);
		
		// *** Fjerne owner som ejer ***
		t1.removeOwner(owner, t1.getID(), GGUI);
		
		assertEquals(t1.getOwner(), null);
	}
	
	@Test
	public void testPlayerLost()
	{
		// *** Ændrer balancen for lander så han ikke har råd til at lande på feltet ***
		lander.acc.setBalance(200);
		
		// *** Sætter owner til ejer af et felt
		t1.setOwner(owner);
		
		// *** Får lander til at lande på feltet ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Forventet værdi for lander og owner ***
		int expectedO = 30000 + 200;
		int expectedL = 0;
		
		// Checker værdierne stemmer overens ***
		assertEquals(expectedO, owner.acc.getBalance());
		assertEquals(expectedL, lander.acc.getBalance());
	}
	
	@Test
	public void testConvict()
	{
		// *** Sætter owner til ejer af et felt og convict til true ***
		t1.setOwner(owner);
		owner.setConvict(true);
		
		// *** Lander lander på feltet som owner ejer ***
		t1.landOnField(lander, GGUI, cc, cup, gb);
		
		// *** Forventet værdi for lander og owner ***
		int expected = 30000;
		
		// *** Checker værdierne ***
		assertEquals(expected, lander.acc.getBalance());
		assertEquals(expected, owner.acc.getBalance());
	}
}
