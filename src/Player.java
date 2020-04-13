import java.util.ArrayList;

/**
 * This class deals with the players.
 * @author Team 51
 *
 */

public class Player {
	private int number; // player 0 or 1
	private String name; // player name
	private int score;

	/**
	 * This constructs an object of the type Player.
	 * @param number - the player's number
	 * @param name - the player's name
	 */
	public Player(int number, String name) {
		this.number = number;
		this.name = name;
		this.score = 0;
	}

}
