
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Actualgame {
	private AlertBox ab;
	private BoardTile[] tiles;
	private final BorderPane gameRoot;
	private Board board;
	private int tile_in_row;
	private int tile_in_col;
	private int board_w;
	private int board_h;
	private int tile_w;
	private int tile_h;
	private Rectangle border;
	private ArrayList<LetterTilePic> rackletters;
	private LetterTilePic currentTile;
	private BoardTile destinationspot;
	private DataFormat LetterTilePic;
	private RadioButton playerOne;
	private RadioButton playerTwo;
	private ToggleGroup toggleGroup;
	private TextField inputWord;
	private Label enteredWord;
	private Label player;
	private Label scorePlayerOne;
	private Label scorePlayerTwo;
	private String confirmButton;
	private String word;
	private Label playerOneLabel;
	private Label playerTwoLabel;
	private Button howToPlay;

	/**
	 * This gets the actual game screen when you click start running.
	 * 
	 * @author Team 51
	 *
	 */

	public Actualgame() {
		tile_in_row = 15;// board spaces per row
		tile_in_col = 15;// board spaces per col
		board_w = 800;// board size
		board_h = 800;

		gameRoot = new BorderPane();
		board = new Board();
		tile_w = board_w / tile_in_row;
		tile_h = board_h / tile_in_col;
		border = new Rectangle(tile_w - 2, tile_h - 2);
		rackletters = new ArrayList<LetterTilePic>();
		LetterTilePic = new DataFormat("some string that identifies your object");

		playerOne = new RadioButton("Player One");
		playerTwo = new RadioButton("Player Two");
		toggleGroup = new ToggleGroup();
		inputWord = new TextField();
		enteredWord = new Label();
		player = new Label();
		scorePlayerOne = new Label("Score");
		scorePlayerTwo = new Label("Score");
		playerOneLabel = new Label ("Player One");
		playerTwoLabel = new Label ("Player Two");
		howToPlay = new Button ("How To Play");
		
		confirmButton = "";
		word = "";
		

		this.ActualGame();
	}

	public void ActualGame() {
		gameRoot.setPrefSize(board_w, board_h);// size of the Pane which ends up being the board
		gameRoot.setId("playingpage");
		LetterBag2 lb = new LetterBag2();
		LetterTilePic lp;
		// creates the user letter bar
		StackPane bottomPanel = new StackPane();
		Rectangle bottomBar = new Rectangle(420, 60);
		bottomBar.setArcWidth(30.0); 
	    bottomBar.setArcHeight(30.0); 
		bottomBar.setFill(Color.SIENNA);
		HBox userBar = new HBox(2);
		userBar.setAlignment(Pos.CENTER);
		Button reset = new Button("Reset");
		// topPane.setAlignment(Pos.TOP_RIGHT);
		// topPane.getChildren().add(reset);
		
		Button forfeitTurn = new Button("Forfeit Turn");
		Button confirmWord = new Button("Confirm Word");
		confirmWord.requestFocus();
		inputWord.setPromptText("Input Your Word Here");
		
		//Right Layout Starts
		VBox howToPlayLayout = new VBox();
		howToPlayLayout.setAlignment(Pos.TOP_CENTER);
		howToPlayLayout.getChildren().add(howToPlay);

		VBox confirmLayout = new VBox(5);
		confirmLayout.setAlignment(Pos.CENTER);
		confirmLayout.getChildren().addAll(enteredWord, inputWord, confirmWord);
		
		VBox forfeitResetLayout = new VBox(5);
		forfeitResetLayout.setAlignment(Pos.CENTER);
		forfeitResetLayout.getChildren().addAll(forfeitTurn, reset);
		
		VBox bottomRightLayout = new VBox(20);
		bottomRightLayout.setAlignment(Pos.BOTTOM_CENTER);
		bottomRightLayout.getChildren().addAll(confirmLayout, forfeitResetLayout);


		VBox rightPanel = new VBox(400);
		rightPanel.setAlignment(Pos.TOP_LEFT);
		rightPanel.getChildren().addAll(howToPlayLayout, bottomRightLayout);
		//Right Layout Ends
		
		// Adding the radio buttons to a toggle group
		playerOne.setToggleGroup(toggleGroup);
		playerTwo.setToggleGroup(toggleGroup);
		
		
		// This listens for change in radio buttons playerOne and playerTwo
		toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
			// Cast newVal to RadioButton.
			RadioButton rb = (RadioButton) newVal;

			// Display the selection.
			player.setText("Current player: " + rb.getText());

		});
		playerOne.setSelected(true);
		
		
		//Left Layout Starts
		HBox playerOnePanel = new HBox (10);
		playerOnePanel.getChildren().addAll(playerOneLabel, scorePlayerOne);
		
		HBox playerTwoPanel = new HBox (10);
		playerTwoPanel.getChildren().addAll(playerTwoLabel, scorePlayerTwo);
		
		VBox playerPanel = new VBox(20);
		playerPanel.setAlignment(Pos.BOTTOM_CENTER);
		playerPanel.getChildren().addAll(playerOnePanel, playerTwoPanel);
		
		Label mockLabel = new Label();
		
		VBox mockPanel = new VBox();
		mockPanel.setAlignment(Pos.TOP_CENTER);
		mockPanel.getChildren().add(mockLabel);
		
		
		VBox leftPanel = new VBox (400);
		leftPanel.setAlignment(Pos.CENTER);
		leftPanel.getChildren().addAll(mockPanel, playerPanel);
		//Left Layout Ends
		
		
		StackPane topPanel = new StackPane();
		topPanel.setAlignment(Pos.CENTER);
		topPanel.getChildren().addAll(player);
		
		//Tooltips
		inputWord.setTooltip(new Tooltip("Separate multiple words by comma."));
		forfeitTurn.setTooltip(new Tooltip("Press to forfiet your turn."));
		confirmWord.setTooltip(new Tooltip("Press to confirm."));
		reset.setTooltip(new Tooltip("Press to reset the game."));
		howToPlay.setTooltip(new Tooltip("Press for \"how to\" guide."));

		// This changes the text in confirmWord button to "Confirm Words" if more than 1
		// words are entered
		inputWord.textProperty().addListener((observable, oldValue, newValue) -> {
			int count = 0;
			for (int i = 0; i < newValue.length(); i++) {
				if (newValue.charAt(i) == ',') {
					count++;
				}
			}
			if (count > 0) {
				confirmWord.setText("Confirm Words");
				confirmButton = "Confirm Words";
				word = "words";
			} else {
				confirmWord.setText("Confirm Word");
				confirmButton = "Confirm Word";
				word = "word";
			}
			
			if (newValue.equals("")) {
				enteredWord.setText("");
			}
			
			if (oldValue.equals("") && !newValue.equals("")) {
				enteredWord.setText("");
			}
		});

		// distribute letters
		bottomPanel.setAlignment(Pos.CENTER);
		bottomPanel.getChildren().addAll(bottomBar, userBar);
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

		/*
		 * This is to let the player forfeit their turn.
		 * 
		 */
		forfeitTurn.setOnAction(e -> {
			this.forfeitTurn();
		});

		/*
		 * This reset the game.
		 */

		reset.setOnAction(e -> {
			this.resetGame();
		});

		/*
		 * This confirms the word played by the user.
		 */

		confirmWord.setOnAction(e -> {
			this.confirmWord();
		});

		/*
		 * This handles event when the user presses "Enter" on the text field.
		 */

		inputWord.setOnAction(e -> {
			this.displayTextField();
		});
		
		
		howToPlay.setOnAction(e -> {
			this.howToPlay();
		});
		
		Rectangle rect = new Rectangle(tile_w, tile_h);
		rect.setId("Tiles");// used for css maybe later haven't set up much there yet
		// bottomPane.getChildren().addAll(bottomBar, userBar);
		gameRoot.setPadding(new Insets(10, 10, 10, 10));
		gameRoot.setCenter(board);// put the board in the middle
		gameRoot.setBottom(bottomPanel);// put the letter rack in the bottom of screen
		gameRoot.setLeft(leftPanel);
		gameRoot.setRight(rightPanel);
		gameRoot.setTop(topPanel);
		
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

	public void forfeitTurn() {
		
		RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();
		String currentPlayer = rb.getText();
		boolean userResponse;
		userResponse = AlertBox.alertWithUserAction("Forfeit Turn", "Forfeit " + currentPlayer + "'s turn?");
		if (userResponse) {

			if (playerOne.isSelected()) {
				playerTwo.fire();
			} else if (playerTwo.isSelected()) {
				playerOne.fire();
			}

		}
	}

	public void resetGame() {
		boolean userResponse;
		userResponse = AlertBox.alertWithUserAction("Reset Game", "Reset the game?");
		if (userResponse) {
			this.ActualGame();
			board.newboard();
		}
	}

	public void confirmWord() {
		String playedWord = inputWord.getText();
		enteredWord.setText(""); // to clear the text shown for previous player before turn changes
		inputWord.clear();
		
		
		
		if (playedWord.equals("")) {
			enteredWord.setText("Enter a word.");
		}else {
			if (playerOne.isSelected()) {
				playerTwo.fire();
			} else if (playerTwo.isSelected()) {
				playerOne.fire();
			}
		}
		

	}

	public void displayTextField() {
		String playedWord = inputWord.getText();
		if (playedWord.equals("")) {
			enteredWord.setText("Enter a word.");
		} else {
			enteredWord.setText("Press " + confirmButton + ".");
		}
	}
	
	public void howToPlay() {
		AlertBox.alertWithoutUserAction("How To Play", "Player One goes first.\n\n"
				+ "Drag and drop tiles to the board.\n\n"
				+ "When you are finished, type the word in the text box.\n\n"
				+ "If you made more than one word, separate them with comma.\n\n"
				+ "Press Confirm Word/s button to play the word(s).\n\n"
				+ "Confirm Word/s button automatically changes turn.\n\n"
				+ "Press Fofriet Turn button to forfeit current player's turn.\n\n"
				+ "Press Reset button to reset the game.");
	}


}
