//This is an Implementation of a drawing tool written in Scala
//Author: Alexander Peck


var movecheck = 0

println("Hello. How many drawing commands do you wish to use?")
val moves = readInt
println("Ok. Please begin each command with the type of action. p, v, or h.") 

val moveset = scala.collection.mutable.Set[(Int, Int)]()   //holds the dataset of coordinates


while (movecheck != moves) {
  println("Enter command.")
  var command = readChar
  
  if (command == 'p') {
     println("Which coordinates? X , Y  -- Each value on separate lines")
     val x = readInt  
     val y = readInt
     moveset += ((x, y))
     }
  
   if (command == 'v') {
     println("Which coordinates? Y1, Y2, X -- Each value on separate lines")   //For H and V, the input orinetation is different from how it is added in the 
     var x1 = readInt		     	      	   	    	     	       // moveset, this is for sake of orientation of the board. 
     var x2 = readInt
     var y = readInt
     while (x1 <= x2) {
    	moveset += ((x1, y))
	x1 = x1 + 1
	}
     }
   
   if (command == 'h') {
     println("Which coordniates? Y, X1, X2 -- Each value on a separate line")
     var x = readInt
     var y1 = readInt
     var y2 = readInt
     while (y1 <= y2) {
       moveset += ((x, y1))
       y1 = y1 + 1
       }
     }
movecheck = movecheck + 1		 //Regulate moves
}


def max(v1: Int, v2: Int) :Int ={  //find max of 2 given Ints in the set
if (v1 > v2) {
 return v1
} else
  return v2
}

var xMax = scala.collection.mutable.Set[Int]()
var yMax = scala.collection.mutable.Set[Int]()

moveset.foreach{ tup =>
xMax += tup._1.toInt
yMax += tup._2.toInt  //Store values for each axis
}

var trueXmax = xMax.foldLeft(0)((v1, v2) => max(v1, v2))
var trueYmax = yMax.foldLeft(0)((v1, v2) => max(v1, v2))  //determine true max with the method and fold left



println(" ")
println("Here is your picture:")
println(" ")


val board = Array.ofDim[String](trueXmax+1, trueYmax+1)  //Creating the board

for (i <- 0 until trueXmax+1) {
  for (j <- 0 until trueYmax+1) {
   board(i)(j) = "-"
   }
}					//initialize the board

moveset.foreach{ tup =>
 var xc = tup._1.toInt
 var yc = tup._2.toInt
 board(xc)(yc) = "X"
}

for (i <- 0 until trueXmax+1) {
  for (j <- 0 until trueYmax+1) {
   print(board(i)(j))
   }
 println("")				//Print the board with the desired coordinates
}  
