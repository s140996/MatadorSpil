package Fields;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;


public class GGoPrison extends GField{

	public GGoPrison(int id, String name)
	{
		super.setID(id);
		super.setName(name);
		super.setType("GoPrison");
	}
	
	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) 
	{
		GGUI.showMessage("Desværre, du skal i fængsel!!");
		player.setPosition(11);
		player.setConvict(true);
		GGUI.moveCar(11, player.toString());
		cup.resetDoubleRoll();
	}

	
	
}
