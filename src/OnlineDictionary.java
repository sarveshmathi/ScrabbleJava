import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This classes uses JSoup library to parse HTML from the official online
 * Scrabble dictionary (https://scrabble.merriam.com)
 * 
 * @author Team 51
 *
 */

public class OnlineDictionary {

	/**
	 * This method returns true if input word is allowed according to official
	 * online Scrabble dictionary (https://scrabble.merriam.com). Uses JSoup library
	 * to parse HTML. Requires Internet connection.
	 * 
	 * @param word the word to check
	 * @return true if word accepted and false if word rejected
	 */

	public static boolean checkWord(String word) {
		String url = "https://scrabble.merriam.com/finder/" + word; // the URL to check if a word is in the dictionary
																	// or not
		try {
			Document doc = Jsoup.connect(url).get();
			Elements results = doc.select("div.play_area"); // obtains the contents in the HTML class = play_area
			// System.out.println(results.toString());
			if (results.hasClass("play_yes")) { // checks if the obtained element contains "play_yes" which means the
												// word is allowed
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Class: OnlineDictionary, Method:checkWord, Error Message: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
