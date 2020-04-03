import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Represents one tile in the 15 x 15 board
 * 
 * @author sarvesh
 *
 */
public class BoardTile extends Parent {
	private int tileNumber; // from 0 to 224
	private String tileType; // none(N), double letter (DL), double word (DW), triple letter (TL), triple
								// word (TW)

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

	public int getTileNumber() {
		return tileNumber;
	}

	public String getTileType() {
		return tileType;
	}

	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

}
