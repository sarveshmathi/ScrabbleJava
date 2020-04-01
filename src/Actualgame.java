
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class Actualgame {
	private final BorderPane gameroot;

	public Actualgame() {
		gameroot = new BorderPane();
		gameroot.setId("playingpage");
		gameroot.autosize();
		for (int i = 0; i <= gameroot.getWidth(); i++) {
			Rectangle rect = new Rectangle(100, 100);
			rect.setId("Tiles");
			rect.getOnDragDetected();
			gameroot.getChildren().add(rect);
		}
	}

	public BorderPane getRootPane() {
		return gameroot;
	}

}
