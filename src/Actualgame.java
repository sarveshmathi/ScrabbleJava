
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;;

public class Actualgame {
	private BoardTile[] tiles;
	private final BorderPane gameRoot = new BorderPane();
	private Board board = new Board();
	private int tile_in_row = 15;// board spaces per row
	private int tile_in_col = 15;// board spaces per col
	private int board_w = 800;// board size
	private int board_h = 800;
	private int tile_w = board_w / tile_in_row;
	private int tile_h = board_h / tile_in_col;
	private Rectangle border = new Rectangle(tile_w - 2, tile_h - 2);
	private ArrayList<LetterTilePic> rackletters = new ArrayList<LetterTilePic>();;

	/**
	 * This gets the actual game screen when you click start running.
	 * @author Team 51
	 */
	public Actualgame() {
		gameRoot.setPrefSize(board_w, board_h);// size of the Pane which ends up being the board
		gameRoot.setId("playingpage");
		LetterBag2 lb = new LetterBag2();
		LetterTilePic lp;
		// creates the user letter bar
		StackPane bottomPane = new StackPane();
		Rectangle bottomBar = new Rectangle(420, 60);
		bottomBar.setFill(Color.BLACK);
		HBox userBar = new HBox(2);
		userBar.setAlignment(Pos.CENTER);
		rackletters = lb.getLetters(7);
		for (LetterTilePic letter : rackletters) {
			userBar.getChildren().add(letter);

		}
		// LetterTilePic lp = new LetterTilePic(LetterTilePic.Letters.A);// this is just
		// a tester
		Rectangle rect = new Rectangle(tile_w, tile_h);
		rect.setId("Tiles");// used for css maybe later haven't set up much there yet
		// lp.getOnDragDetected();// doesn't work but i meant for this to make the tiles
		// draggable for user
		bottomPane.getChildren().addAll(bottomBar, userBar);
		gameRoot.setCenter(board);// put the board in the middle
		gameRoot.setBottom(bottomPane);// put the letter rack in the bottom of screen
		// gameroot.getChildren().addAll(board, bottomPane);// this gets these elements
		// onto the screen(pane)
	}

	/**
	 * This method gets the BorderPane layout- gameRoot 
	 * @return
	 */
	public Pane getRootPane() {
		return gameRoot;
	}

	public void addtorack() {
	}

}
