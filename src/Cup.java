
public class Cup {

	private int dieOne;
	private int dieTwo;
	private int lastRoll;
	private int doubleThrows;
	
	Die die = new Die();
	
	public void roll()
	{
		this.dieOne = this.die.rollDie();
		this.dieTwo = this.die.rollDie();
		
		this.lastRoll = this.dieOne + this.dieTwo;
		
		if (this.dieOne == this.dieTwo)
		{
			this.doubleThrows++;
		}
	}
	
	public int getLastRoll()
	{
		return this.lastRoll;
	}

	public void resetDoubleThrows()
	{
		this.doubleThrows = 0;
	}
	
	public int getDoubleThrows()
	{
		return this.doubleThrows;
	}
	
	public int getDieOne()
	{
		return this.dieOne;
	}
	
	public int getDieTwo()
	{
		return this.dieTwo;
	}
	
}
