package Game;
import java.awt.Color;

import Fields.GBrewery;
import Fields.GChance;
import Fields.GField;
import Fields.GFleet;
import Fields.GGoPrison;
import Fields.GParking;
import Fields.GPrison;
import Fields.GStart;
import Fields.GTax;
import Fields.GTerritory;
import Player.Player;
import desktop_codebehind.Car;
import desktop_fields.*;
import desktop_resources.GUI;

public class GUIController {

	private Color[] color = {Color.red, Color.blue, Color.green, Color.yellow, Color.black, Color.white};
	private int i = 0;


	// *** Add Player ***
	public void addPlayer(String name)
	{
		Car car = new Car.Builder()
		.primaryColor(color[i])
		.typeRacecar()
		.build();
		i++;

		GUI.addPlayer(name, 30000, car);
		GUI.setCar(1, name);
	}

	// *** Load Player ***
	public void loadPlayer(String name, int balance, int position)
	{
		Car car = new Car.Builder()
		.primaryColor(color[i])
		.typeRacecar()
		.build();
		i++;

		GUI.addPlayer(name, balance, car);
		GUI.setCar(position, name);
	}

	// *** Move Car ***
	public void moveCar(int position, String name)
	{
		GUI.removeAllCars(name);
		GUI.setCar(position, name);
	}

	// *** Remove Owner ***
	public void removeOwner(int fieldNumber)
	{
		GUI.removeOwner(fieldNumber);
		GUI.setHouses(fieldNumber, 0);
		GUI.setHotel(fieldNumber, false);
	}

	// *** Set Owner ***
	public void setOwner(int fieldNumber, String name)
	{
		GUI.setOwner(fieldNumber, name);
	}

	// *** Set dice ***
	public void setDice(int faceValue1, int faceValue2)
	{
		GUI.setDice(faceValue1, faceValue2);
	}

	// *** Set Balance ***
	public void setGUIBalance(int newBalance, String name)
	{
		GUI.setBalance(name, newBalance);
	}

	// *** Set Hotel ***
	public void setHotel(int fieldNumber, boolean hasHotel)
	{
		GUI.setHotel(fieldNumber, hasHotel);
	}

	// *** Set Houses ***
	public void setHouses(int fieldNumber, int houseCount)
	{
		GUI.setHouses(fieldNumber, houseCount);
	}

	// *** Close GUI ***
	public void close()
	{
		GUI.close();
	}

	// *** Show Chance Card ***
	public void displayChanceCard(String msg)
	{
		GUI.displayChanceCard(msg);
	}

	// *** User button pressed ***
	public void getUserButtonPressed(String msg, String buttons)
	{
		GUI.getUserButtonPressed(msg, buttons);
	}

	// *** User string ***
	public void getUserString(String msg)
	{
		GUI.getUserString(msg);
	}

	// *** Show message ***
	public void showMessage(String msg)
	{
		GUI.showMessage(msg);
	}

	// *** Create Bool-Button
	public boolean boolButton(String msg, String trueButton, String falseButton)
	{
		boolean reply = GUI.getUserLeftButtonPressed(msg, trueButton, falseButton);
		return reply;
	}

	// *** Player loose ***
	public void playerLost(String name)
	{
		GUI.removeAllCars(name);
	}

	// *** Create GameBoard ***
	public void createGameboard(GameBoard gb)
	{
		GField[] list = gb.getFieldList();

		Field[] fields = new Field[list.length];

		for (int i = 0; i < list.length; i++) {
			GField field = gb.getField(i);
			String Type = field.getType();

			switch (Type) {

			case "Start":
				GStart start = (GStart) field;
				fields[i] = new Start.Builder()
				.setBgColor(start.getColor())
				.setTitle(start.getName())
				.setFgColor(Color.white)
				.setDescription("Modtag 4000,-, når du passerer start!")
				.setSubText("Modtag: 4000")
				.build();
				break;

			case "Territory":
				GTerritory territory = (GTerritory) field;
				fields[i] = new Street.Builder()
				.setTitle(territory.getName())
				.setDescription("Hus/hotel-pris: " + territory.getBuildPrice() + ",-")
				.setSubText("Pris: " + territory.getPrice() + ",-")
				.setBgColor(territory.getColor())
				.build();
				break;

			case "Chance":
				GChance chance = (GChance) field;
				fields[i] = new Chance.Builder()
				.setBgColor(chance.getColor())
				.build();
				break;

			case "Fleet":
				GFleet fleet = (GFleet) field;
				fields[i] = new Shipping.Builder()
				.setTitle("")
				.setSubText("Pris: " + fleet.getPrice() + ",-")
				.setDescription(fleet.getName())
				.setBgColor(fleet.getColor())
				.build();
				break;

			case "Tax":
				GTax tax = (GTax) field;

				if (tax.getPercentageTax() != 0) 
				{
					fields[i] = new Tax.Builder()
					.setTitle("Betal: " + tax.getBasetax())
					.setDescription(tax.getName())
					.setSubText("Du kan vælge at betale " + tax.getBasetax() + ",- eller " + (int) tax.getPercentageTax() + "% af, hvad du har af værdier!")
					.setBgColor(tax.getColor())
					.build();
				}
				else 
				{
					fields[i] = new Tax.Builder()
					.setTitle("Betal: " + tax.getBasetax())
					.setDescription(tax.getName())
					.setSubText("Du skal betale en skat på " + tax.getBasetax() + ",-!")
					.setBgColor(tax.getColor())
					.build();
				}
				break;

			case "Brewery":
				GBrewery brewery = (GBrewery) field;
				fields[i] = new Brewery.Builder()
				.setTitle(brewery.getName())
				.setDescription("Leje: Ejes én virksomhed, så betales " + brewery.getRent() + ",- gange øjnene, ellers hvis det er den samme ejer ved begge virksomheder, så betales " + 2 * brewery.getRent() + ",- gange øjnene.")
				.setSubText("Pris: " + brewery.getPrice())
				.build();
				break;

			case "Prison":
				GPrison prison = (GPrison) field;
				fields[i] = new Jail.Builder()
				.setSubText(prison.getName())
				.setDescription("På besøg i fængslet.")
				.build();
				break;

			case "Parking":
				GParking parking = (GParking) field;
				fields[i] = new Refuge.Builder()
				.setTitle("")
				.setSubText(parking.getName())
				.setDescription("Flot parkering! Helt inde for stregerne :)")
				.build();
				break;

			case "GoPrison":
				GGoPrison goPrison = (GGoPrison) field;
				fields[i] = new Jail.Builder()
				.setSubText(goPrison.getName())
				.setDescription("Én, to, mange, du er taget til fange!")
				.build();
				break;

			}
		}

		GUI.create(fields);
	}

	// *** Choose number of players ***
	public int amountOfPlayers()
	{
		int pick = 0;
		String ans = GUI.getUserButtonPressed("Vælg antal spillere:", "2", "3", "4", "5", "6");

		switch (ans) {
		case "2": pick = 2; break;
		case "3": pick = 3; break;
		case "4": pick = 4; break;
		case "5": pick = 5; break;
		case "6": pick = 6; break;
		}

		return pick;
	}

	// *** Type player name ***
	public String newPlayer(int playerNo)
	{
		boolean dauda = true;
		String ans = "";

		while (dauda == true)
		{
			ans = GUI.getUserString("Skriv navnet på spiller " + playerNo + ":");

			if(ans.equals(""))
			{
				showMessage("Du skal indtaste et navn!");
			}
			else
			{
				dauda = false;
			}
		}

		return ans;
	}

	// *** Turn overview ***
	public String turn(Player player)
	{
		String ans = GUI.getUserButtonPressed("Det er " + player.toString() + "'s tur!", "Kast terninger", "Pantsæt", "Sælg huse", "Sælg hotel", "Gem spil");
		return ans;
	}

	// *** Startup ***
	public String startMenu()
	{
		String ans = GUI.getUserButtonPressed("Velkommen!", "Nyt spil", "Load spil");
		return ans;
	}

}
