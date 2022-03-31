![alt text](Images/Thursday/StartGame.PNG "The starting screen of the game")  
The game starts on this screen

---

![alt text](Images/Thursday/playerscore.PNG "The player's score showing 0")  
Both players start with 0 points, of course.

---

Invalid inputs (such as out of range numbers or non-integers) are stopped, as are attempts to play a card that has already been played  
![alt text](Images/Thursday/invalidinput.PNG "Message for an invalid input")
![alt text](Images/Thursday/repeatcard.PNG "Message for trying to input a repeat card")

---

![alt text](Images/Final/stakecard.PNG "Number 9 card in play as the stake")  
The stake card for the round is set as 9.

---

![alt text](Images/Final/round1result.PNG "Player played 11 and and Computer played 10.")  
Both player's cards are shown together, and the winner is declared. Player played 11 and and Computer played 10, so the player wins the stake.

---

![alt text](Images/Final/p1scoreequals9.PNG "The player's score showing 9")  
The player won 9 points from the round. The score even counts up to show the change.  
![alt text](Images/Final/next.PNG "Message to start next round")

---

![alt text](Images/Final/1cardmissing.PNG "The set of player's cards but the Jack is missing")  
In the next round, the card you played is removed conveniently from the on screen set. The computer's cards go down by one too.

---

![alt text](Images/Final/win.PNG "Player wins message")  
The game continues until the player or computer wins, or there is tie. This would be after all 13 rounds have been played (i.e. there are no more cards, or a player has won more than half of points)

---

Technical bits:
![alt text](Images/Final/pointsup.PNG "code showing points increasing")  
I quite enjoy this piece of code that makes the score count up, for how simple it is.
![alt text](Images/Thursday/userinputexample.PNG "Code showing how the user inputs information")  
![alt text](Images/Thursday/buttonlistener.PNG "Button action listener code")  
I am somewhat proud of the user input code - it is really quite complicated to work so I am proud that I got it to work but am still concerned it is not optimal.  
![alt text](Images/Final/cardcode.PNG "Code related to the drawing of cards on the screen")  
I am quite proud that I got a sprite sheet working properly - with it selecting the right part of the sheet to use at the right time.  
![alt text](Images/Thursday/paintcard1.PNG "Code showing the paintComponent method of the game panel")  
I am somewhat proud of this code - that draws everything to the screen - as well, as it is quite complicated (though it possibly could have been simpler)  
