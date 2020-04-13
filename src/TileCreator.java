import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TileCreator {
	private Image image;
	private Rectangle rect;
	private Scene s;

	private WritableImage textToImage(String text) {

		Text t = new Text(text);
		Scene scene = new Scene(new BorderPane(t));
		return t.snapshot(null, null);
	}

	public static void main(String[] args) {
		// Rectangle rect = new Rectangle(50, 50);
		// Image image = new Image("doublewordscore.png");
		// LetterTile letter = new LetterTile("A", 2);

		// TODO Auto-generated method stub

	}

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		TileCreator tc = new TileCreator();
		tc.textToImage("5");
		// primaryStage.setScene(scene);
	}

}
