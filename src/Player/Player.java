package Player;
import Game.GUIController;

public class Player {
	
	private String name;
	private int worth;
	private int position;
	private int prisonCard;
	private int fleetsOwned;
	private int brewerysOwned;
	private boolean lastPosition;
	private boolean convict;
	private boolean alive;
	
	
	public Account acc = new Account(30000);
	
	public Player(String name, int worth, int position, int prisonCard, int fleetsOwned, int brewerysOwned, boolean lastPosition, boolean convict, boolean alive)
	{
		this.name = name;
		this.worth = worth; 
		this.position = position;
		this.prisonCard = prisonCard;
		this.fleetsOwned = fleetsOwned;
		this.brewerysOwned = brewerysOwned;
		this.lastPosition = lastPosition;
		this.convict = convict;
		this.alive = alive;
	}
	
	public void setPosition(int newPosition)
	{
		this.position = newPosition;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public void changePosition(int diceRoll, GUIController GGUI)
	{
		this.position = this.position + diceRoll;
		
		if (this.lastPosition == true)
		{
			this.lastPosition = false;
			this.acc.deposit(4000);
			GGUI.setGUIBalance(this.acc.getBalance(), this.name);
		}
		
		if (this.position == 41)
		{
			this.position = this.position - 40;
			this.lastPosition = true;
		}
		else if (this.position > 40)
		{
			this.position = this.position - 40;
			this.acc.deposit(4000);
			GGUI.setGUIBalance(this.acc.getBalance(), this.name);
		}
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public int getWorth()
	{
		return this.worth + this.acc.getBalance();
	}
	
	public void setWorth(int worth)
	{
		this.worth = this.worth + worth;
	}
	
	
	public int getPrisonCard()
	{
		return prisonCard;
	}
	
	public void setPrisonCard(int prisonCard)
	{
		this.prisonCard = prisonCard;
	}
	
	public void setFleetsOwned(int fleetsOwned)
	{
		this.fleetsOwned = fleetsOwned;
	}
	
	public int getFleetsOwned()
	{
		return fleetsOwned;
	}
	
	public void setBrewerysOwned(int brewerysOwned)
	{
		this.brewerysOwned = brewerysOwned;
	}
	
	public int getBrewerysOwned()
	{
		return brewerysOwned;
	}
	
	public void setConvict(boolean convict)
	{
		this.convict = convict;
	}
	
	public boolean getConvict()
	{
		return convict;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public boolean getAlive()
	{
		return alive;
	}
	
	public int getConvictDB()
	{
		if (this.convict == true)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public int getAliveDB()
	{
		if (this.alive == true)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public void goToStart()
	{
		this.lastPosition = true;
		this.position = 1;
	}
	
	public boolean getLastPosition()
	{
		return this.lastPosition;
	}
	
}


