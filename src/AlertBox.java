
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * 
 *This class deals with alert events such as when the user tries to
 *close the program.
 *@author Team 51
 * 
 */

public class AlertBox {

	private static boolean response;
	
	/**
	 * A method that deals with alert events that requires Yes or No from the user.
	 * @param title - the title of the alert window.
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
		Button buttonNo = new Button("No");
		Button buttonCancel = new Button("Cancel");

		buttonYes.setOnAction(e -> {
			response = true;
			alertWindow.close();

		});

		buttonNo.setOnAction(e -> {
			response = false;
			alertWindow.close();
		});

		buttonCancel.setOnAction(e -> {
			alertWindow.close();
		});

		VBox layoutVBox = new VBox(20); //the final layout
		HBox layoutHBox = new HBox(20); //the layout for Yes and No buttons
		
		layoutHBox.getChildren().addAll(buttonYes, buttonNo);
		layoutHBox.setAlignment(Pos.CENTER);

		layoutVBox.getChildren().addAll(label, layoutHBox, buttonCancel);
		layoutVBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layoutVBox, 350, 350);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();

		return response;
	}
	
	/**
	 * A method that deals with alert events that does not require Yes or No from the user.
	 * @param title - the title of the alert window.
	 * @param message - the message displayed in the window.
	 */
	public static void alertWithoutUserAction (String title, String message) {
		Stage alertWindow = new Stage();
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);// user must deal with this window first

		Label label = new Label(message);
		Button buttonCancel = new Button("Cancel");
		
		buttonCancel.setOnAction(e -> {
			alertWindow.close();
		});
		
		VBox layoutVBox = new VBox(20); //the final layout
		
		layoutVBox.getChildren().addAll(label, buttonCancel);
		layoutVBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layoutVBox, 350, 350);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();
		
	}
	
	public static boolean winnerAlert(String scorePlayerOne, String scorePlayerTwo) {
		int finalScorePlayerOne = Integer.parseInt(scorePlayerOne);
		int finalScorePlayerTwo = Integer.parseInt(scorePlayerTwo);
		
		
		Stage alertWindow = new Stage();
		alertWindow.setTitle("Final Scores");
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);// user must deal with this window first
		
		Label winner = new Label();
		Label playerOne = new Label("Player One: ");
		Label playerTwo = new Label("Player Two: ");
		Label playerOneScore  = new Label(scorePlayerOne);
		Label playerTwoScore = new Label(scorePlayerTwo);
		Button buttonPlayAgain = new Button ("Play Again");
		Button buttonQuitGame = new Button ("Quit Game");
		Button buttonCancel = new Button("Cancel");
		
		if (finalScorePlayerOne > finalScorePlayerTwo) {
			winner.setText ("Player One wins!");
		} else if(finalScorePlayerTwo > finalScorePlayerOne) {
			winner.setText("Player Two wins!");
		} else {
			winner.setText ("The game is draw.");
		}
		
		HBox winnerLayout = new HBox();
		winnerLayout.setAlignment(Pos.CENTER);
		winnerLayout.getChildren().add(winner);
		
		HBox playerOneLayout = new HBox (10);
		playerOneLayout.getChildren().addAll(playerOne, playerOneScore);
		
		HBox playerTwoLayout = new HBox (10);
		playerTwoLayout.getChildren().addAll(playerTwo, playerTwoScore);
		
		VBox playersLayout = new VBox (10);
		playersLayout.setAlignment(Pos.CENTER);
		playersLayout.getChildren().addAll(playerOneLayout, playerTwoLayout);
		
		HBox playQuitLayout = new HBox(10);
		playQuitLayout.setAlignment(Pos.CENTER);
		playQuitLayout.getChildren().addAll(buttonPlayAgain, buttonQuitGame);
		
		HBox cancelLayout = new HBox();
		cancelLayout.setAlignment(Pos.CENTER);
		cancelLayout.getChildren().add(buttonCancel);
		
		VBox buttonsLayout = new VBox (10);
		buttonsLayout.setAlignment(Pos.CENTER);
		buttonsLayout.getChildren().addAll(playQuitLayout, cancelLayout);
		
		buttonPlayAgain.setOnAction(e -> {
			response = true;
			alertWindow.close();
		});
		
		buttonQuitGame.setOnAction( e -> {
			System.exit(0);
		});
		
		buttonCancel.setOnAction(e -> {
			alertWindow.close();
		});
		
		VBox finalLayout = new VBox (50);
		finalLayout.getChildren().addAll(winnerLayout, playersLayout, buttonsLayout);
		
		Scene scene = new Scene (finalLayout, 350, 350);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();
		
		return response;
	}
}
