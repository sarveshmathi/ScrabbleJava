import java.util.ArrayList;

public class Player {
	private int number; //player 0 or 1
	private String name; //player name
	private int score; 
	private ArrayList<LetterTile> letterRack; //should always have 7 letters at the end of the turn unless there's none left in the bag
	
	public Player(int number, String name) {
		this.number = number; 
		this.name = name;
		this.score = 0;
		this.letterRack  = new ArrayList<>();
	}
	
}
