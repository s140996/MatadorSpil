import java.awt.Color;

import desktop_codebehind.Car;
import desktop_fields.*;
import desktop_resources.GUI;

public class GUIController {

	private Color[] color = {Color.RED, Color.blue, Color.green, Color.yellow, Color.black, Color.white};
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
	
	public void createGameboard(GameBoard gb)
	{
		GField[] list = gb.getFieldList();
		
		Field[] fields = new Field[list.length];

		for (int i = 1; i < list.length; i++) {
			GField field = gb.getField(i);
			String Type = field.getType();

			switch (Type) {

			case "Start":
				//GStart start = (GStart) field;
				fields[i] = new Street.Builder().build();
				break;

			case "Territory":
				//GTerritory territory = (GTerritory) field;
				fields[i] = new Street.Builder()
				//.setTitle(territory.getName())
				//.setDescription("John")
				//.setSubText("Poul")
				//.setBgColor(field.getColor())
				.build();
				break;

			case "Chance":
				//GChance chance = (GChance) field;
				fields[i] = new Street.Builder()
				.build();
				break;

			case "Fleet":
				//GFleet fleet = (GFleet) field;
				fields[i] = new Street.Builder()
				.build();
				break;

			case "Tax":
				//GTax tax = (GTax) field;
				fields[i] = new Street.Builder()
				.build();
				break;

			case "Brewery":
				//GBrewery brewery = (GBrewery) field;
				fields[i] = new Street.Builder()
				.build();
				break;
				
			case "Prison":
				//GPrison prison = (GPrison) field;
				fields[i] = new Street.Builder()
				.build();
				break;

			case "Parking":
				//GParking parking = (GParking) field;
				fields[i] = new Street.Builder()
				.build();
				break;
				
			case "GoPrison":
				//GGoPrison goPrison = (GGoPrison) field;
				fields[i] = new Street.Builder()
				.build();
				break;
				
			}
		}
		
		GUI.create(fields);
	}
	
	
}
