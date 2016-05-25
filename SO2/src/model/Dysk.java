package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dysk {
	
	public Blok[] bloki;
	public List<Odwolanie> odwolania;
	
	public Dysk(){
		init();
	}
	
	public Dysk(Dysk dysk){
		bloki = new Blok[dysk.bloki.length];
		odwolania = new ArrayList<>();
		for(int i = 0 ; i < bloki.length ; i++){
			bloki[i] = new Blok();
		}
		for(Odwolanie odwolanie : dysk.odwolania){
			odwolania.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok));
			bloki[odwolanie.blok].odwolania.add(new Odwolanie(odwolanie.czasPrzyjscia, odwolanie.blok));
		}
	}
	
	public void init(){
		Random random = new Random();
		bloki = new Blok[100];
		odwolania = new ArrayList<>();
		for(int i = 0 ; i < bloki.length ; i++){
			bloki[i] = new Blok();
		}
		int blok = 0;
		for(int i = 0 ; i < 100 ; i++){
			blok = random.nextInt(100);
			Odwolanie o = new Odwolanie(random.nextInt(21), blok);
			odwolania.add(o);
			bloki[blok].odwolania.add(o);
		}
		Collections.sort(odwolania, new OdwolanieComparator());
	}

}
