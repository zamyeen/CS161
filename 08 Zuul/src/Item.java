//Item Class is a constructor and is called in Game.java
public class Item {
	
	//Creating a description String
	String description;
	//Method that takes in string and makes it equal to description
	public Item(String newdescription) {
		description = newdescription;
	}
	//When description is called for description is returned
	public String getDescription() {
		return description;
	}
}