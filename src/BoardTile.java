/**
 * Represents one tile in the 15 x 15 board
 * 
 * @author sarvesh
 *
 */
public class BoardTile {
	private int tileNumber; // from 0 to 224
	private String tileType; // none(N), double letter (DL), double word (DW), triple letter (TL), triple
								// word (TW)

	public BoardTile(int tileNumber, String tileType) {
		this.tileNumber = tileNumber;
		this.tileType = tileType;

	}

	public int getTileNumber() {
		return tileNumber;
	}

	public String getTileType() {
		return tileType;
	}

	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

}
