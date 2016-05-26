package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dysk {
	
	public int iloscBlokow;
	public int liczbaOdwolan;
	public Blok[] bloki;
	public List<Odwolanie> odwolania;
	
	public Dysk(int iloscBlokow, int liczbaOdwolan){
		this.iloscBlokow = iloscBlokow;
		this.liczbaOdwolan = liczbaOdwolan;
		init();
	}
	
	public Dysk(Dysk dysk){
		this.iloscBlokow = dysk.iloscBlokow;
		this.liczbaOdwolan = dysk.liczbaOdwolan;
		bloki = new Blok[iloscBlokow];
		odwolania = new ArrayList<>();
		for(int i = 0 ; i < iloscBlokow ; i++){
			bloki[i] = new Blok();
		}
		for(Odwolanie odwolanie : dysk.odwolania){
			odwolania.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok));
			bloki[odwolanie.blok].odwolania.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok));
		}
	}
	
	public void init(){
		Random random = new Random();
		bloki = new Blok[iloscBlokow];
		odwolania = new ArrayList<>();
		for(int i = 0 ; i < iloscBlokow ; i++){
			bloki[i] = new Blok();
		}
		int blok = 0;
		for(int i = 0 ; i < liczbaOdwolan ; i++){
			blok = random.nextInt(iloscBlokow);
			Odwolanie o = new Odwolanie(random.nextInt(21), blok);
			odwolania.add(o);
			bloki[blok].odwolania.add(o);
		}
		for(int i = 0 ; i < iloscBlokow ; i++){
			Collections.sort(bloki[i].odwolania, new OdwolanieComparator());
		}
		Collections.sort(odwolania, new OdwolanieComparator());
	}
	
	public Boolean czyJestOdwolanieDlaBlokuWCzasie(int blok, int czas){
		if(bloki[blok].odwolania.isEmpty()){
			return false;
		} else if(bloki[blok].odwolania.get(0).czasPrzyjscia <= czas){
			return true;
		}
		return false;
	}

}
