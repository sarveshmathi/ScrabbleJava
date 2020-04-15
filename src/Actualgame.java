
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
	Button confirmWord;
	Label lastPlayedWord;
	String currentPlayer;
	String totalScorePlayerOne;
	String totalScorePlayerTwo;
	private Player player1;
	private Player player2;
	private Scoring scoring;

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
		scorePlayerOne = new Label();
		scorePlayerTwo = new Label();
		playerOneLabel = new Label("Player One");
		playerTwoLabel = new Label("Player Two");
		howToPlay = new Button("How To Play");
		confirmWord = new Button("Confirm Word");
		lastPlayedWord = new Label();
		currentPlayer = "";
		totalScorePlayerOne = "0";
		totalScorePlayerTwo = "0";
		scoring = new Scoring();
		confirmButton = "";
		word = "";
		player1 = new Player(1);
		player2 = new Player(2);
		this.ActualGame();
	}

	public void ActualGame() {
		gameRoot.setPrefSize(board_w, board_h);// size of the Pane which ends up being the board
		gameRoot.setId("playingpage");
		LetterBag2 lb = new LetterBag2();
		LetterTilePic lp;
		// creates the user letter bar
		StackPane bottomPanel = new StackPane();
		StackPane bottomPanel2 = new StackPane();// for user 2
		Button reset = new Button("Reset");
		Button refill = new Button("Refill");
		// topPane.setAlignment(Pos.TOP_RIGHT);
		// topPane.getChildren().add(reset);

		Button forfeitTurn = new Button("Forfeit Turn");
		inputWord.setPromptText("Player One's Turn");
		scorePlayerOne.setText(totalScorePlayerOne);
		scorePlayerTwo.setText(totalScorePlayerTwo);

		// Right Layout Starts
		VBox howToPlayLayout = new VBox();
		howToPlayLayout.setAlignment(Pos.CENTER);
		howToPlayLayout.getChildren().add(howToPlay);

		VBox confirmLayout = new VBox(5);
		confirmLayout.setAlignment(Pos.CENTER);
		confirmLayout.getChildren().addAll(enteredWord, inputWord, confirmWord);

		VBox forfeitResetLayout = new VBox(5);
		forfeitResetLayout.setAlignment(Pos.CENTER);
		forfeitResetLayout.getChildren().addAll(forfeitTurn, reset, refill);

		VBox bottomRightLayout = new VBox(20);
		bottomRightLayout.setAlignment(Pos.CENTER);
		bottomRightLayout.getChildren().addAll(confirmLayout, forfeitResetLayout);

		VBox rightPanel = new VBox(400);
		rightPanel.setAlignment(Pos.CENTER);
		rightPanel.getChildren().addAll(howToPlayLayout, bottomRightLayout);
		// Right Layout Ends

		// Adding the radio buttons to a toggle group
		playerOne.setToggleGroup(toggleGroup);
		playerTwo.setToggleGroup(toggleGroup);

		// This listens for change in radio buttons playerOne and playerTwo
		toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
			// Cast newVal to RadioButton.
			RadioButton rb = (RadioButton) newVal;
			currentPlayer = rb.getText();
			// Display the selection.
			player.setText("Current player: " + currentPlayer);
			if (playerOne.isSelected() == true) {
				gameRoot.setBottom(bottomPanel);// put the letter rack in the bottom of screen
			} else if (playerTwo.isSelected() == true) {
				gameRoot.setBottom(bottomPanel2);// put the letter rack in the bottom of screen
			}
			board.turnoffdrag();
		});
		playerOne.setSelected(true);

		// Left Layout Starts
		HBox playerOnePanel = new HBox(10);
		playerOnePanel.getChildren().addAll(playerOneLabel, scorePlayerOne);

		HBox playerTwoPanel = new HBox(10);
		playerTwoPanel.getChildren().addAll(playerTwoLabel, scorePlayerTwo);

		VBox playerPanel = new VBox(20);
		playerPanel.setAlignment(Pos.BOTTOM_CENTER);
		playerPanel.getChildren().addAll(playerOnePanel, playerTwoPanel);

		VBox lastPlayedWordPanel = new VBox();
		lastPlayedWordPanel.setAlignment(Pos.TOP_CENTER);
		lastPlayedWordPanel.getChildren().add(lastPlayedWord);

		VBox leftPanel = new VBox(400);
		leftPanel.setAlignment(Pos.CENTER);
		leftPanel.getChildren().addAll(lastPlayedWordPanel, playerPanel);
		// Left Layout Ends

		// Top Layout Starts
		StackPane topPanel = new StackPane();
		topPanel.setAlignment(Pos.CENTER);
		topPanel.getChildren().addAll(player);
		// Top Layout Ends

		// Bottom layout
		bottomPanel.setAlignment(Pos.CENTER);
		bottomPanel2.setAlignment(Pos.CENTER);

		// Tooltips
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

		// this makes rack for each player
		player1.rackletters = makerack(bottomPanel, lb);
		player2.rackletters = makerack(bottomPanel2, lb);

		/*
		 * This is to let the player forfeit their turn.
		 * 
		 */
		forfeitTurn.setOnAction(e -> {
			this.forfeitTurn();
		});

		/*
		 * This resets the game.
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

		if (playerOne.isSelected() == true) {
			gameRoot.setBottom(bottomPanel);// put the letter rack in the bottom of screen
		} else if (playerTwo.isSelected() == true) {
			gameRoot.setBottom(bottomPanel2);// put the letter rack in the bottom of screen
		}
		// gameRoot.setBottom(bottomPanel);// put the letter rack in the bottom of
		// screen
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

		boolean userResponse;
		String tempCurrentPlayer = "";

		if (currentPlayer.equals("Player One")) {
			tempCurrentPlayer = "Player Two";
		} else if (currentPlayer.equals("Player Two")) {
			tempCurrentPlayer = "Player One";
		}

		userResponse = AlertBox.alertWithUserAction("Forfeit Turn", "Forfeit " + currentPlayer + "'s turn?");

		if (userResponse) {
			inputWord.setPromptText(tempCurrentPlayer + "'s Turn");
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
			inputWord.clear();
			lastPlayedWord.setText("");
		}
	}

	public void confirmWord() {
		String playedWord = inputWord.getText().trim();
		String wordToTest = "";
		String tempWord = word;
		String tempCurrentPlayer = "";

		enteredWord.setText(""); // to clear the text shown for previous player before turn changes
		inputWord.clear();

		String str = playedWord.toLowerCase();
		boolean onlyEnglishAlphabet = true;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!(ch >= 'a' && ch <= 'z' || ch == ',' || ch == ' ')) {
				onlyEnglishAlphabet = false;
			}

		}

		if (currentPlayer.equals("Player One")) {
			tempCurrentPlayer = "Player Two";
		} else if (currentPlayer.equals("Player Two")) {
			tempCurrentPlayer = "Player One";
		}

		if (playedWord.equals("")) {
			enteredWord.setText("Enter a word.");
		} else if (!onlyEnglishAlphabet) {
			enteredWord.setText("English alphabets only.");
		} else // all filters passed
		{

			wordToTest = playedWord;
			String status = "";
			String points = "";

			// Test codes begin

			ArrayList<String> wordToTestArray = new ArrayList<String>();
			wordToTestArray.add(wordToTest);
			// int score = Scoring.checkInput(wordToTestArray);
			// tester for player1 and player 2 correctly
			if (playerOne.isSelected()) {
				scoring.checkInputboard(wordToTestArray, board, player1);
				System.out.println(player1.score);
			}
			if (playerTwo.isSelected()) {
				scoring.checkInputboard(wordToTestArray, board, player2);
				System.out.println(player2.score);
			}

			if (currentPlayer.equals("Player One")) {
				int playerOneScore = player1.score;
				scorePlayerOne.setText("" + playerOneScore);

				totalScorePlayerOne = "" + playerOneScore;
			} else if (currentPlayer.equals("Player Two")) {
				int playerTwoScore = player2.score;
				scorePlayerTwo.setText("" + playerTwoScore);
				totalScorePlayerTwo = "" + playerTwoScore;
			} else {
				status = "Denied";
				points = "0";
			}

			// status = "Accepted"; points = "" + score;
			if (currentPlayer.equals("Player One")) {
				int playerOneScore = player1.score;
				scorePlayerOne.setText("" + player1.score);
				totalScorePlayerOne = "" + playerOneScore;
			} else if (currentPlayer.equals("Player Two")) {
				int playerTwoScore = player2.score;
				scorePlayerTwo.setText("" + playerTwoScore);
				totalScorePlayerTwo = "" + playerTwoScore;
			} else {
				status = "Denied";
				points = "0";
			}

			/**
			 * if (score != -1) { status = "Accepted"; points = "" + score; if
			 * (currentPlayer.equals("Player One")) { int playerOneScore =
			 * Integer.parseInt(totalScorePlayerOne) + score; scorePlayerOne.setText("" +
			 * playerOneScore);
			 * 
			 * totalScorePlayerOne = "" + playerOneScore; } else if
			 * (currentPlayer.equals("Player Two")) { int playerTwoScore =
			 * Integer.parseInt(totalScorePlayerTwo) + score; scorePlayerTwo.setText("" +
			 * playerTwoScore); totalScorePlayerTwo = "" + playerTwoScore; } } else { status
			 * = "Denied"; points = "0"; }
			 */

			// Test codes end

			lastPlayedWord.setText("Last played " + tempWord + ":\n" + wordToTest.toUpperCase() + "\nBy: "
					+ currentPlayer + "\nStatus: " + status + "\nPoints: " + points);
			inputWord.setPromptText(tempCurrentPlayer + "'s Turn");

			if (playerOne.isSelected()) {
				playerTwo.fire();
			} else if (playerTwo.isSelected()) {
				playerOne.fire();
			}

		}

	}

	public void displayTextField() {
		String playedWord = inputWord.getText();
		String str = playedWord.toLowerCase();
		boolean onlyEnglishAlphabet = true;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!(ch >= 'a' && ch <= 'z' || ch == ',' || ch == ' ')) {
				onlyEnglishAlphabet = false;
			}

		}
		if (playedWord.equals("")) {
			enteredWord.setText("Enter a word.");
		} else if (!onlyEnglishAlphabet) {
			enteredWord.setText("English alphabets only.");

		} else {
			enteredWord.setText("Press " + confirmButton + ".");
		}
	}

	public void howToPlay() {
		AlertBox.alertWithoutUserAction("How To Play",
				"Player One goes first.\n\n" + "Drag and drop tiles on the board.\n\n"
						+ "When you are finished, type the word in the text box.\n\n"
						+ "If you made more than one word, separate them with comma.\n\n"
						+ "Press Confirm Word/s button to play the word(s).\n\n"
						+ "Confirm Word/s button automatically changes turn.\n\n"
						+ "Press Fofriet Turn button to forfeit current player's turn.\n\n"
						+ "Press Reset button to reset the game.");
	}

	/**
	 * this method makes a letter rack
	 */
	public ArrayList<LetterTilePic> makerack(StackPane bottomPanel, LetterBag2 lb) {
		Rectangle bottomBar = new Rectangle(420, 60);
		bottomBar.setArcWidth(30.0);
		bottomBar.setArcHeight(30.0);
		bottomBar.setFill(Color.SIENNA);
		HBox userBar = new HBox(2);
		userBar.setAlignment(Pos.CENTER);
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
					if (letter.isin != null) {
						letter.isin.holds = null;
					}
					currentTile = letter;
					Dragboard db = currentTile.startDragAndDrop(TransferMode.MOVE);
					/* Put a string on a dragboard */
					ClipboardContent content = new ClipboardContent();
					content.putString(letter.toString());
					content.put(LetterTilePic, currentTile);
					// content.getFiles();
					currentTile.getClass();
					db.setContent(content);
					userBar.getChildren().remove(letter);
					rackletters.remove(letter);

					event.consume();
					System.out.println("i'm being touched");
				}
			});

			letter.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag and drop gesture ended */
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() != TransferMode.MOVE) {
						userBar.getChildren().add(letter);
						rackletters.add(letter);
					}
					event.consume();
				}
			});
		}
		userBar.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				// System.out.println("test1");
				/* data is dragged over the target */
				/*
				 * accept it only if it is not dragged from the same node and if it isn't
				 * holding a card
				 */
				if (event.getGestureSource() != userBar) {
					/* allow for both copying and moving, whatever user chooses */
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();
			}
		});
		userBar.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				Object j = db.getContent(LetterTilePic);
				// bt.holds = (LetterTilePic) event.getGestureSource();
				boolean success = false;
				if (userBar.getChildren().size() <= 7) {
					success = true;
					userBar.getChildren().add((LetterTilePic) event.getGestureSource());
				}
				/*
				 * let the source know whether the string was successfully transferred and used
				 */
				event.setDropCompleted(success);
				event.consume();
			}
		});
		return rackletters;
	}

}
