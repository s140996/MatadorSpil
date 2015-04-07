
public class Cup {

	Die dice = new Die();
	
	int dieOne = dice.rollDie();
	int dieTwo = dice.rollDie();
	
	int lastRoll = dieOne + dieTwo;
	
}
