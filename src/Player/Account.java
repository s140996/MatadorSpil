package Player;

public class Account {
	
	private int balance;
	private int lastBalance;
	
	public Account(int balance)
	{
		this.balance = balance;
	}

	public void setBalance(int Cash)
	{
		this.balance = Cash;
	}
	
	public int getBalance()
	{
		return balance;
	}
	
	public void setLastBalance(int Cash)
	{
		this.balance = Cash;
	}
	
	public int getLastBalance()
	{
		return this.lastBalance;
	}
	
	public void deposit(int newCash)
	{
		if(newCash >= 0)
		{
			this.balance += newCash;
		}
	}

	public void withdraw(int newCash)
	{
		if(newCash <= this.balance)
		{
			this.balance -= newCash;
		}
		else if(newCash > this.balance)
		{
			this.balance = 0;
		}
		
	}
	
}
