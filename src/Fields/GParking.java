package Fields;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;


public class GParking extends GField {

	public GParking (int id, String name)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Parking");
	}
	
	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		GGUI.showMessage("Velkommen til parkeringspladsen!! Ta' en pause!!");
		
	}


}
