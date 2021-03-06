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

	public class GameBoard {

		private GField[] list = new GField[40];

		public GameBoard()
		{ 
			list[0] = new GStart(1);
			list[1] = new GTerritory(2, "Rødovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000, new Color(77, 77, 242));
			list[2] = new GChance(3, "Prøv lykken");
			list[3] = new GTerritory(4, "Hvidovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000, new Color(77, 77, 242));
			list[4] = new GTax(5, "Indkomstskat", 4000, 10); 
			list[5] = new GFleet(6, "LB Færgerne", 4000, 500);
			list[6] = new GTerritory(7, "Roskildevej", 2000, 100, 1000, 600, 1800, 5400, 8000, 11000, new Color(246, 94, 5));
			list[7] = new GChance(8, "Prøv lykken");
			list[8] = new GTerritory(9, "Valby Langgade", 2000, 100, 1000, 600, 1800, 5400, 8000, 11000, new Color(246, 94, 5));
			list[9] = new GTerritory(10, "Allégade", 2400, 150, 1000, 800, 2000, 6000, 9000, 12000, new Color(246, 94, 5));
			list[10] = new GPrison(11, "Fængsel");
			list[11] = new GTerritory(12, "Frederiksberg Allé", 2800, 200, 2000, 1000, 3000, 9000, 12500, 15000, new Color(206, 246, 5));
			list[12] = new GBrewery(13, "Tuborg", 3000, 100);
			list[13] = new GTerritory(14, "Bülowsvej", 2800, 200, 2000, 1000, 3000, 9000, 12500, 15000, new Color(206, 246, 5));
			list[14] = new GTerritory(15, "Gammel Kongevej", 3200, 250, 2000, 1250, 3750, 10000, 14000, 18000, new Color(206, 246, 5));
			list[15] = new GFleet(16, "Danmark", 4000, 500);
			list[16] = new GTerritory(17, "Bernstorfsvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
			list[17] = new GChance(18, "Prøv lykken");
			list[18] = new GTerritory(19, "Hellerupvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
			list[19] = new GTerritory(20, "Strandvej", 4000, 350, 2000, 1600, 4400, 12000, 16000, 20000, new Color(129, 129, 129));
			list[20] = new GParking(21, "Parkering");
			list[21] = new GTerritory(22, "Trianglen", 4400, 350, 3000, 1800, 5000, 14000, 17500, 21000, new Color(255, 0, 0));
			list[22] = new GChance(23, "Prøv lykken");
			list[23] = new GTerritory(24, "Østerbrogade", 4400, 350, 3000, 1800, 5000, 14000, 17500, 21000, new Color(255, 0, 0));
			list[24] = new GTerritory(25, "Grønningen", 4800, 400, 3000, 2000, 6000, 15000, 18500, 22000, new Color(255, 0, 0));
			list[25] = new GFleet(26, "Mols-Linien A/S", 4000, 500);
			list[26] = new GTerritory(27, "Bredgade", 5200, 450, 3000, 2200, 6600, 16000, 19500, 23000, new Color(255, 255, 255));
			list[27] = new GTerritory(28, "Kgs. Nytorv", 5200, 450, 3000, 2200, 6600, 16000, 19500, 23000, new Color(255, 255, 255));
			list[28] = new GBrewery(29, "Carlsberg", 3000, 100);
			list[29] = new GTerritory(30, "Østergade", 5600, 500, 3000, 2400, 7200, 17000, 20500, 24000, new Color(255, 255, 255));
			list[30] = new GGoPrison(31, "Gå i fængsel");
			list[31] = new GTerritory(32, "Amagertorv", 6000, 550, 4000, 2600, 7800, 18000, 22000, 25000, new Color(255, 255, 0));
			list[32] = new GTerritory(33, "Vimmelskaftet", 6000, 550, 4000, 2600, 7800, 18000, 22000, 25000, new Color(255, 255, 0));
			list[33] = new GChance(34, "Prøv lykken");
			list[34] = new GTerritory(35, "Nygade", 6400, 600, 4000, 3000, 9000, 20000, 24000, 28000, new Color(255, 255, 0));
			list[35] = new GFleet(36, "Skandinavisk Linietrafik A/S", 4000, 500);
			list[36] = new GChance(37, "Prøv lykken");
			list[37] = new GTerritory(38, "Frederiks- bergsgade", 7000, 700, 4000, 3500, 10000, 22000, 26000, 30000, new Color(168, 53, 137));
			list[38] = new GTax(39,"Ekstra- ordinær statsskat", 2000, 0);
			list[39] = new GTerritory(40, "Rådhus- pladsen", 8000, 1000, 4000, 4000, 12000, 28000, 34000, 40000, new Color(168, 53, 137));		
		}

		public GField getField(int id){
			GField field = list[id];
			return field;
		}
		
		public GField[] getFieldList()
		{
			return list;
		}

	}
	
