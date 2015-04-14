
public class ChanceCard {

	private String text;
	private int cash;
	private int position;
	private int houseTax;
	private int hotelTax;
	private boolean all;
	private boolean rich;
	
	public ChanceCard (String text, int cash, int position)
	{
		this.text = text;
		this.cash = cash;
		this.position = position;
	}
	
	// Matadorlegatet
	public ChanceCard (String text, int cash, boolean rich)
	{
		this.text = text;
		this.cash = cash;
		this.rich = rich;
	}
	
	//Ejendomsskat
	public ChanceCard (int houseTax, int hotelTax, String text, boolean all)
	{
		this.text = text;
		this.hotelTax = houseTax;
		this.hotelTax = hotelTax;
		this.all = all;
	}
	
	//Fødselsdag
	public ChanceCard (String text, int cash)
	{
		this.text = text;
		this.cash = cash;
		
		
	}
	

	public int getCash()
	{
		return this.cash;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public String toString()
	{
		return this.text;
	}
	
}
