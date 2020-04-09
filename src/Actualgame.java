
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Actualgame {
	private AlertBox ab;
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
	private ArrayList<LetterTilePic> rackletters = new ArrayList<LetterTilePic>();
	private LetterTilePic currentTile;
	private BoardTile destinationspot;
	DataFormat LetterTilePic = new DataFormat("some string that identifies your object");

	/**
	 * This gets the actual game screen when you click start running.
	 * 
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
		// distribute letters
		bottomPane.getChildren().addAll(bottomBar, userBar);
		rackletters = lb.getLetters(7);
		for (LetterTilePic letter : rackletters) {
			userBar.getChildren().add(letter);
			// letter.onMouseDragEnteredProperty();
			letter.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start a drag-and-drop gesture */
					/* allow any transfer mode */
					currentTile = letter;
					Dragboard db = currentTile.startDragAndDrop(TransferMode.MOVE);
					/* Put a string on a dragboard */
					ClipboardContent content = new ClipboardContent();
					content.putString(letter.toString());
					content.put(LetterTilePic, currentTile);
					// content.getFiles();
					currentTile.getClass();
					db.setContent(content);
					event.consume();
				}
			});

			board.setOnDragDropped(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* data dropped */
					/* if there is a string data on dragboard, read it and use it */
					Dragboard db = event.getDragboard();
					board.getTile().holds = (LetterTilePic) event.getAcceptingObject();
					boolean success = false;
					if (board.getTile().holds != null) {
						success = true;
					}
					/*
					 * let the source know whether the string was successfully transferred and used
					 */
					event.setDropCompleted(success);
					event.consume();
				}
			});
			letter.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag and drop gesture ended */
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
						userBar.getChildren().remove(letter);
					}
					event.consume();
				}
			});
		}

		Rectangle rect = new Rectangle(tile_w, tile_h);
		rect.setId("Tiles");// used for css maybe later haven't set up much there yet
		// bottomPane.getChildren().addAll(bottomBar, userBar);
		gameRoot.setCenter(board);// put the board in the middle
		gameRoot.setBottom(bottomPane);// put the letter rack in the bottom of screen

	}

	/**
	 * This method gets the BorderPane layout- gameRoot
	 * 
	 * @return gameRoot - the layout
	 */
	public Pane getRootPane() {
		return gameRoot;
	}

	public void moveTile(LetterTilePic lp) {
		BoardTile bt = board.getTile();
		if (bt.holds == null) {
			bt.holds = lp;
		}
		// else {
		// ab.alert("Error", "Spot taken.");
		// }
	}
}
