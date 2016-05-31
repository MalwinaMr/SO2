package model;

import java.util.Comparator;

public class OdwolaniaWgCzasuPrzyjscia implements Comparator<Odwolanie> {

	@Override
	public int compare(Odwolanie o1, Odwolanie o2){
		if(o1.czasPrzyjscia < o2.czasPrzyjscia){
			return -1;
		} else if(o1.czasPrzyjscia == o2.czasPrzyjscia){
			return 0;
		} else {
			return 1;
		}
	}
}