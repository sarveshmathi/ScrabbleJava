import LetterTilePic.Letters;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Parent;

/**
 * Represents one tile in the 15 x 15 board
 * 
 * @author lourdes
 *
 */
public class BoardTile2 extends Parent {
	private int tileNumber; // from 0 to 224
	private String tileType; // none(N), double letter (DL), double word (DW), triple letter (TL), triple
								// word (TW)
	private Rectangle image;
	public BoardTile2(int tileNumber, String tileType) {
		this.tileNumber = tileNumber;
		this.tileType = tileType;
		Rectangle piece = new Rectangle(40, 40);
		//piece.setArcHeight(20);
			//piece.setArcWidth(20);
			piece.setFill(Color.WHITE);
			Text text1 = new Text(tileType);
			//Text text2 = new Text(PointtoString());
			text1.setWrappingWidth(6);
			//text2.setWrappingWidth(7);
			// text2.setTextOrigin(VPos.BOTTOM);
			getChildren().add(new StackPane(piece, text1));
		
		
		
		
		
	}

	//public String tileNumbertoString() {
		//return Integer.toString(tileNumber);
	//}

	//public String PointtoString() {
		//return Integer.toString(points);
	//}
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
