
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
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

public class GamePlay {

	private String totalScorePlayerOne;
	private String totalScorePlayerTwo;
	private String confirmButton;
	private String currentPlayer;

	private int tile_in_row;
	private int tile_in_column;
	private int board_width;
	private int board_height;
	private int tile_width;
	private int tile_height;

	private Rectangle border;

	private final BorderPane gameRoot;
	private StackPane bottomPanel;
	private StackPane bottomPanel2;

	private RadioButton playerOne;
	private RadioButton playerTwo;
	private ToggleGroup toggleGroup;
	private Label enteredWord;
	private Label player;
	private Label scorePlayerOne;
	private Label scorePlayerTwo;
	private Label lastPlayedWord;
	private TextField inputWord;

	private BoardTile[] tiles;
	private Board board;
	private ArrayList<LetterTilePicture> rackletters;
	private LetterTilePicture currentTile;
	private BoardTile destinationspot;
	private DataFormat LetterTilePic;
	private Player player1;
	private Player player2;
	private Scoring scoring;
	private LetterBag letterBag;

	/**
	 * This gets the actual game screen when you click start running.
	 * 
	 */

	public GamePlay() {
		tile_in_row = 15;// board spaces per row
		tile_in_column = 15;// board spaces per col
		board_width = 800;// board size
		board_height = 800;

		gameRoot = new BorderPane();
		board = new Board();
		tile_width = board_width / tile_in_row;
		tile_height = board_height / tile_in_column;
		border = new Rectangle(tile_width - 2, tile_height - 2);

		LetterTilePic = new DataFormat("some string that identifies your object");
		playerOne = new RadioButton("Player One");
		playerTwo = new RadioButton("Player Two");
		toggleGroup = new ToggleGroup();
		inputWord = new TextField();
		enteredWord = new Label();
		player = new Label();
		scorePlayerOne = new Label();
		scorePlayerTwo = new Label();
		lastPlayedWord = new Label();
		currentPlayer = "";
		totalScorePlayerOne = "0";
		totalScorePlayerTwo = "0";

		confirmButton = "";

		this.buildLayout();
	}

	/**
	 * This method builds the layout for the actual game.
	 */

	public void buildLayout() {
		LetterTilePicture lp;
		Button reset = new Button("Reset");
		Button endGame = new Button("End Game");
		Button forfeitTurn = new Button("Forfeit Turn");
		Button howToPlay = new Button("How To Play");
		Button confirmWord = new Button("Confirm Word");
		Label playerOneLabel = new Label("Player One");
		Label playerTwoLabel = new Label("Player Two");

		scoring = new Scoring();
		player1 = new Player(1);
		player2 = new Player(2);
		letterBag = new LetterBag();
		bottomPanel = new StackPane();
		bottomPanel2 = new StackPane();
		rackletters = new ArrayList<LetterTilePicture>();

		gameRoot.setPrefSize(board_width, board_height);// size of the Pane which ends up being the board
		gameRoot.setId("playingpage");

		inputWord.setPromptText("Player One's Turn");
		scorePlayerOne.setText(totalScorePlayerOne);
		scorePlayerTwo.setText(totalScorePlayerTwo);

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
			board.removeTiles();
			rackcheck(player1, letterBag, bottomPanel);
			rackcheck(player2, letterBag, bottomPanel2);
			// board.turnoffdrag();
		});
		// Select Player One to start off with
		playerOne.setSelected(true);

		// Right Layout Starts

		VBox howToPlayLayout = new VBox();
		howToPlayLayout.setAlignment(Pos.CENTER);
		howToPlayLayout.getChildren().addAll(howToPlay);

		VBox confirmLayout = new VBox(5);
		confirmLayout.setAlignment(Pos.CENTER);
		confirmLayout.getChildren().addAll(enteredWord, inputWord, confirmWord);

		VBox forfeitResetLayout = new VBox(5);
		forfeitResetLayout.setAlignment(Pos.CENTER);
		forfeitResetLayout.getChildren().addAll(forfeitTurn, reset, endGame);

		VBox bottomRightLayout = new VBox(20);
		bottomRightLayout.setAlignment(Pos.CENTER);
		bottomRightLayout.getChildren().addAll(confirmLayout, forfeitResetLayout);

		VBox rightPanel = new VBox(400);
		rightPanel.setAlignment(Pos.CENTER);
		rightPanel.getChildren().addAll(howToPlayLayout, bottomRightLayout);
		// Right Layout Ends

		// Left Layout Starts
		Label legends = new Label();
		legends.setText("C: Center\n" + "DL: Double Letter Score\n" + "TL: Triple Letter Score\n"
				+ "DW: Double Word Score\n" + "TW: Triple Word Score");
		HBox playerOnePanel = new HBox(10);
		playerOnePanel.getChildren().addAll(playerOneLabel, scorePlayerOne);

		HBox playerTwoPanel = new HBox(10);
		playerTwoPanel.getChildren().addAll(playerTwoLabel, scorePlayerTwo);

		Separator separator1 = new Separator();
		Separator separator2 = new Separator();
		Separator separator3 = new Separator();
		separator1.setOrientation(Orientation.HORIZONTAL);
		separator2.setOrientation(Orientation.HORIZONTAL);
		separator3.setOrientation(Orientation.HORIZONTAL);

		VBox legendsPanel = new VBox(20);
		legendsPanel.getChildren().addAll(separator3, legends);

		VBox playerPanel = new VBox(20);
		playerPanel.setAlignment(Pos.BOTTOM_CENTER);
		playerPanel.getChildren().addAll(separator1, playerOnePanel, playerTwoPanel, separator2);

		VBox playerLastPlayedWordPanel = new VBox(20);
		// playerLastPlayedWordPanel.setAlignment(Pos.TOP_CENTER);
		playerLastPlayedWordPanel.getChildren().addAll(playerPanel, lastPlayedWord);

		BorderPane leftPanel = new BorderPane();
		leftPanel.setPadding(new Insets(50, 10, 50, 10));
		leftPanel.setTop(playerLastPlayedWordPanel);
		leftPanel.setBottom(legendsPanel);
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
		inputWord.setTooltip(new Tooltip("Enter the word that you played here."));
		forfeitTurn.setTooltip(new Tooltip("Press to forfiet your turn."));
		confirmWord.setTooltip(new Tooltip("Press to confirm."));
		reset.setTooltip(new Tooltip("Press to reset the game."));
		howToPlay.setTooltip(new Tooltip("Press for \"how to\" guide."));
		endGame.setTooltip(new Tooltip("Press to end current game."));

		// this makes rack for each player
		rackcheck(player1, letterBag, bottomPanel);
		rackcheck(player2, letterBag, bottomPanel2);

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
			if (board.checkTogether() == true) {
				if (playerOne.isSelected()) {
					if (player1.rackletters.size() == 7) {
						AlertBox.alertWithoutUserAction("Rack Not Used", "You must use the tile(s) from your rack.");
					} else {
						this.confirmWord();
					}
				} else if (playerTwo.isSelected()) {
					if (player2.rackletters.size() == 7) {
						AlertBox.alertWithoutUserAction("Rack Not Used", "You must use the tile(s) from your rack.");
					} else {
						this.confirmWord();
					}
				}
				// this.confirmWord();
			} else {
				AlertBox.alertWithoutUserAction("Tiles Not Together", "The tiles are not together.");
			}

			// this.confirmWord();
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

		endGame.setOnAction(e -> {
			this.endGame();
		});

		Rectangle rect = new Rectangle(tile_width, tile_height);
		rect.setId("Tiles");// used for css maybe later haven't set up much there yet
		// bottomPane.getChildren().addAll(bottomBar, userBar);
		gameRoot.setPadding(new Insets(10, 10, 10, 10));
		gameRoot.setCenter(board);// put the board in the middle

		if (playerOne.isSelected() == true) {
			gameRoot.setBottom(bottomPanel);// put the letter rack in the bottom of screen
		} else if (playerTwo.isSelected() == true) {
			gameRoot.setBottom(bottomPanel2);// put the letter rack in the bottom of screen
		}

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

	/**
	 * This method moves tiles to a spot in the board if it's empty.
	 * 
	 * @param lp
	 */

	public void moveTile(LetterTilePicture lp) {
		BoardTile bt = board.getTile();
		if (bt.holds == null) {
			bt.holds = lp;
		}
	}

	/**
	 * This method will create and refill players' tile-racks.
	 * 
	 * @param player      - the current player
	 * @param lb          - the current instance of the letter bag
	 * @param bottomPanel - the bottom panel on which the tile-rack is displayed
	 */

	public void rackcheck(Player player, LetterBag lb, StackPane bottomPanel) {
		int num = 0;
		ArrayList<LetterTilePicture> potential;
		if (player.rackletters.isEmpty()) {
			num = 7;
			potential = lb.getLetters(num);
			for (LetterTilePicture lp : potential) {
				player.rackletters.add(lp);
			}
		} else if (player.rackletters.size() < 7) {
			num = 7 - player.rackletters.size();
			potential = lb.getLetters(num);
			for (LetterTilePicture lp : potential) {
				player.rackletters.add(lp);
			}
		}
		rackpicture(bottomPanel, player.rackletters);
	}

	/**
	 * This method makes images for the rack tiles.
	 * 
	 * @param bottomPanel - the bottom panel on which the tile-rack is displayed
	 * @param rackletters - the letter tiles on the rack
	 */
	public void rackpicture(StackPane bottomPanel, ArrayList<LetterTilePicture> rackletters) {
		Rectangle bottomBar = new Rectangle(420, 60);
		bottomBar.setArcWidth(30.0);
		bottomBar.setArcHeight(30.0);
		bottomBar.setFill(Color.SIENNA);
		HBox userBar = new HBox(2);
		userBar.setAlignment(Pos.CENTER);
		bottomPanel.setAlignment(Pos.CENTER);
		bottomPanel.getChildren().addAll(bottomBar, userBar);
		for (LetterTilePicture letter : rackletters) {
			if (!userBar.getChildrenUnmodifiable().contains(letter)) {
				userBar.getChildren().add(letter);
			}
			// letter.onMouseDragEnteredProperty();
			letter.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start a drag-and-drop gesture */
					/* allow any transfer mode */
					if (letter.isInBoardTile != null) {
						letter.isInBoardTile.holds = null;
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
					userBar.getChildren().add((LetterTilePicture) event.getGestureSource());
				}
				/*
				 * let the source know whether the string was successfully transferred and used
				 */
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

	/**
	 * This method removes tiles from the board.
	 */

	public void removetiles() {
		for (BoardTile bt : tiles) {
			if (bt.holds == null && board.getChildrenUnmodifiable().contains(bt.holds)) {
				gameRoot.getChildren().remove(bt.holds);
			}
		}
	}

	/**
	 * This method lets a player confirm the word that they typed on the text box
	 * after playing their turn. It then updates score for the player.
	 */

	public void confirmWord() {
		String playedWord = inputWord.getText().trim();
		String wordToTest = "";

		enteredWord.setText(""); // to clear the text shown for previous player before turn changes
		inputWord.clear();

		String str = playedWord.toLowerCase();
		boolean onlyEnglishAlphabet = true;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!(ch >= 'a' && ch <= 'z' || ch == ' ')) {
				onlyEnglishAlphabet = false;
			}
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

			if (playerOne.isSelected()) {
				int currentScore = scoring.scoreFinal(wordToTest, board, player1);
				if (currentScore == -1) {

					status = "Incorrect Word";
					points = "0";

					int playerOneScore = player1.score;
					scorePlayerOne.setText("" + playerOneScore);
					totalScorePlayerOne = "" + playerOneScore;

					this.showStatus(wordToTest, status, points);

					playerTwo.fire();

				} else if (currentScore == -2) {
					status = "Words Mismatch";
					points = "0";

					int playerOneScore = player1.score;
					scorePlayerOne.setText("" + playerOneScore);
					totalScorePlayerOne = "" + playerOneScore;

					this.showStatus(wordToTest, status, points);

				}

				else if (currentScore == -888) {
					status = "Words placement error";
					points = "0";

					int playerOneScore = player1.score;
					scorePlayerOne.setText("" + playerOneScore);
					totalScorePlayerOne = "" + playerOneScore;

					this.showStatus(wordToTest, status, points);
					playerTwo.fire();
				}

				else {
					status = "Accepted";
					points = "" + currentScore;

					int playerOneScore = player1.score;
					scorePlayerOne.setText("" + playerOneScore);
					totalScorePlayerOne = "" + playerOneScore;

					this.showStatus(wordToTest, status, points);
					playerTwo.fire();

				}
				System.out.println(player1.score);
				
			} else if (playerTwo.isSelected()) {

				int currentScore = scoring.scoreFinal(wordToTest, board, player2);
				if (currentScore == -1) {

					status = "Incorrect Word";
					points = "0";

					int playerTwoScore = player2.score;
					scorePlayerTwo.setText("" + playerTwoScore);
					totalScorePlayerTwo = "" + playerTwoScore;

					this.showStatus(wordToTest, status, points);

					playerOne.fire();

				} else if (currentScore == -2) {
					status = "Words Mismatch";
					points = "0";

					int playerTwoScore = player2.score;
					scorePlayerTwo.setText("" + playerTwoScore);
					totalScorePlayerTwo = "" + playerTwoScore;

					this.showStatus(wordToTest, status, points);
				}

				else if (currentScore == -888) {
					status = "Words placement error";
					points = "0";

					int playerTwoScore = player2.score;
					scorePlayerTwo.setText("" + playerTwoScore);
					totalScorePlayerTwo = "" + playerTwoScore;

					this.showStatus(wordToTest, status, points);
					playerOne.fire();

				}

				else {
					status = "Accepted";
					points = "" + currentScore;

					int playerTwoScore = player2.score;
					scorePlayerTwo.setText("" + playerTwoScore);
					totalScorePlayerTwo = "" + playerTwoScore;

					this.showStatus(wordToTest, status, points);
					playerOne.fire();
				}
				System.out.println(player2.score);
			}
			inputWord.setPromptText(currentPlayer + "'s Turn");
		}

	}

	/**
	 * This method shows the status and points of the last played word.
	 * 
	 * @param wordToTest - the word played by the player
	 * @param status     - if the word passed or failed
	 * @param points     - the points earned by the word
	 */
	public void showStatus(String wordToTest, String status, String points) {
		lastPlayedWord.setText("Last played word:\n" + wordToTest.toUpperCase() + "\nBy: " + currentPlayer
				+ "\nStatus: " + status + "\nPoints: " + points);

	}

	/**
	 * This method shows message to the user as they are interacting with the text
	 * box.
	 */

	public void displayTextField() {
		String playedWord = inputWord.getText();
		String str = playedWord.toLowerCase();
		boolean onlyEnglishAlphabet = true;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!(ch >= 'a' && ch <= 'z' || ch == ' ')) {
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

	/**
	 * This method displays "how to play" dialogue box.
	 */

	public void howToPlay() {
		AlertBox.howToPlay();
	}

	/**
	 * This method lets a player forfeit their turn.
	 */
	public void forfeitTurn() {
		boolean response;
		String tempCurrentPlayer = "";

		if (currentPlayer.equals("Player One")) {
			tempCurrentPlayer = "Player Two";
		} else if (currentPlayer.equals("Player Two")) {
			tempCurrentPlayer = "Player One";
		}

		response = AlertBox.alertWithUserAction("Forfeit Turn", "Forfeit " + currentPlayer + "'s turn?");

		if (response) {
			inputWord.setPromptText(tempCurrentPlayer + "'s Turn");
			if (playerOne.isSelected()) {
				playerTwo.fire();
			} else if (playerTwo.isSelected()) {
				playerOne.fire();
			}
		}
	}

	/**
	 * This method lets players reset the game.
	 */

	public void resetGame() {
		boolean response;
		response = AlertBox.alertWithUserAction("Reset Game", "Reset and play again?");
		if (response) {
			this.resetSequence();
		}
	}

	/**
	 * This method determines the outcome of the game
	 * 
	 * @param scorePlayerOne - the score of player one
	 * @param scorePlayerTwo - the score of player two
	 * @return - result - the result of the game
	 */

	public String determineOutcome(String scorePlayerOne, String scorePlayerTwo) {
		String outcome = "Tie!";

		int finalScorePlayerOne = Integer.parseInt(scorePlayerOne);
		int finalScorePlayerTwo = Integer.parseInt(scorePlayerTwo);

		if (finalScorePlayerOne > finalScorePlayerTwo) {
			outcome = "Player One wins!";
		} else if (finalScorePlayerTwo > finalScorePlayerOne) {
			outcome = "Player Two wins!";
		}

		return outcome;
	}

	/**
	 * This method lets players end the game with end game options and the winner of
	 * the last played game.
	 */

	public void endGame() {
		boolean response = AlertBox.alertWithUserAction("End Game", "End the game now?");
		if (response) {
			String outcome = this.determineOutcome(totalScorePlayerOne, totalScorePlayerTwo);
			boolean nextResponse = AlertBox.endGameAlert(totalScorePlayerOne, totalScorePlayerTwo, outcome);
			if (nextResponse) {
				this.resetSequence();
			}
		}
	}

	/**
	 * This method determines the sequence of events that occur when a game is
	 * restarted.
	 */

	private void resetSequence() {
		this.buildLayout();
		board.newBoard();
		inputWord.clear();
		lastPlayedWord.setText("");
		player1.score = 0;
		player2.score = 0;
		scorePlayerOne.setText("0");
		scorePlayerTwo.setText("0");
		totalScorePlayerOne = "0";
		totalScorePlayerTwo = "0";
	}

	public int getScorePlayerOne() {
		int scorePlayerOne = Integer.parseInt(totalScorePlayerOne);
		return scorePlayerOne;
	}

	public int getScorePlayerTwo() {
		int scorePlayerTwo = Integer.parseInt(totalScorePlayerTwo);
		return scorePlayerTwo;
	}

}
