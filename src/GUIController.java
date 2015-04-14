import java.awt.Color;

import desktop_codebehind.Car;
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
	
			// *** Create Field ***
	public void createField(GField fields[])
	{
		GUI.create(fields[]);
	}
	
			// *** Remove Car ***
	public void removeCar(int position, String name)
	{
		GUI.removeCar(position, name);
	}
	
			// *** Set Car ***
	public void setCar(int position, String name)
	{
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
	
			
	
	
	
	
	
	
	
}
