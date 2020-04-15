
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class displays the initial menu and reacts to user's button press.
 * 
 * @author Team 51
 *
 */

public class BoardDisplay extends Application {
	public static final int TileSize = 100;
	public static final int Width = 15;
	public static final int Height = 15;// because we have 15 *15
	Stage primaryStage;

	// Override the start() method.
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		this.primaryStage = primaryStage;
		Button startButton = new Button();

		FileInputStream playPath = new FileInputStream("play.png");
		Image play = new Image(playPath, 300, 150, true, true);
		ImageView ivPlay = new ImageView(play);
		startButton.setGraphic(ivPlay);
		startButton.getStyleClass().remove("button");

		// Handle action events for "Start" button.
		startButton.setOnAction(e -> {
			Actualgame game = new Actualgame();
			primaryStage.getScene().setRoot(game.getRootPane());
		});

		Button instructionButton = new Button();
		FileInputStream howToPlayPath = new FileInputStream("howtoplay.png");
		Image howToPlay = new Image(howToPlayPath, 550, 300, true, true);
		ImageView ivHowToPlay = new ImageView(howToPlay);
		instructionButton.setGraphic(ivHowToPlay);
		instructionButton.getStyleClass().remove("button");

		Button exitButton = new Button();

		FileInputStream exitPath = new FileInputStream("exit.png");
		Image exit = new Image(exitPath, 200, 80, true, true);
		ImageView ivExit = new ImageView(exit);
		exitButton.setGraphic(ivExit);
		exitButton.getStyleClass().remove("button");

		// Handle action events for "Exit" button.
		exitButton.setOnAction(e -> {
			this.closeProgram();
		});

		primaryStage.setOnCloseRequest(e -> {
			// Consumes the system close event
			e.consume();
			this.closeProgram();
		});

		// Handle action events for "How to Play?" button.
		instructionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Hyperlink hyperlink = new Hyperlink("https://scrabble.hasbro.com/en-us/rules");
				getHostServices().showDocument(hyperlink.getText());
				// TODO Auto-generated method stub

			}
		});
		;

		VBox root = new VBox(30);

		// Add Start button, How to Play button and Exit button to the scene graph.
		root.getChildren().addAll(startButton, instructionButton, exitButton);
		root.setAlignment(Pos.CENTER);
		startButton.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 1200, 800);
		root.setId("mainpage");
		scene.getStylesheets().add("scrabblestyle.css");
		primaryStage.setTitle("SCRABBLE");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * This method deals with the event when the user closes the program.
	 */

	private void closeProgram() {
		boolean userResponse;
		userResponse = AlertBox.alertWithUserAction("Exit", "Exit Scrabble?");
		if (userResponse) {
			primaryStage.close();
		}
	}

	/**
	 * This method launches the JavaFX program.
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
