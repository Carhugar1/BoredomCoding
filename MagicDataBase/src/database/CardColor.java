package database;

public class CardColor implements Comparable<Object> {

	private String Color;
	
	private int ColorValue;
	
	public CardColor(String Color) {
		this.Color = Color;
		this.setColorValue();
	}
	
	@Override
	public int compareTo(Object arg0) {
		if (arg0.getClass() != this.getClass() || arg0 == null) {
			throw new IllegalArgumentException();
		}
		
		else {
			CardColor Other = (CardColor) arg0;
			
			return this.ColorValue - Other.ColorValue;
		}
	}
	
	public String getColor() {
		return Color;
	}
	
	public void setColor(String Color) {
		this.Color = Color;
		this.setColorValue();
	}
	
	private void setColorValue() {
		
		if (Color.equals("White")) {
			this.ColorValue = 1;
		}
		
		else if (Color.equals("Blue")) {
			this.ColorValue = 2;
		}
		
		else if (Color.equals("Black")) {
			this.ColorValue = 3;
		}
		
		else if (Color.equals("Red")) {
			this.ColorValue = 4;
		}
		
		else if (Color.equals("Green")) {
			this.ColorValue = 5;
		}
		
		else if (Color.equals("Colorless")) {
			this.ColorValue = 6;
		}
		
		else if (Color.equals("MultiColor")) {
			this.ColorValue = 7;
		}
		
		else if (Color.equals("Land")) {
			this.ColorValue = 8;
		}
		
		else {
			this.ColorValue = Integer.MAX_VALUE;
		}
	}
	
} // end of CardColor class
