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
	
			// *** Move Car ***
	public void moveCar(int position, String name, int lastRoll)
	{
		GUI.removeCar(position, name);
		GUI.setCar(position + lastRoll, name);
	}
	
			// *** Set Balance ***
	public void setGUIBalance(int newBalance, String name)
	{
		GUI.setBalance(name, newBalance);
	}
	
}
