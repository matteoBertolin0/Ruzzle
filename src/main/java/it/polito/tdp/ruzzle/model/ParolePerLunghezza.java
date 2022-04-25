package it.polito.tdp.ruzzle.model;

import java.util.Comparator;

public class ParolePerLunghezza implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		String s1 = (String) o1;
		String s2 = (String) o2;
		int l1 = s1.length();
		int l2 = s2.length();
		return -(l1-l2);
	}

}
