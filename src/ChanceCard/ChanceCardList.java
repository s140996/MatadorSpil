package ChanceCard;
import java.util.*;

public class ChanceCardList {

	private int index;
	
	private ArrayList<ChanceCard> list = new ArrayList<ChanceCard>();
	private ChanceCard c1 = new ChanceCard("CHANCEKORT: Du modtager dit aktieudbytte på 1000,-.", 1000, 0);
	private ChanceCard c2 = new ChanceCard("CHANCEKORT: Ryk frem til start.", 0, 1);
	private ChanceCard c3 = new ChanceCard("CHANCEKORT: Gå i fængsel.", 0, 11);
	private ChanceCard c4 = new ChanceCard("CHANCEKORT: Gå i fængsel.", 0, 11);
	private ChanceCard c5 = new ChanceCard("CHANCEKORT: Du har haft for mange cigaretter med hjem. Betal 200,-. i told-bøde", -200, 0);
	private ChanceCard c6 = new ChanceCard("CHANCEKORT: Du har modtaget din tandlægeregning. Betal 2000,-.", -2000, 0);
	private ChanceCard c7 = new ChanceCard("CHANCEKORT: Du har haft 12 rigtige i tips 13. Modtag 1000,-.", 1000, 0);
	private ChanceCard c8 = new ChanceCard("CHANCEKORT: Din restskat er gået ind. Modtag 1000,-.", 1000, 0);
	private ChanceCard c9 = new ChanceCard("CHANCEKORT: Du har solgt din gamle Velo Solex 3800S. Modtag 1000,-.", 1000, 0);
	// Skal have funktion med, så det er alle der betaler til fødselaren
	private ChanceCard c10 = new ChanceCard("CHANCEKORT: Du er blevet konfirmeret, og har fået 1000,- af din bedstemor.", 1000);
	private ChanceCard c11 = new ChanceCard("CHANCEKORT: Du har fundet en pung på gaden. Stjæl de 200,- der ligger i den.", 200, 0);
	private ChanceCard c12 = new ChanceCard("CHANCEKORT: Du har modtaget din vægtafgift. Betal 1000,-.", -1000, 0);
	// Ejendomsskat: 800 pr. hus og 2300 pr. hotel (Y)
	private ChanceCard c13 = new ChanceCard(800, 2300, "CHANCEKORT: Ejendomsskatten er steget.", true);
	private ChanceCard c14 = new ChanceCard("CHANCEKORT: Ryk frem til Grønningen", 0, 25);
	// Skal rykkes frem til nærmeste redderi og betale ejeren dobbelt leje
	private ChanceCard c15 = new ChanceCard("CHANCEKORT: Ryk frem til nærmeste redderi og betal dobbelt leje.");
	// Skal rykkes frem til nærmeste redderi og betale ejeren dobbelt leje
	private ChanceCard c16 = new ChanceCard("CHANCEKORT: Ryk frem til nærmeste redderi og betal dobbelt leje.");
	private ChanceCard c17 = new ChanceCard("CHANCEKORT: Tag med LB færgen", 0, 6);
	// Benådes for fængsel må ikke rettes, uden også at rette i GChance
	private ChanceCard c18 = new ChanceCard("CHANCEKORT: Du benådes for fængsel", 0, 0);
	private ChanceCard c19 = new ChanceCard("CHANCEKORT: Lønforhøjelse på 1000,-.", 1000, 0);
	private ChanceCard c20 = new ChanceCard("CHANCEKORT: Ryk frem til Frederiksberh Allé.", 0, 12);
	private ChanceCard c21 = new ChanceCard("CHANCEKORT: Vundet i klasselotteriet. Modtag 500,-.", 500, 0);
	private ChanceCard c22 = new ChanceCard("CHANCEKORT: Tag ind til Rådhuspladsen", 0, 40);
	private ChanceCard c23 = new ChanceCard("CHANCEKORT: Ryk tre felter tilbage.", 0, -3);
	// 500 pr. hus og 2000 pr. hotel (Y)
	private ChanceCard c24 = new ChanceCard("CHANCEKORT: Oliepriserne er steget.", true);
	private ChanceCard c25 = new ChanceCard("CHANCEKORT: Reparation af vogn. Betal 3000,-.", -3000, 0);		
	private ChanceCard c26 = new ChanceCard("CHANCEKORT: Reparation af vogn. Betal ,-.", -3000, 0);		
	// +40000, hvis formue ikke overstiger 15000 kr
	private ChanceCard c27 = new ChanceCard("CHANCEKORT: Matadorlegatet.", 40000, false);
	private ChanceCard c28 = new ChanceCard("CHANCEKORT: Eftergivet skat. Modtag 3000,-.", 3000, 0);
	private ChanceCard c29 = new ChanceCard("CHANCEKORT: Aktieudbytte på 1000,-.", 1000, 0);
	private ChanceCard c30 = new ChanceCard("CHANCEKORT: Aktieudbytte på 1000,-.", 1000, 0);
	private ChanceCard c31 = new ChanceCard("CHANCEKORT: Kørt frem for Fuld stop. Betal 1000,-.", -1000, 0);
	private ChanceCard c32 = new ChanceCard("CHANCEKORT: Parkeringsbøde på 200,-.", -200, 0);
	
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
}
