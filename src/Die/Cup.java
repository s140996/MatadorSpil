package Die;


public class Cup {

	private int dieOne;
	private int dieTwo;
	private int lastRoll;
	private int doubleRoll;
	
	Die die = new Die();
	
	public void roll()
	{
		this.dieOne = this.die.rollDie();
		this.dieTwo = this.die.rollDie();
		
		this.lastRoll = this.dieOne + this.dieTwo;
		
		if (this.dieOne == this.dieTwo)
		{
			this.doubleRoll++;
		}
	}
	
	public int getLastRoll()
	{
		return this.lastRoll;
	}

	public void resetDoubleRoll()
	{
		this.doubleRoll = 0;
	}
	
	public int getDoubleRoll()
	{
		return this.doubleRoll;
	}
	
	public int getDieOne()
	{
		return this.dieOne;
	}
	
	public int getDieTwo()
	{
		return this.dieTwo;
	}
	
	public void setDieOne(int dieOne)
	{
		this.dieOne = dieOne;
	}
	
	public void setDieTwo(int dieTwo)
	{
		this.dieTwo = dieTwo;
	}
}
