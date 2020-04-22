import java.io.Serializable;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class represents a standard letter tile.
 * 
 * @author Team 51
 *
 */

public class LetterTilePicture extends Parent implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Letters {
		A(1, 9), B(3, 2), C(3, 2), D(2, 4), E(1, 12), F(4, 2), G(2, 3), H(4, 2), I(1, 9), J(8, 1), K(5, 1), L(1, 4),
		M(3, 2), N(1, 6), O(1, 8), P(3, 2), Q(10, 1), R(1, 6), S(1, 4), T(1, 6), U(1, 4), V(4, 2), W(4, 2), X(8, 1),
		Y(4, 2), Z(10, 1);

		int points;
		int quantity;

		private Letters(int points, int quantity) {
			this.points = points;
			this.quantity = quantity;
			// TODO Auto-generated constructor stub
		}

	}

	public final Letters letter;
	public int quantity;
	public int points;
	public BoardTile isInBoardTile;

	/**
	 * Initialized a LetterTile with given letter and value
	 * 
	 * @param letter - object of type Letters
	 * @param value  - points of each letter
	 */
	public LetterTilePicture(Letters letter) {
		this.letter = letter;
		this.points = letter.points;
		Rectangle piece = new Rectangle(40, 40);
		piece.setArcHeight(20);
		piece.setArcWidth(20);
		piece.setFill(Color.BISQUE);
		Text text1 = new Text(LettertoString() + PointtoString());
		Text text2 = new Text(PointtoString());
		text1.setWrappingWidth(6);
		text2.setWrappingWidth(7);
		Label label = new Label(LettertoString() + "\n" + PointtoString());
		// text2.setTextOrigin(VPos.BOTTOM);
		StackPane st = new StackPane(piece, label);
		// StackPane st = new StackPane(piece, text1);
		getChildren().add(st);
		// getChildren().add(new StackPane(piece, text1));
	}
	

	/**
	 * This method casts objects of Letters to string.
	 * 
	 * @return - a string
	 */
	public String LettertoString() {
		return letter.toString();
	}

	/**
	 * This method casts integer to string.
	 * 
	 * @return - a string
	 */
	public String PointtoString() {
		return Integer.toString(points);
	}

	public void handle() {
		/* drag was detected, start a drag-and-drop gesture */
		/* allow any transfer mode */
		Dragboard db = startDragAndDrop(TransferMode.ANY);

		/* Put a string on a dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(letter.toString());
		db.setContent(content);

	}

}
