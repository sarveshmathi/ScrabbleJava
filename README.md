# Team 51 Final Project - Scrabble

This project is a Scrabble game with GUI implemented in Java.

To be able to run this on your computer, you will need to clone the repo and
add the following libraries to the project once you open it on Eclipse:

1. JavaFX for Eclipse - Download e(fx)clipse 3.6.0 from Eclipse Marketplace and then follow the instructions outlined
[here](https://openjfx.io/openjfx-docs/#install-javafx) under the section "Non-modular from IDE" to add JavaFX to your project.
Make sure you add the right VM arguments to run the main method found in BoardDisplay class.

2. Download jsoup from [here](https://jsoup.org/download) and drag the jar into your project, adding it to your classpath.

3. You might have to copy the .png files from the project folder into the src folder to run the program.

Once you've followed these steps you should be able to run the project from BoardDisplay class,
which should bring up the game window.

After this, it's a simple two-player game of Scrabble, which is played according to a slightly modified version*
of the [official rules](https://scrabble.hasbro.com/en-us/rules):

1. Player One starts by default and makes a word using any of the letters in his/her rack. 
2. Player One enters the word made into the text box provided on the right pane. 
3. The computer checks with an online dictionary and if the word is an accepted word.
Player One is given the points and his/her rack is refilled. Turn changes to Player Two.
4. If the word is not accepted, Player One does not get any points, the tiles are removed from the board and turn changes.
5. If the word entered by Player One mismatches with the word played by them, an alert is given and the player is allowed to reenter the word and Steps 3-5 are repeated.
6. The game ends when the players decide to end the game by pressing the "End Game" button, for example: when no more words can be made, and the player with the highest score wins.

* Deviations from offical rules to simplfy the game: 
1. This game only accepts one word, unlike the official version in which you get points for every word formed by the move. This also means that if you form a word that inadvently forms another word with existing letters on the board, and the latter is incorrect, you will still get points for the word you intended to play, assuming it's valid. 
2. There are only 98 tiles, we have removed the two blank tiles. 
3. First turn must start from the center tile and can be in any direction.
4. Playing an incorrect word will result in the player losing the played tiles and his/her chance.
5. Playing a word which is not connected to another word in the board will also result in the player losing the played tiles and his/her chance.
