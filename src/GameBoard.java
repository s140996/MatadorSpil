
	import java.awt.Color;
	import java.util.*;

	import desktop_fields.*;
	import desktop_resources.GUI;

	public class GameBoard {

		private GField[] list = new GField[40];

		public GameBoard()
		{
			list[1] = new GStart(1);
			list[2] = new GTerritory(2, "Rødovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000, new Color(17, 17, 94));
			list[3] = new Territory(100, 1000, "Tribe Encampment", 3);
			list[4] = new GTerritory(4, "Hvidovrevej", 1200, 50, 1000, 250, 750, 2250, 4000, 6000, new Color(17, 17, 94));
			list[5] = new Territory(300, 1500, "Crater", 5);
			list[6] = new Fleet(4000, "Second Sail", 6);
			list[7] = new GTerritory(7, "Roskildevej", 2000, 100, 1000, 600, 1800, 5400, 8000, 11000, new Color(246, 94, 5));
			list[8] = new FieldTax(2000, 0, "Goldmine", 8);
			list[9] = new GTerritory(9, "Valby Langgade", 2000, 100, 1000, 600, 1800, 5400, 8000, 11000, new Color(246, 94, 5));
			list[10] = new GTerritory(10, "Allégade", 2400, 150, 1000, 800, 2000, 6000, 9000, 12000, new Color(246, 94, 5));
			list[11] = new Territory(1600, 4750, "Mountain Village", 11);
			list[12] = new GTerritory(12, "Frederiksberg Allé", 2800, 200, 2000, 1000, 3000, 9000, 12500, 15000, new Color(206, 246, 5));
			list[13] = new Territory(2000, 5000, "South Citadel", 13);
			list[14] = new GTerritory(14, "Bülowsvej", 2800, 200, 2000, 1000, 3000, 9000, 12500, 15000, new Color(206, 246, 5));
			list[15] = new GTerritory(15, "Gammel Kongevej", 3200, 250, 2000, 1250, 3750, 10000, 14000, 18000, new Color(206, 246, 5));
			list[16] = new FieldTax(4000, 10, "Caravan", 16);
			list[17] = new GTerritory(17, "Bernstorfsvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
			list[18] = new LaborCamp(2500, "The Pit", 18);
			list[19] = new GTerritory(19, "Hellerupvej", 3600, 300, 2000, 1400, 4000, 11000, 15000, 19000, new Color(129, 129, 129));
			list[20] = new GTerritory(20, "Strandvej", 4000, 350, 2000, 1600, 4400, 12000, 16000, 20000, new Color(129, 129, 129));
			list[21] = new Fleet(4000, "Privateer Armade", 21);
			list[22] = new GTerritory(22, "Trianglen", 4400, 350, 3000, 1800, 5000, 14000, 17500, 21000, new Color(255, 0, 0));
			list[23] = new GTerritory(23,);
			list[24] = new GTerritory(24, "Østerbrogade", 4400, 350, 3000, 1800, 5000, 14000, 17500, 21000, new Color(255, 0, 0));
			list[25] = new GTerritory(25, "Grønningen", 4800, 400, 3000, 2000, 6000, 15000, 18500, 22000, new Color(255, 0, 0));
			list[26] = new GTerritory(26,);
			list[27] = new GTerritory(27, "Bredgade", 5200, 450, 3000, 2200, 6600, 16000, 19500, 23000, new Color(255, 255, 255));
			list[28] = new GTerritory(28, "Kgs. Nytorv", 5200, 450, 3000, 2200, 6600, 16000, 19500, 23000, new Color(255, 255, 255));
			list[29] = new GTerritory(29,);
			list[30] = new GTerritory(30, "Østergade", 5600, 500, 3000, 2400, 7200, 17000, 20500, 24000, new Color(255, 255, 255));
			list[31] = new GTerritory(31, );
			list[32] = new GTerritory(32, "Amagertorv", 6000, 550, 4000, 2600, 7800, 18000, 22000, 25000, new Color(255, 255, 0));
			list[33] = new GTerritory(33, "Vimmelskaftet", 6000, 550, 4000, 2600, 7800, 18000, 22000, 25000, new Color(255, 255, 0));
			list[34] = new GTerritory(34, );
			list[35] = new GTerritory(35, "Nygade", 6400, 600, 4000, 3000, 9000, 20000, 24000, 28000, new Color(255, 255, 0));
			list[36] = new GTerritory(36, );
			list[37] = new GTerritory(37, );
			list[38] = new GTerritory(38, "Frederiksberggade", 7000, 700, 4000, 3500, 10000, 22000, 26000, 30000, new Color(168, 53, 137));
			list[39] = new GTerritory(39,);
			list[40] = new GTerritory(40, "Rådhuspladsen", 8000, 1000, 4000, 4000, 12000, 28000, 34000, 40000, new Color(168, 53, 137));
						
		}

		public void randomizer() {
			Collections.shuffle(Arrays.asList(list));
		}

		public GameFields getField(int id){
			GameFields field = list[id];
			return field;
		}

		public int getStartNumber() {
			String Type = "";
			int i = 0;

			while (Type != "Start") {
				GameFields field = getField(i);
				Type = field.getType();
				i++;
			}

			return i;
		}

		public void create() {
			Field[] fields = new Field[list.length];

			for (int i = 0; i < list.length; i++) {
				GameFields field = getField(i);
				String Type = field.getType();

				switch (Type) {

				case "Start":
					Startfield start = (Startfield) field;
					fields[i] = new Start.Builder()
					.setTitle(start.getName())
					.setDescription("")
					.setSubText("")
					.setBgColor(Color.WHITE)
					.build();
					break;

				case "Territory":
					Territory territory = (Territory) field;
					fields[i] = new Street.Builder()
					.setTitle(territory.getName())
					.setDescription(Txt.Rent + territory.getRent())
					.setSubText(Txt.Price + territory.getPrice())
					.setBgColor(new Color(147,159,80))
					.build();
					break;

				case "Fleet":
					Fleet fleet = (Fleet) field;
					fields[i] = new Street.Builder()
					.setTitle(fleet.getName())
					.setDescription(Txt.Rent + Txt.Fleet)
					.setSubText(Txt.Price + fleet.getPrice())
					.setBgColor(new Color(7,140,255))
					.build();
					break;

				case "Refuge":
					FieldRefuge refuge = (FieldRefuge) field;
					fields[i] = new Street.Builder()
					.setTitle(refuge.getName())
					.setDescription(Txt.Bonus + refuge.getBonus())
					.setSubText("")
					.setBgColor(new Color(255,153,47))
					.build();
					break;

				case "LaborCamp":
					LaborCamp lc = (LaborCamp) field;
					fields[i] = new Street.Builder()
					.setTitle(lc.getName())
					.setDescription(Txt.Rent + Txt.LaborCamp)
					.setSubText(Txt.Price + lc.getPrice())
					.setBgColor(new Color(255,0,0))
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

		public String toString() {
			String s = "";
			for (int i = 0; i < list.length; i++) {
				s = s + list[i].getName() + "\n";
			}
			return s;
		}


	}
	
