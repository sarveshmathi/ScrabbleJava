import java.io.Serializable;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class represents one tile in the 15 x 15 board.
 * 
 * @author Team 51
 *
 */
public class BoardTile extends Parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tileNumber; // from 0 to 224
	private String tileType; // none(N), double letter (DL), double word (DW), triple letter (TL), triple
								// word (TW)
	public LetterTilePicture holds;// this is the letter piece it will hold

	/**
	 * This constructs objects of the type BoardTile
	 * 
	 * @param tileNumber - the positional number of tile
	 * @param tileType   - the property of tile
	 */
	public BoardTile(int tileNumber, String tileType) {
		this.tileNumber = tileNumber;
		this.tileType = tileType;
		// holds = null;
		Rectangle border = new Rectangle(40, 40);
		border.setFill(Color.BEIGE);
		border.setStroke(Color.BLACK);
		if (holds == null) {
			if (!tileType.equals("N")) {
				Text text1 = new Text(getTileType());
				getChildren().add(new StackPane(border, text1));
			} else {
				getChildren().add(new StackPane(border));
			}
		} else {
			getChildren().add(new StackPane(holds));
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

	public LetterTilePicture getholds() {
		return holds;
	}

	/**
	 * This method sets the property of tile.
	 * 
	 * @param tileType - the property of tile
	 */
	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

	/**
	 * This makes what appears on the screen to be the tile that the place on the
	 * board is holding IF it is holding any tile.
	 * 
	 */
	public void displayHold() {
		if (this.holds != null) {
			StackPane sp = new StackPane(this.holds);
			sp.setId("holdpic");
			getChildren().add(sp);
		}
	}

	/**
	 * This method will remove any tile it is holding. It will only keep on its
	 * stack pane the default tile image which is either blank of DW DL etc if it is
	 * special.
	 * 
	 */
	public void remove() {
		getChildren().remove(1, getChildren().size());

	}

}
