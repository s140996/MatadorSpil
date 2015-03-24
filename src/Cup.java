
public class Cup {

	Dice dice = new Dice();
	
	int dieOne = dice.rollDieOne();
	int dieTwo = dice.rollDieTwo();
	
	int lastRoll = dieOne + dieTwo;
	
}
