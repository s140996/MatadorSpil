package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Die.Cup;
import Die.Die;

import java.util.Random;


public class DieTest {

	Cup cup = new Cup();
	Die die = new Die();
	
	@Test
	public void getLastRolltest() {
		
		cup.roll();
		assertEquals(cup.getLastRoll(), cup.getDieOne()+cup.getDieTwo());
	}

}
