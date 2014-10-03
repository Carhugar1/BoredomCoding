package database;

import java.util.Comparator;

public class Alphabetical implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		return o1.Name.compareTo(o2.Name);
	}

}
