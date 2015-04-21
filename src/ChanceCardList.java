import java.util.*;

public class ChanceCardList {

	private int index;
	
	private ArrayList<ChanceCard> list = new ArrayList<ChanceCard>();
	private ChanceCard c1 = new ChanceCard("Du modtager 1000 kr i aktieudbytte.", 1000, 0);
	private ChanceCard c2 = new ChanceCard("Ryk frem til start.", 0, 1);
	private ChanceCard c3 = new ChanceCard("Gå i fængsel.", 0, 11);
	private ChanceCard c4 = new ChanceCard("Gå i fængsel.", 0, 11);
	private ChanceCard c5 = new ChanceCard("Told - for mange cigaretter.", -200, 0);
	private ChanceCard c6 = new ChanceCard("Tandlægeregning.", -2000, 0);
	private ChanceCard c7 = new ChanceCard("Tip 13.", 1000, 0);
	private ChanceCard c8 = new ChanceCard("Præmieobligationer.", 1000, 0);
	private ChanceCard c9 = new ChanceCard("Præmieobligationer.", 1000, 0);
	// Skal have funktion med, så det er alle der betaler til fødselaren
	private ChanceCard c10 = new ChanceCard("Din fødselsdag. Modtag 200 fra hver medspiller", 200);
	private ChanceCard c11 = new ChanceCard("Nyttehaven af avl.", 200, 0);
	private ChanceCard c12 = new ChanceCard("Bilforsikring.", -1000, 0);
	// Ejendomsskat: 800 pr. hus og 2300 pr. hotel (Y)
	private ChanceCard c13 = new ChanceCard(800, 2300, "Ejendomsskatten er steget.", true);
	private ChanceCard c14 = new ChanceCard("Ryk frem til Grønningen", 0, 25);
	// Skal rykkes frem til nærmeste redderi og betale ejeren dobbelt leje
	private ChanceCard c15 = new ChanceCard("Ryk frem til nærmeste redderi og betal dobbelt leje.", 0, 0);
	// Skal rykkes frem til nærmeste redderi og betale ejeren dobbelt leje
	private ChanceCard c16 = new ChanceCard("Ryk frem til nærmeste redderi og betal dobbelt leje.", 0, 0);
	private ChanceCard c17 = new ChanceCard("Tag med LB færgen", 0, 6);
	private ChanceCard c18 = new ChanceCard("Benådes for fængsel", 0, 0);
	private ChanceCard c19 = new ChanceCard("Lønforhøjelse.", 1000, 0);
	private ChanceCard c20 = new ChanceCard("Ryk frem til Frederiksberh Allé.", 0, 12);
	private ChanceCard c21 = new ChanceCard("Vundet i klasselotteriet.", 500, 0);
	private ChanceCard c22 = new ChanceCard("Tag ind til Rådhuspladsen", 0, 40);
	private ChanceCard c23 = new ChanceCard("Ryk tre felter tilbage.", 0, -3);
	// 500 pr. hus og 2000 pr. hotel (Y)
	private ChanceCard c24 = new ChanceCard(500, 2000, "Oliepriserne er steget.", false);
	private ChanceCard c25 = new ChanceCard("Reparation af vogn.", -3000, 0);		
	private ChanceCard c26 = new ChanceCard("Reparation af vogn.", -3000, 0);		
	// +40000, hvis formue ikke overstiger 15000 kr
	private ChanceCard c27 = new ChanceCard("Matadorlegatet.", 40000, false);
	private ChanceCard c28 = new ChanceCard("Eftergivet skat.", 3000, 0);
	private ChanceCard c29 = new ChanceCard("Aktieudbytte.", 1000, 0);
	private ChanceCard c30 = new ChanceCard("Aktieudbytte.", 1000, 0);
	private ChanceCard c31 = new ChanceCard("Kørt frem for Fuld stop. Betal 1000 kr.", -1000, 0);
	private ChanceCard c32 = new ChanceCard("Parkeringsbøde.", -200, 0);
	
	public ChanceCardList()
	{
		index = 0;
		
		list.add(0, c1);
		list.add(1, c2);
		list.add(2, c3);
		list.add(3, c4);
		list.add(4, c5);
		list.add(5, c6);
		list.add(6, c7);
		list.add(7, c8);
		list.add(8, c9);
		list.add(9, c10);
		list.add(10, c11);
		list.add(11, c12);
		list.add(12, c13);
		list.add(13, c14);
		list.add(14, c15);
		list.add(15, c16);
		list.add(16, c17);
		list.add(17, c18);
		list.add(18, c19);
		list.add(19, c20);
		list.add(20, c21);
		list.add(21, c22);
		list.add(22, c23);
		list.add(23, c24);
		list.add(24, c25);
		list.add(25, c26);
		list.add(26, c27);
		list.add(27, c28);
		list.add(28, c29);
		list.add(29, c30);
		list.add(30, c31);
		list.add(31, c32);
	}
	
	 public void randomizer() {
         Collections.shuffle(list);
 }
	 
	 public ChanceCard draw()
	 {
		ChanceCard c;
		c = list.get(index);
		if(index == 31)
		{
			index = 0;
		}
		else
		{
		index++;
		}
		return c;
	 }
	 
	 public static void main(String[] args) {
		
		 ChanceCardList cd = new ChanceCardList();
		 
		 cd.randomizer();
		 
		 System.out.println(cd.draw().getPosition());
		 
		 
//		 
//		 for(int i=0; i<34; i++){
//		 System.out.println(cd.draw().toString());
//		 }
	}
}
