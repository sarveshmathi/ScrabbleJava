
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
	 * The method that deals with alert events.
	 * @param title - the title of the alert window.
	 * @param message - the message displayed in the window.
	 * @return response - the user's response: true or false
	 */

	public static boolean alert(String title, String message) {
		Stage alertWindow = new Stage();
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);// user must deal with this window first

		Label label = new Label(message);
		Button buttonYes = new Button("Yes");
		Button buttonNo = new Button("No");
		Button cancelButton = new Button("Cancel");

		buttonYes.setOnAction(e -> {
			response = true;
			alertWindow.close();

		});

		buttonNo.setOnAction(e -> {
			response = false;
			alertWindow.close();
		});

		cancelButton.setOnAction(e -> {
			alertWindow.close();
		});

		VBox layoutVBox = new VBox(20); //the final layout
		HBox layoutHBox = new HBox(20); //the layout for Yes and No buttons
		
		layoutHBox.getChildren().addAll(buttonYes, buttonNo);
		layoutHBox.setAlignment(Pos.CENTER);

		layoutVBox.getChildren().addAll(label, layoutHBox, cancelButton);
		layoutVBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layoutVBox, 300, 300);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();

		return response;
	}
}
