
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Actualgame {
	private final Pane gameroot = new Pane();
	private Board board = new Board();
	private int tile_in_row = 15;
	private int tile_in_col = 15;
	private int board_w = 800;
	private int board_h = 800;
	private int tile_w = board_w / tile_in_row;
	private int tile_h = board_h / tile_in_col;
	private Rectangle border = new Rectangle(tile_w - 2, tile_h - 2);

	public Actualgame() {
		gameroot.setPrefSize(board_w, board_h);
		gameroot.setId("playingpage");
		// border.setStroke(Color.WHITE);
		// gameroot.getChildren().add(border);
		// for (int i = 0; i <= 15; i = i + 15) {
		// for (int j = 0; j <= 15; j = j + 15) {
		LetterTilePic lp = new LetterTilePic(LetterTilePic.Letters.A);
		Rectangle rect = new Rectangle(tile_w, tile_h);
		rect.setId("Tiles");
		lp.getOnDragDetected();
		// Text text = new Text("hi");
		gameroot.getChildren().addAll(lp);
		// }
		// }
	}

	public Pane getRootPane() {
		return gameroot;
	}

}
