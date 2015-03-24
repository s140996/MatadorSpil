import java.util.Random;

public class Dice {

	Random rand = new Random();   //+1 for else it would be 0 - 5

	public int rollDieOne(){

		int dieOne = rand.nextInt(6) + 1; 	
		
		return dieOne;
	}
	
	public int rollDieTwo(){
		
		int dieTwo = rand.nextInt(6) + 1;

		return dieTwo;
	}
	
}
