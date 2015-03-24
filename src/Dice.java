import java.util.Random;

public class Dice {

	Random rand = new Random();   

	public int rollDieOne(){

		int dieOne = rand.nextInt(6) + 1;		//+1 --> else it would be 0 - 5	
		
		return dieOne;
	}
	
	public int rollDieTwo(){
		
		int dieTwo = rand.nextInt(6) + 1;		//+1 --> else it would be 0 - 5

		return dieTwo;
	}
	
}
