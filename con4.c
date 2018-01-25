//This is an implementation of an 8x8 game of Connect 4 written in C
//Nearly the exact same as my Scala implementation. 
//Written by Alexander Peck - CS-208 - Knox College


#include <stdlib.h>
#include <stdio.h>

char board[8][8];
char player = 'X';
int movecol = 0;
int totalmoves = 0;
int availableMoves = 64;
int checkwinBool = 0;
int checkwinVal = 0;
int winVal = 4;

void printBoard() {
  for(int i=8; i>=0; i--) {  
    for (int j=0; j<8; j++) {
      printf("%c", board[i][j]);
     }
   printf("\n");
  }
}

void makeMove(int col) {        //call to let user place a move on the board
  int column = col-1;
  int index = 0;
  while (board[index][column] != '_') {
    index = index + 1;
       }
  board[index][column] = player;
}

void checkWin() {
  for(int i=0; i<7; i++) {   //check vertical wins
    checkwinVal = 0;               //value to keep track of matched plays, resets before every case just for good measure
    for (int j=0; j<7; j++) {     //First case checks for horizontal matches
      if (board[i][j] == player) {
	checkwinVal = checkwinVal + 1;
	if (checkwinVal == winVal)
          checkwinBool = 1;
    } else
        checkwinVal = 0;
      }
  }

   for(int i=0; i<7; i++) {       //second case checks for horizontal matches
     checkwinVal = 0;
      for (int j=0; j<7; j++) {
        if (board[j][i] == player)  {
          checkwinVal = checkwinVal + 1;
          if (checkwinVal == winVal)
	    checkwinBool = 1;
     } else
	   checkwinVal = 0;
       }
   }      


    for (int a=0; a<4; a++) { //Checks for BR to TL diagonals through and above the middle.. note: i may have lost track of the order of these..
      checkwinVal = 0;              //In cases that use the "a" loop, this is done to maintain checks from going out of bounds for diagonals that are not in the middle.
      int index = 7;
      for (int i=0; i<(7-a); i++) {
	if (board[i+a][index] == player) {
	  checkwinVal = checkwinVal + 1;
	  if (checkwinVal == winVal) {
	    checkwinBool = 1;
	  }
	  index = index - 1;
	} else
	  checkwinVal = 0;
      }
    }


    for (int a=0; a<4; a++) { //Checks for BR to TL diagonals that are below the middle
      checkwinVal = 0;
      int index = 7;
      for (int i=0; i<(7-a); i++) {
	 if (board[i][index-a] == player) {
	   checkwinVal = checkwinVal + 1;
	   if (checkwinVal == winVal) {
	     checkwinBool = 1;
	   }
	   index = index - 1;
	 } else
	   checkwinVal = 0;
      }
    }


    for (int a=0; a<4; a++) { //Checks BL to TR diagonals that are within and above the middle
      checkwinVal = 0;
      for(int i=0; i<(7-a); i++) {
	if (board[i+a][i] == player) {
	  checkwinVal = checkwinVal + 1;
	  if (checkwinVal == winVal)
	    checkwinBool = 1;
        } else
	  checkwinVal = 0;
      }
    }


    for (int a=1; a<4; a++) { //Checks BL to TR diagonals below the middle
      checkwinVal = 0;
      for(int i=0; i<(7-a); i++) {
       if (board[i][i+a] == player) {
	 checkwinVal = checkwinVal + 1;
       if (checkwinVal == winVal)
         checkwinBool = 1;
       } else
	 checkwinVal = 0;
      }
    }
}
 

void endGame() {  //have a call to end the game
  if (totalmoves == availableMoves) {
    printf("That's a draw.. Better luck next time!");
    printf("\n");
  } else {
       printf("%c", player);
       printf(" has won! What an intense battle.");
       printf("\n");
  }
}





int main() {

  for (int i=0; i<8; i++)     //setup board
   for (int j=0; j<8; j++)
     board[i][j] = '_';



 while ((totalmoves < availableMoves) && (checkwinBool != 1)) {   //regulate gameplay while alternating characters
  if (totalmoves % 2 == 0) {
    player = 'X';
     }else {
    player = 'O';
     }
  printf("It is ");
  printf("%c", player);
  printf("'s turn. Please choose a column 1-8 to make a move.");
  printf("\n");
  scanf("%d", &movecol);
  makeMove(movecol);
  totalmoves = totalmoves + 1;
  printBoard();
  checkWin();
   }

 endGame();
 
}
