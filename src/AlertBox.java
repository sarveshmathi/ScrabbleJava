

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	
	private static boolean response;
	
	public static boolean alert(String title, String message) {
		
		
		Stage alertWindow = new Stage();
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(250);
		alertWindow.initModality(Modality.APPLICATION_MODAL);//user must deal with this window first
		
		Label label = new Label (message);
		Button buttonYes = new Button ("Yes");
		Button buttonNo = new Button ("No");
		
		buttonYes.setOnAction(e -> {
			response = true;
			alertWindow.close();
			
		});
		
		buttonNo.setOnAction(e -> {
			response = false;
			alertWindow.close();
		});
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> alertWindow.close());
		
		
		VBox layoutVBox = new VBox (20);
		HBox layoutHBox = new HBox (20);
		layoutHBox.getChildren().addAll(buttonYes, buttonNo);
		layoutHBox.setAlignment(Pos.CENTER);
		
		layoutVBox.getChildren().addAll (label, layoutHBox, cancelButton);
		layoutVBox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layoutVBox, 300, 300);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();
		
		return response;
		
	}

}
