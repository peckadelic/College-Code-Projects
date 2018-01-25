//This is an implementation of Connect 4 written in Scala
//This version supports any size board, along with any number of consecutive pieces to win, both given by the user
//Written by Alex Peck - CS-208 - Knox College


println("Enter the size of the gameboard.")
val size = readInt
println("How many pieces in a row to win?")
val winVal = readInt
println("Okay. Prepare to battle!")
val board = Array.ofDim[Char](size, size)
val moves = size * size
var totalmoves = 0
var column = 0
var player = 'X'
var checkwinBool = false
var checkwinVal = 0


for(i <- 0 to size-1)     //set up the board
  for (j <- 0 to size-1)
    board(i)(j) = '_'


def printBoard {
 for(i <- (size-1) to 0 by -1) {    //call to print the current status of the board
  for (j <- 0 to size-1) {	
    print(board(i)(j))
    }
  print("\n")
  } 
}

def makeMove(col: Int) {	//call to let user place a move on the board
 column = col - 1
 var index = 0
 while (board(index)(column) != '_') 
     index = index + 1
 board(index)(column) = player
}

def checkWin {			//Call to check if the game has been won
 for( i <- 0 to size-1) { 
  checkwinVal = 0		//value to keep track of matched plays, resets before every case just for good measure
   for (j <- 0 to size-1) {	//First case checks for horizontal matches
     if (board(i)(j) == player) {
       checkwinVal = checkwinVal + 1
       if (checkwinVal == winVal)
     	  checkwinBool = true
    } else
        checkwinVal = 0
   }
 }

 for( i <- 0 to size-1) {	//second case checks for column matches
  checkwinVal = 0
   for (j <- 0 to (size-1)) {
    if (board(j)(i) == player)	{
        checkwinVal = checkwinVal + 1
        if (checkwinVal == winVal)
           checkwinBool = true
     } else
         checkwinVal = 0
    } 
  }

  for (a <- 0 to (size-winVal)) { //Checks for BR to TL diagonals through and above the middle.. note: i may have lost track of the order of these, but I will cover all of them
   checkwinVal = 0		//In cases that use the "a" loop, this is done to maintain checks from going out of bounds for diagonals that are not in the middle. 
   var index = (size-1)
   for (i <- 0 to (size-1-a)) { 
    if (board(i+a)(index) == player) {
      checkwinVal = checkwinVal + 1
      if (checkwinVal == winVal) {
        checkwinBool = true
	 }
      index = index - 1
    } else
      checkwinVal = 0
  } 
 }

  for (a <- 0 to (size-winVal)) { //Checks for BR to TL diagonals that are below the middle
   checkwinVal = 0
   var index = (size-1)
   for (i <- 0 to (size-1-a)) {
    if (board(i)(index-a) == player) {
      checkwinVal = checkwinVal + 1
      if (checkwinVal == winVal) {
        checkwinBool = true
         }
      index = index - 1
    } else
      checkwinVal = 0
  }
 }

 
  checkwinVal = 0   
  for (a <- 0 to (size-winVal)) { //Checks BL to TR diagonals that are within and above the middle
   checkwinVal = 0
   for(i <- 0 to (size-1-a)) { 
     if (board(i+a)(i) == player) {
       checkwinVal = checkwinVal + 1
        if (checkwinVal == winVal)
          checkwinBool = true
      } else
        checkwinVal = 0
    }
   }
 

 for (a <- 1 to (size-winVal)) { //Checks BL to TR diagonals below the middle
  checkwinVal = 0
  for(i <- 0 to (size-1-a)) {
    if (board(i)(i+a) == player) {
      checkwinVal = checkwinVal + 1
       if (checkwinVal == winVal)
         checkwinBool = true
     } else
       checkwinVal = 0
   }
  }
 }


def endGame {  //have a call to end the game
  if (totalmoves == moves)
    println("That's a draw.. Better luck next time!")
     else
      println(player + " has won! What an intense battle.")
 }

while ((totalmoves < moves) && (checkwinBool == false)) {  //regulate gameplay
 if (totalmoves % 2 == 0)
  player = 'X'
    else 
      player = 'R'
 println("It is " + player + "'s turn. Type the column number you wish to play.")
 makeMove(readInt)
 totalmoves = totalmoves + 1
 printBoard
 checkWin
}

endGame
