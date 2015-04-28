package Fields;
import ChanceCard.ChanceCardList;
import Die.Cup;
import Game.GUIController;
import Game.GameBoard;
import Player.Player;

public class GPrison extends GField {
	
	public GPrison (int id, String name)
	{
		super.setID(id);
		super.setName(name);
		super.setType("Prison");
	}

	@Override
	public void landOnField(Player player, GUIController GGUI, ChanceCardList cc, Cup cup, GameBoard gb) {
		GGUI.showMessage("Du er på besøg i fængslet. Giv agt!");		
	}


}
