
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardDisplay extends Application {
	public static final int TileSize = 100;
	public static final int Width = 15;
	public static final int Height = 15;// because we have 15 *15

	@Override
	public void start(Stage primaryStage) {
		Button startbutton = new Button("Start");
		startbutton.setOnAction(e -> {
			Actualgame game = new Actualgame();
			primaryStage.getScene().setRoot(game.getRootPane());
		});
		Button instructionbutton = new Button("How to Play?");
		Button exitbutton = new Button("Exit");
		exitbutton.setOnAction(e -> System.exit(0));
		instructionbutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Hyperlink hyperlink = new Hyperlink("https://scrabble.hasbro.com/en-us/rules");
				getHostServices().showDocument(hyperlink.getText());
				// TODO Auto-generated method stub

			}
		});
		;
		VBox root = new VBox();
		root.getChildren().add(startbutton);
		root.getChildren().add(instructionbutton);
		root.getChildren().add(exitbutton);
		root.setAlignment(Pos.CENTER);
		startbutton.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 500, 300);
		root.setId("mainpage");
		scene.getStylesheets().add("scrabblestyle.css");
		primaryStage.setTitle("SCRABBLE");
		primaryStage.show();
		primaryStage.setScene(scene);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
