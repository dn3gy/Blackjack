# ---------------------------------
# Description of the Blackjack game
# ---------------------------------

This program is a simplified version of the famous card game of Blackjack. It was designed specifically in Java. The rules and specifications of this version of Blackjack are in the "Blackjack Technical Assessment" document.

# -----------------------------
# How to Run the Blackjack Game
# -----------------------------

1) Unzip "Blackjack" folder
2) If using a Unix machine (Mac, Linux, etc.), then proceed to open up the terminal. If using Windows or another OS that doesn't support bash scripts, then look into using a VM or other tools (e.g. cygwin) that will support running bash scripts. 
3) Go into the "src" directory within the "Blackjack" folder in the terminal
4) Type in the command "chmod 754 run.sh" to give the computer permission to run the script
5) Then type in "./run.sh" to run the bash script
- The JUnit tests will start running and passing and then the Blackjack game will officially run
- Don’t need to worry about the output the unit tests produces because those are printed as a result of the program running in the tests

# ------------------------------
# How to Play the Blackjack Game
# ------------------------------

On the screen the user will see the cards he/she has in their hand and the cards of the dealer's hand. The program will then prompt the player to either hit or stand. To hit the player types 'h' and then the 'ENTER' key and to stand the player can type in any key, preferably 's', and then the 'ENTER' key. If the player hits and didn't bust, then the player has an option to hit again or not by typing in any key, preferably 'y', and then 'ENTER' key to hit again or the 'n' key and then 'ENTER' key to stand. Once the player stands then the dealer hits until his hand reaches 17 or more and the results of the game will then be determined. After the game is over and the results have been received, the player will then have a chance to play a new game by typing 'y' and the 'ENTER' key or not start a new game by typing the 'n' key then the 'ENTER' key. Also anytime an Ace shows up in either the player's or dealer's hand, the computer prompts you to either choose the Ace to be a value of 1 or 11. There is no advance AI involved to determine whether an Ace should be a 1 or 11, because this is a simplified version of Blackjack.
