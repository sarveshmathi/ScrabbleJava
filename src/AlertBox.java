
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * 
 * This class deals with alert events such as when the user tries to close the
 * program.
 * 
 * @author Team 51
 * 
 */

public class AlertBox {

	private static boolean response;

	/**
	 * A method that deals with alert events that requires Yes or No from the
	 * players.
	 * 
	 * @param title   - the title of the alert window.
	 * @param message - the message displayed in the window.
	 * @return response - the user's response: true or false
	 */

	public static boolean alertWithUserAction(String title, String message) {
		Stage alertWindow = new Stage();
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);// user must deal with this window first

		Label label = new Label(message);
		Button buttonYes = new Button("Yes");
		Button buttonCancel = new Button("Cancel");

		buttonYes.setOnAction(e -> {
			response = true;
			alertWindow.close();

		});


		buttonCancel.setOnAction(e -> {
			response = false;
			alertWindow.close();
		});

		HBox layoutButtons = new HBox(20); // the layout for Yes and No buttons

		layoutButtons.getChildren().addAll(buttonYes, buttonCancel);
		layoutButtons.setAlignment(Pos.CENTER);
		
		HBox labelLayout = new HBox();
		labelLayout.setAlignment(Pos.CENTER);
		labelLayout.getChildren().add(label);
		
		BorderPane finalLayout = new BorderPane();
		finalLayout.setPadding(new Insets(10, 10, 40, 10));
		finalLayout.setCenter(labelLayout);
		finalLayout.setBottom(layoutButtons);

		Scene scene = new Scene(finalLayout, 250, 250);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();

		return response;
	}

	/**
	 * A method that deals with alert events that does not require Yes or No from
	 * the players.
	 * 
	 * @param title   - the title of the alert window.
	 * @param message - the message displayed in the window.
	 */
	public static void alertWithoutUserAction(String title, String message) {
		Stage alertWindow = new Stage();
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);// user must deal with this window first

		Label label = new Label(message);
		Button buttonCancel = new Button("Cancel");

		buttonCancel.setOnAction(e -> {
			response = false;
			alertWindow.close();
		});

		HBox layoutButtons = new HBox(); // the layout for Yes and No buttons

		layoutButtons.getChildren().addAll(buttonCancel);
		layoutButtons.setAlignment(Pos.CENTER);
		
		HBox labelLayout = new HBox();
		labelLayout.setAlignment(Pos.CENTER);
		labelLayout.getChildren().add(label);
		
		BorderPane finalLayout = new BorderPane();
		finalLayout.setPadding(new Insets(10, 10, 40, 10));
		finalLayout.setCenter(labelLayout);
		finalLayout.setBottom(layoutButtons);

		Scene scene = new Scene(finalLayout, 400, 300);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();


	}

	/**
	 * A method that gives the end game dialogue box showing the winner of the game
	 * as well as the end game options - to play the game again or to quit the game
	 * 
	 * @param scorePlayerOne - the final score of Player One
	 * @param scorePlayerTwo - the final score of Player Two
	 * @return response - the user's response to whether to play the game again
	 */

	public static boolean endGameAlert(String scorePlayerOne, String scorePlayerTwo, String outcome) {
		
		Stage alertWindow = new Stage();
		alertWindow.setTitle("Final Scores");
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);// user must deal with this window first

		Label winner = new Label();
		Label playerOne = new Label("Player One: ");
		Label playerTwo = new Label("Player Two: ");
		Label playerOneScore = new Label(scorePlayerOne);
		Label playerTwoScore = new Label(scorePlayerTwo);
		Button buttonPlayAgain = new Button("Play Again");
		Button buttonQuitGame = new Button("Quit Game");
		
		winner.setText(outcome);

		HBox playerOneLayout = new HBox(10);
		playerOneLayout.setAlignment(Pos.CENTER);
		playerOneLayout.getChildren().addAll(playerOne, playerOneScore);

		HBox playerTwoLayout = new HBox(10);
		playerTwoLayout.setAlignment(Pos.CENTER);
		playerTwoLayout.getChildren().addAll(playerTwo, playerTwoScore);

		VBox playersLayout = new VBox(5);
		playersLayout.setAlignment(Pos.CENTER);
		playersLayout.getChildren().addAll(playerOneLayout, playerTwoLayout);

		VBox winnerPlayersLayout = new VBox(15);
		winnerPlayersLayout.setAlignment(Pos.CENTER);
		winnerPlayersLayout.getChildren().addAll(winner, playersLayout);

		HBox playQuitLayout = new HBox(10);
		playQuitLayout.setAlignment(Pos.CENTER);
		playQuitLayout.getChildren().addAll(buttonPlayAgain, buttonQuitGame);

		buttonPlayAgain.setOnAction(e -> {
			response = true;
			alertWindow.close();
		});

		buttonQuitGame.setOnAction(e -> {
			response = false;
			boolean nextResponse = AlertBox.alertWithUserAction("Exit", "Exit Scrabble");
			if (nextResponse) {
				System.exit(0);
			}
		});

		alertWindow.setOnCloseRequest(e -> {
			// Consumes the system close event
			e.consume();
			alertWithoutUserAction("Play Again or Quit", "Please click \"Play Again\" or \"Quit Game\".");

		});

		VBox finalLayout = new VBox(50);
		finalLayout.setAlignment(Pos.CENTER);
		finalLayout.getChildren().addAll(winnerPlayersLayout, playQuitLayout);

		Scene scene = new Scene(finalLayout, 350, 250);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();

		return response;
	}

	/**
	 * This displays the dialogue box of how to play the game.
	 */

	public static void howToPlay() {
		Stage howToPlayWindow = new Stage ();
		howToPlayWindow.setTitle("How To Play");
		Label howToPlay = new Label();
		ScrollPane howToPlayPane = new ScrollPane();
		Button cancelButton = new Button ("Cancel");
		howToPlayPane.setPadding(new Insets (10, 10, 10, 10));
		howToPlay.setText(
						"HOW TO PLAY:"
						+ "\n\nPlayer One goes first.\n\n" + "Drag and drop tiles on the board.\n\n"
						+ "When you are finished, type the word in the text box.\n\n"
						+ "Press Confirm Word button to play the word.\n\n"
						+ "Confirm Word button automatically changes turn.\n\n"
						+ "Players decide to end the game at any moment by clicking \"End Game\" button.\n\n"
						+ "Press Fofriet Turn button to forfeit current player's turn.\n\n"
						+ "Press Reset button to reset the game.\n\n\n"
						
						+ "RULES:\n\n"
						+ "1. Only submit the one word you intended to make by actually playing \n"
						+ "the word on the board. The game only accepts one word- the one you played.\n\n"
						+ "2. There are only 98 tiles, we have removed the two blank tiles.\n\n"
						+ "3. First turn must start from the center tile and can be in any direction.\n\n"
						+ "4. Playing an incorrect word will result in the player losing \n"
						+ "the played tiles and his/her chance.\n\n"
						+ "5. Playing a word which is not connected to another word in the board will also \n"
						+ "result in the player losing the played tiles and his/her chance.\n\n"
						+ "6. Maximum two players or two groups can play the game at a time.\n\n"
						+ "7. Players will have to end the game themselves whenever they want \n"
						+ "to or when their racks are empty and don’t refill.\n\n"
						+ "8. There are no extra points if a player uses all the letters \n"
						+ "in the rack in one turn.\n\n"
						+ "These are the situations where the turn changes to the next player:\n" + 
						"  a. If the current player plays a correct word.\n" + 
						"  b. If the current player plays an incorrect word.\n" + 
						"  b. If the current player forfeits their turn.\n" + 
						"  c. If the current player tries to cheat, which is defined by:\n" + 
						"    - playing a word without including at-least one current tile present on the board,\n" + 
						"    - submitting in the text field an already played word on the board and not the one \n"
						+ "they actually played."
		);
		
		cancelButton.setOnAction(e -> {
			howToPlayWindow.close();
		});
		
		howToPlayPane.setContent(howToPlay);
		HBox cancelLayout = new HBox();
		cancelLayout.setAlignment(Pos.CENTER);
		cancelLayout.setPadding(new Insets (0, 10, 20, 10));
		cancelLayout.getChildren().add(cancelButton);
		
		VBox finalPane = new VBox (20);
		finalPane.getChildren().addAll(howToPlayPane, cancelLayout);
		
		Scene howToPlayScene = new Scene (finalPane, 500, 500);
		howToPlayWindow.setScene(howToPlayScene);
		howToPlayWindow.show();
	}
}
