
public abstract class GOwnable extends GField {

	private int price;
	private Player owner;
	
	public abstract int getRent(); 

	
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
