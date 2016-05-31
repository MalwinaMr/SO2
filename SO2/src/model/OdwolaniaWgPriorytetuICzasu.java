package model;

import java.util.Comparator;

public class OdwolaniaWgPriorytetuICzasu implements Comparator<Odwolanie> {

	@Override
	public int compare(Odwolanie o1, Odwolanie o2){
		if(o1.priorytet < o2.priorytet){
			return -1;
		} else if(o1.priorytet == o2.priorytet){
			if(o1.czasPrzyjscia < o2.czasPrzyjscia){
				return -1;
			} else if(o1.czasPrzyjscia > o2.czasPrzyjscia){
				return 1;
			}
			return 0;
		} else {
			return 1;
		}
	}
}