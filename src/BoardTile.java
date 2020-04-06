import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Represents one tile in the 15 x 15 board
 * 
 * @author Team 51
 *
 */
public class BoardTile extends Parent {
	private int tileNumber; // from 0 to 224
	private String tileType; // none(N), double letter (DL), double word (DW), triple letter (TL), triple
								// word (TW)
	private LetterTilePic holds;// this is the letter piece it will hold

	/**
	 * This constructs objects of the type BoardTile
	 * 
	 * @param tileNumber - the positional number of tile
	 * @param tileType   - the property of tile
	 */
	public BoardTile(int tileNumber, String tileType) {
		this.tileNumber = tileNumber;
		this.tileType = tileType;
		Rectangle border = new Rectangle(40, 40);
		border.setFill(Color.BEIGE);
		border.setStroke(Color.BLACK);
		if (!tileType.equals("N")) {
			Text text1 = new Text(getTileType());
			getChildren().add(new StackPane(border, text1));
		} else {
			getChildren().add(new StackPane(border));
		}
	}

	/**
	 * This method gets the tile number.
	 * 
	 * @return tileNumber - the positional number of tile
	 */
	public int getTileNumber() {
		return tileNumber;
	}

	/**
	 * This method gets the tile type.
	 * 
	 * @return - the property of tile
	 */
	public String getTileType() {
		return tileType;
	}

	/**
	 * This method sets the property of tile.
	 * 
	 * @param tileType - the property of tile
	 */
	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

}
