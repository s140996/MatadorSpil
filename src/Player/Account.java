package Player;

public class Account {
	
	private int balance;
	
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
	
	public void deposit(int newCash)
	{
		this.balance = this.balance + newCash;
	}

	
}
