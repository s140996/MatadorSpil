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
		GGUI.showMessage("Modtag 4000");
		
	}


}
