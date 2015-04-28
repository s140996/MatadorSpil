package Fields;
import java.awt.Color;

import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;


public class GStart extends GField {

	public GStart (int id)
	{
		super.setID(id);
		super.setType("Start");
		super.setColor(Color.black);
		super.setName("Start");
	}
	
	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOwner(Player player, int fieldnumber, GUIController GGUI) {
		// TODO Auto-generated method stub
		
	}

}
