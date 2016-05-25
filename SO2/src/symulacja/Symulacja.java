package symulacja;

import algorytmy.C_SCAN;
import algorytmy.FCFS;
import algorytmy.SCAN;
import algorytmy.SSTF;

public class Symulacja {

	public static void main(String[] args) {
		FCFS fcfs = new FCFS();
		fcfs.wykonaj();
		System.out.println("Iloœæ przesuniêæ FCSF: " + fcfs.iloscPrzesuniec);
		SSTF sstf = new SSTF();
		sstf.wykonaj();
		System.out.println("Iloœæ przesuniêæ SSTF: " + sstf.iloscPrzesuniec);
		SCAN scan = new SCAN();
		scan.wykonaj();
		System.out.println("Iloœæ przesuniêæ SCAN: " + scan.iloscPrzesuniec);
		C_SCAN c_scan = new C_SCAN();
		c_scan.wykonaj();
		System.out.println("Iloœæ przesuniêæ C_SCAN: " + c_scan.iloscPrzesuniec);
	}

}
