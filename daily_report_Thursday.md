![alt text](Images/Thursday/StartGame.PNG "The starting screen of the game")  
The game is now fully implemented  
![alt text](Images/Thursday/userinputexample.PNG "code that gets and verifies input")
Most of all invalid inputs are stopped.  
![alt text](Images/Thursday/invalidinput.PNG "Message for an invalid input")  
![alt text](Images/Thursday/repeatcard.PNG "Message for trying to input a repeat card")

---

The game loop is now a lot more complex, as shown above. This shows part of the code that draws the screen:
![alt text](Images/Thursday/paintcard1.PNG "code that draws the UI")

---

There is a button and a text box. The button being pressed causes the input to be taken!
![alt text](Images/Thursday/InputCar.PNG "The button listener")
The button listener:
![alt text](Images/Thursday/buttonlistener.PNG "The button listener")
It doesn't do much by itself, but it sets a flag so the game knows there is an input to check.

---

Some more bits:  
![alt text](Images/Thursday/playerscore.PNG "the player's score shown")  
The player's current score. It counts out when their bid wins the stake.  
![alt text](Images/Thursday/playerwinround.PNG "The button listener")  
And this is shown if you win the round.  
![alt text](Images/Thursday/comparingcards.PNG "Two cards placed end to end")  
Each players card are put next to each other, symbolising the comparison...
Many changes were made to old code also, including the game logic, which seems better toward the end of a game than before.

---

Current state: A working version of the game, though there are probably bugs. The computer player's strategy still isn't great.
Next steps: Try and test and improve as much as possible.
