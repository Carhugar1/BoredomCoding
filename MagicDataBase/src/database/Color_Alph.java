package database;

import java.util.Comparator;

public class Color_Alph implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		if (o1.Color.compareTo(o2.Color) == 0) {
			return o1.Name.compareTo(o2.Name);
		}
		
		else {
			return o1.Color.compareTo(o2.Color);
		}
	}

}
