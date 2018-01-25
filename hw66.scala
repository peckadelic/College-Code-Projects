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


val counts = scala.collection.mutable.Map.empty[String,Array[Int]]

def findMax(category: scala.xml.NodeSeq) : String = {
category.foreach { v =>
  val name = (v \ "nm").text
  val ethnicity = (v \ "ethcty").text
  val count = (v \ "cnt").text.toInt
  val e = eth(ethnicity)
  if(!counts.contains(name)) {
    val arr = new Array[Int](4)
    for( i <- 0 to 3 ) {
      arr(i) = 0
    }
    counts += ( name -> arr )
  }
  counts(name)(e) = count
 }
 

var name = ""
var count = 0

counts.foreach { tup =>
   val k = tup._1
   val v = tup._2
   var total = 0
   for( i <- 0 to 3 ) {
     total += v(i)
   } 
   if (total > count) {
     count = total
     name = k
    }
 }
 return name + " with " + count
}

val female2011 = innerRows.filter { v => (v \ "brth_yr").text == "2011" &&
                                         (v \ "gndr").text == "FEMALE" }

println(findMax(female2011))

val female2012 = innerRows.filter { v => (v \ "brth_yr").text == "2012" &&
                                         (v \ "gndr").text == "FEMALE" }

println(findMax(female2012))
val female2013 = innerRows.filter { v => (v \ "brth_yr").text == "2013" &&
    	       	 		    (v \ "gndr").text == "FEMALE" }
println(findMax(female2013))
val female2014 = innerRows.filter { v => (v \ "brth_yr").text == "2014" &&
                                         (v \ "gndr").text == "FEMALE" }
println(findMax(female2014))
val male2011 = innerRows.filter { v => (v \ "brth_yr").text == "2011" &&
                                         (v \ "gndr").text == "MALE" }
println(findMax(male2011))
val male2012 = innerRows.filter { v => (v \ "brth_yr").text == "2012" &&
                                         (v \ "gndr").text == "MALE" }
println(findMax(male2012))
val male2013 = innerRows.filter { v => (v \ "brth_yr").text == "2013" &&
                                         (v \ "gndr").text == "MALE" }
println(findMax(male2013))
val male2014 = innerRows.filter { v => (v \ "brth_yr").text == "2014" &&
    	       			       	  (v \ "gndr").text == "MALE" }
println(findMax(male2014))