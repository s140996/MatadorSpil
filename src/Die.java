import java.util.Random;

public class Die {

	Random rand = new Random();   

	public int rollDie(){

		int die = rand.nextInt(6) + 1;		//+1 --> else it would be 0 - 5	
		
		return die;
	}
	

}
