import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This classes uses JSoup library to parse HTML from the official online Scrabble dictionary (https://scrabble.merriam.com)
 * @author Team 51
 *
 */

public class OnlineDictionary {
	
	/**
	 * Returns true if input word is allowed according to official online Scrabble dictionary (https://scrabble.merriam.com). 
	 * Uses JSoup library to parse HTML. Requires Internet connection.
	 * @param word - the word
	 * @return
	 */
	
	public boolean checkWord(String word) {
		String url = "https://scrabble.merriam.com/finder/" + word; // the URL to check if a word is in the dictionary or not
		try {
			Document doc = Jsoup.connect(url).get();
			Elements results = doc.select("div.play_area"); //obtains the contents in the HTML class = play_area 
			//System.out.println(results.toString());
			if (results.hasClass("play_yes")) {  //checks if the obtained element contains "play_yes" which means the word is allowed
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
	
//	public static void main(String[] args) {
//		System.out.println("Checking run time for 20 fetches:");
//		long startTime = System.nanoTime();
//		OnlineDictionary od = new OnlineDictionary();
//		String word = "her";
//		boolean wordResult = od.checkWord(word);
//		System.out.println(word + " "+ wordResult);
//		String word1 = "i";
//		boolean wordResult1 = od.checkWord(word1);
//		System.out.println(word1 + " "+ wordResult1);
//		String word2 = "sarvesh";
//		boolean wordResult2 = od.checkWord(word2);
//		System.out.println(word2 + " "+ wordResult2);
//		String word3 = "immaculate";
//		boolean wordResult3 = od.checkWord(word3);
//		System.out.println(word3 + " "+ wordResult3);
//		for (int i = 0; i < 16; i++) {
//		String word4 = "OXYPHENBUTAZONE";
//		boolean wordResult4 = od.checkWord(word4);
//		System.out.println(word4 + " " + wordResult4);
//		}
//		long endTime = System.nanoTime();
//		System.out.println("Output complete! Time taken: " + (endTime - startTime)/1000000000 + "s");
//	}

}
