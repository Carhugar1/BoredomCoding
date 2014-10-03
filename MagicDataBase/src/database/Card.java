package database;

	public class Card {
		
		public CardColor Color;
		
		public String Name;
		
		public int Quantity;
		
		public Card(String Color, String Name, int Number) {
			this.Color = new CardColor(Color);
			this.Name = Name;
			this.Quantity = Number;
		}
		
		
	} // end of the public class Card
	