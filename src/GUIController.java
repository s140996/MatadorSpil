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

		for (int i = 0; i < list.length; i++) {
			GField field = gb.getField(i);
			String Type = field.getType();

			switch (Type) {

			case "Start":
				GStart start = (GStart) field;
				fields[i] = new Start.Builder()
				.setTitle(start.getName())
				.setDescription("")
				.setSubText("")
				.setBgColor(field.getColor())
				.build();
				break;

			case "Territory":
				GTerritory territory = (GTerritory) field;
				fields[i] = new Street.Builder()
				.setTitle(territory.getName())
				.setDescription(Txt.Rent + territory.getRent())
				.setSubText(Txt.Price + territory.getPrice())
				.setBgColor(field.getColor())
				.build();
				break;

			case "Chance":
				GChance chance = (GChance) field;
				fields[i] = new Chance.Builder()
				.build();
				break;

			case "Fleet":
				GFleet fleet = (GFleet) field;
				fields[i] = new Shipping.Builder()
				.setTitle(refuge.getName())
				.setDescription(Txt.Bonus + refuge.getBonus())
				.setSubText("")
				.build();
				break;

			case "Tax":
				GTax tax = (GTax) field;
				fields[i] = new Tax.Builder()
				.setTitle(tax.getName())
				.setDescription(Txt.Rent + Txt.LaborCamp)
				.setSubText(Txt.Price + tax.getPrice())
				.build();
				break;

			case "Tax":
				FieldTax tax = (FieldTax) field;
				if (tax.getTaxRate() == 0) {
					fields[i] = new Street.Builder()
					.setTitle(tax.getName())
					.setDescription(Txt.Pay + tax.getTaxAmount())
					.setSubText("")
					.setBgColor(new Color(255,255,0))
					.build();
				}
				else {
					fields[i] = new Street.Builder()
					.setTitle(tax.getName())
					.setDescription(Txt.Pay + tax.getTaxAmount() + Txt.Pay2 + tax.getTaxRatePro() + Txt.Pay3)
					.setSubText("")
					.setBgColor(new Color(255,255,0))
					.build();
				}
				break;

			}
		}
		
		GUI.create(fields);
	}
	
	
}
