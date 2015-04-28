package Fields;
import Game.GUIController;
import Player.Player;


public abstract class GOwnable extends GField {

	private int price;
	private Player owner;
	private boolean pawn;
	
	public abstract void removeOwner(Player player, int fieldnumber, GUIController GGUI);
	
	public abstract int getRent(); 

	public void setPawn (boolean setNew)
	{
		this.pawn = setNew;
	}
	
	public boolean getPawn ()
	{
		return this.pawn;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isOwned(){
		if(owner == null){
			return false;
		}
		else{
			return true;
		}
	}

	public Player getOwner() {
		return owner;
	}

	public int getPrice() {
		return price;
	}
	
}
