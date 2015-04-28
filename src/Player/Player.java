package Player;
import Game.GUIController;

public class Player {
	
	private String name;
	private int worth;
	private int position;
	private int prisonCard;
	private int fleetsOwned;
	private int brewerysOwned;
	private int houseCount;
	private int hotelCount;
	private boolean convict;
	private boolean alive;
	
	
	public Account acc = new Account(30000);
	
	public Player(String name, int worth, int position, int prisonCard, int fleetsOwned, int brewerysOwned, int houseCount, int hotelCount, boolean convict, boolean alive)
	{
		this.name = name;
		this.worth = worth;
		this.position = position;
		this.prisonCard = prisonCard;
		this.fleetsOwned = fleetsOwned;
		this.brewerysOwned = brewerysOwned;
		this.houseCount = houseCount;
		this.hotelCount = hotelCount;
		this.convict = false;
		this.alive = true;
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
		
		if (this.position > 40)
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
	
	public void setHouseCount(int addHouseCount)
	{
		this.houseCount = this.houseCount + addHouseCount;
	}
	
	public int getHouseCount()
	{
		return houseCount;
	}
	
	public void setHotelCount(int addHotelCount)
	{
		this.hotelCount = this.hotelCount + addHotelCount;
	}
	
	public int getHotelCount()
	{
		return hotelCount;
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
	
	
}

