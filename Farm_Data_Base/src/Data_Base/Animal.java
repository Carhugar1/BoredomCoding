package Data_Base;

public class Animal {

	public String name;
	
	public String[] ids;
	
	public char gender;
	
	public String born; // TODO custom DATE?
	
	public String death; // ^ ?
	
	public String father;
	
	public String mother;
	
	public String[] children;
	
	public String description;
	
	

	
	public Animal() {
		this("", null, ' ', null);
	}
	
	public Animal(String Name, String[] Ids, char Gender, String Born) {
		this(Name, Ids, Gender, Born, null, null, null, null, null);
	}
	
	public Animal(String Name, String[] Ids, char Gender, String Born, String Death, String Father, String Mother, String[] Children, String Description) {
		this.name = Name;
		this.ids = Ids;
		this.gender = Gender;
		this.born = Born;
		this.death = Death;
		this.father = Father;
		this.mother = Mother;
		this.children = Children;
		this.description = Description;
	}
	
	public boolean isDead() {
		return death != null;
	}
	
	public String gender() {
		return gender == 'M' ? "Male" : "Female";
	}
	
	public int numberChildren() {
		return children.length;
	}
	
	public void addChild(String ChildId) {
		int temp = children.length;
		children = new String[temp + 1];
		children[temp] = ChildId;
	}
	
	public String[] getChildren(String Year) {
		// Custom DATE?
	}
	
}
