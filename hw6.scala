//This tool used a database of baby names, This and hw66.scala in this repository build off of eachother. 
//This one just gives the highest number of Male and Female names ber year

import scala.xml.XML
val names = XML.loadFile("/home/courses/cs208/babynames.xml")
val outerRows = names \ "row"
val innerRows = outerRows(0) \ "row"

val eth = Map(
  "WHITE NON HISPANIC" -> 0,
  "HISPANIC" -> 1,
  "BLACK NON HISP" -> 2,
  "ASIAN AND PACI" -> 3,
  "ASIAN AND PACIFIC ISLANDER" -> 3,
  "BLACK NON HISPANIC" -> 2,
  "WHITE NON HISP" -> 0)

val years = Map(
    "2011" -> 0,
    "2012" -> 1,
    "2013" -> 2,
    "2014" -> 3)

val males = innerRows.filter { v => (v \ "gndr").text == "MALE" }
val females = innerRows.filter { v => (v \ "gndr").text == "FEMALE" }

def findMax(category: scala.xml.NodeSeq, birthyearfind: String) : String = {    
 val counts = scala.collection.mutable.Map.empty[String,Array[Array[Int]]]       //Variable to hold the Name, which has a number of appearances for every year.
 category.foreach { v =>
   val name = (v \ "nm").text
   val ethnicity = (v \ "ethcty").text
   val count = (v \ "cnt").text.toInt
   val e = eth(ethnicity)
   val year = (v \ "brth_yr").text
   val y = years(year)                 //extracting data from the given set

   if(!counts.contains(name)) {
     var arr = Array.ofDim[Int](10,10)
     for( i <- 0 to 3 ) {
       for( j <- 0 to 0){
       	arr(i)(j) = 0
       }
     }
     counts += ((name) -> arr )
   }
   counts(name)(e)(y) = count 
 }


 var highnames = ""
 var highnums =	0

 counts.foreach { name =>
  val namecheck = name._1
  val highcheck = name._2
  var total = 0
  for( i <- 0 to 3 ) {
     total += highcheck(i)(years(birthyearfind)) //At the current year, total the number of births for each ethnicity 
   }
  if (total > highnums ) {  //Update highest
    highnames = namecheck
    highnums = total
           }
 }

return "For " + birthyearfind + " the most popular name was " + highnames + " with " + highnums
 
}

println("Boys:")
println(findMax(males, "2011"))
println(findMax(males, "2012"))
println(findMax(males, "2013"))
println(findMax(males, "2014"))
println(" ")
println("Girls: ")
println(findMax(females, "2011"))
println(findMax(females, "2012"))
println(findMax(females, "2013"))
println(findMax(females, "2014"))
